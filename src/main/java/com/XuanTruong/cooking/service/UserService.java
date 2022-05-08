package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.entity.CustomUserDetails;
import com.XuanTruong.cooking.entity.Role;
import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.payload.RegistrationRequest;
import com.XuanTruong.cooking.reponsitory.IRoleRepository;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null ){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);

    }
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null ){
            throw new UsernameNotFoundException(username);
        }
        return user;

    }
    public UserDetails loadUserById(Integer userID) throws UsernameNotFoundException {
        User user = userRepository.findUsersById(userID);
        if(user == null ){
            throw new UsernameNotFoundException("not found");
        }
        return new CustomUserDetails(user);

    }
    public Boolean IsEmailExist(String email) {
        User user = userRepository.findUsersByEmail(email);
        if(user == null ){
           return false;
        }
        return true;
    }
    public Boolean IsUserExist(String username){
        User user = userRepository.findByUserName(username);
        if(user == null) {
            return false;
        }
        return true;
    }
    public void createUser(RegistrationRequest registrationRequest){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        User user = mapper.map(registrationRequest,User.class);
        user.setPassWord(encoder.encode(registrationRequest.getPassWord()));
        user.setCreatedAt(new Date());
        user.setStatus(false);
        userRepository.save(user);
        Role role = new Role();
        role.setUserId(user.getUserId());
        roleRepository.save(role);

    }
    public List<String> getRoleByUserId(Integer userId){
        return roleRepository.getRoleByUserId(userId);
    }
}
