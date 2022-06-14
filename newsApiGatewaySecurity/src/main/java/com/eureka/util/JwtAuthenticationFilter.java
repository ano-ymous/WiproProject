package com.eureka.util;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

	@Autowired
	private JwtTokenUtil jwtUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

		final List<String> apiEndpoints = List.of("/api/v1/register", "/api/v1/login");

		Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
				.noneMatch(uri -> r.getURI().getPath().contains(uri));
		
		if (isApiSecured.test(request)) {
			if (!request.getHeaders().containsKey("Authorization")) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}

			final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
			
			try {
				jwtUtil.validate(token);
			} catch (Exception e) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				return response.setComplete();
			}
		}

		return chain.filter(exchange);
	}

}
