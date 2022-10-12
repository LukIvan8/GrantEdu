package indie.lukivan8.GrantEdu.jwt_inter;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

//Задокументировать
public interface JwtTokenUtil_inter {
    String getUsernameFromToken(String token);
    Date getIssuedAtDateFromToken(String token);
    Date getExpirationDateFromToken(String token);
    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
    Claims getAllClaimsFromToken(String token);
    Boolean isTokenExpired(String token);
    Boolean ignoreTokenExpiration(String token);
    String generateToken(UserDetails userDetails);
    String doGenerateToken(Map<String, Object> claims, String subject);
    Boolean canTokenBeRefreshed(String token);
    Boolean validateToken(String token, UserDetails userDetails);
}
