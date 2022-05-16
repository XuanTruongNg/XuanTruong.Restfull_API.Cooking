package com.XuanTruong.cooking.security;

import com.XuanTruong.cooking.entity.CustomUserDetails;
import com.XuanTruong.cooking.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.XuanTruong.cooking.security.SecurityConstants.*;

@Component
@Slf4j
public class JwtTokenProvider {

    public String generateTokenForConfirmation(User user){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(Integer.toString(user.getUserId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String generateToken(CustomUserDetails userDetails, List<String> authorityList) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder().claim(ROLE, authorityList)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Claims getBodyJWT(String token) {
        return  Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUserName(String token){
        return  Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String authToken ) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error(INVALID);
        } catch (ExpiredJwtException ex) {
            log.error(EXPIRED);
        } catch (UnsupportedJwtException ex) {
            log.error(UNSUPPORTED);
        } catch (IllegalArgumentException ex) {
            log.error(CLAIMS_EMPTY);
        }
        return false;
    }
}
