package furniture.ecommerce.furnitureecommerce.config.security.jwt;


import furniture.ecommerce.furnitureecommerce.config.security.user.SecureUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtGenerator {
    static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication, Long expiration) {
        SecureUser authenticatedUser = (SecureUser) authentication.getPrincipal();
        String username = authenticatedUser.getUsername();
        return generateToken(username, expiration);
    }
    public String generateToken(String username, Long expiration) {
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("Trust worthy Bank")
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    
    public static String generateVerificationToken() {
        return Jwts.builder()
                .setIssuer("CLM")
                .signWith(key)
                .setIssuedAt(new Date())
                .compact();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getSignature();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthenticationCredentialsNotFoundException("Invalid token");
        }
    }
}