package com.il.apigateway.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.il.apigateway.provider.PublicKeyProvider;

import java.security.interfaces.RSAPublicKey;

@Component
public class JwtGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtGatewayFilterFactory.Config> {

    private final PublicKeyProvider keyProvider;

    public JwtGatewayFilterFactory(PublicKeyProvider keyProvider) {
        super(Config.class);
        this.keyProvider = keyProvider;
    }
    /**
     * This method extracts the user ID from the JWT token.
     * It parses the token and retrieves the subject, which is expected to be the user ID.
     *
     * @param token The JWT token from which to extract the user ID.
     * @return The user ID extracted from the token.
     * @throws JwtException if the token is invalid or if the user ID cannot be found.
     */
    public String extractUserID(String token, RSAPublicKey publicKey) {
        try {
            String userID = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            if (userID == null || userID.isEmpty()) {
                throw new JwtException("Invalid JWT token: User ID not found");
            }
            return userID;
        } catch (SignatureException ex) {
            throw new JwtException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtException("JWT claims string is empty.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * This method is called to create a GatewayFilter that will be applied to the request.
     * It checks for the presence of a valid JWT in the Authorization header.
     * If the JWT is valid, it allows the request to proceed; otherwise, it returns an Unauthorized response.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if(token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try {
                RSAPublicKey publicKey = keyProvider.getPublicKey();

                String jwtToken = token.substring(7);
                String userId = extractUserID(jwtToken, publicKey);

                // Thêm user info vào header (nếu cần)
                ServerHttpRequest mutation = exchange.getRequest()
                        .mutate()
                        .header("userId", userId)
                        .build();
                return chain.filter(exchange.mutate().request(mutation).build());
            } catch (Exception e) {
                System.err.println("JWT validation failed: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {}
}
