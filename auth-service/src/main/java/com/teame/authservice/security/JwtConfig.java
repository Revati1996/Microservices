package com.teame.authservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Getter;
import lombok.ToString;
	
@Getter 		
@ToString
@CrossOrigin
public class JwtConfig {

	@Value("${security.jwt.uri:/auth/login/**}")
	
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60*180}}")
    private Long expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
    
    
	public String getUri() {
		return Uri;
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public Long getExpiration() {
		return expiration;
	}

	public String getSecret() {
		return secret;
	}
    
}
