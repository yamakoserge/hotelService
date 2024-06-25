package com.gsoftCodeProjet.HotelServices.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String generateToken(Map<String, Object> extraClaims, UserDetails details){
        return Jwts.builder().setClaims(extraClaims).setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean IsTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);

        return (userName.equals(userDetails.getUsername())) && !isTokenExpiration(token);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }
    private Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }
    private boolean isTokenExpiration(String token
    ){
        return extractExpiration(token).before(new Date());
    }
    private <T> T extractClaims (String token, Function<Claims, T>claimsResolvers){
        final Claims claims= extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode("TWFuIGlzIGRKc3Rpbmd1aXNoZGQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCAuLi6");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}