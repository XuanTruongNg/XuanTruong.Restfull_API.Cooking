package com.XuanTruong.cooking.security;

import com.XuanTruong.cooking.entity.CustomUserDetails;
import com.XuanTruong.cooking.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(String.format("%s,%s", userDetails.getUser().getUserId(),userDetails.getUser().getRoleName()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        String[] subjects = claims.getSubject().split(",");

        return Integer.parseInt(subjects[0]);
    }
    public String getUserRoleFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        String[] subjects = claims.getSubject().split(",");
        return subjects[1];
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
