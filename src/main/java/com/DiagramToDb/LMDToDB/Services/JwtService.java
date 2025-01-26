package com.DiagramToDb.LMDToDB.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private String secretkey="";
    @Autowired
    private TokenBlacklistService tokenBlacklistService;
    public JwtService(){
        try {
            KeyGenerator k = KeyGenerator.getInstance("HmacSHA256");
            SecretKey skey=k.generateKey();
          secretkey=  Base64.getEncoder().encodeToString(skey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        String s=  Jwts.builder()
                .claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*60*30*60)).and().signWith(getKey())
                .compact();
        return s;
    }

    private SecretKey getKey() {
        byte[] key = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey()).build()
                .parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token)&&!tokenBlacklistService.isBlacklisted(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
