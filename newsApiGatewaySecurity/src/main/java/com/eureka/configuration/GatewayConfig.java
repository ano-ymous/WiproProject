package com.eureka.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import com.eureka.util.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {
	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		System.out.println("tharun");
		return builder.routes().route("login", r -> r.path("/api/v1/login").uri("http://localhost:8081/api/v1/login"))
				.route("register",r-> r.path("/api/v1/register").uri("http://localhost:8081/api/v1/register"))
				.route("favorites", r -> r.path("/news/api/favorites/**").filters(f -> f.filter(filter)).uri("http://localhost:8083/news/api/favorites/**"))
				.route("fetch", r -> r.path("/news/api/fetch/**").filters(f -> f.filter(filter)).uri("http://localhost:8082/news/api/fetch/**"))
				.route("userdetails", r -> r.path("/api/v1/**").filters(f -> f.filter(filter)).uri("http://localhost:8081/api/v1/**")).build();
	}
	@Bean
    public CorsWebFilter corsFilter() {
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST","PUT", "OPTIONS"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
