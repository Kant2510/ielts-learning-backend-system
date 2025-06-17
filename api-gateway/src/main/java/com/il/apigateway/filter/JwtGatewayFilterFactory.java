package com.il.apigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            System.out.println("JWT Token: " + token);
            if(token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try {
                RSAPublicKey publicKey = keyProvider.getPublicKey();

                Claims claims = Jwts.parser()
                        .verifyWith(publicKey)
                        .build()
                        .parseSignedClaims(token.substring(7))
                        .getPayload();

                // Thêm user info vào header (nếu cần)
                ServerHttpRequest mutation = exchange.getRequest()
                        .mutate()
                        .header("userId", claims.getSubject())
                        .build();
                return chain.filter(exchange.mutate().request(mutation).build());

            } catch (Exception e) {
                System.err.println("JWT validation failed: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

//            return webClient.get()
//                    .uri("/validate")
//                    .header(HttpHeaders.AUTHORIZATION, token)
//                    .retrieve()
//                    .toBodilessEntity()
//                    .then(chain.filter(exchange));
        };
    }

    public static class Config {}
}
