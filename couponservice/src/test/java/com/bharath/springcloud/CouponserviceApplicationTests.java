package com.bharath.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.bharath.springcloud.model.Coupon;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponserviceApplicationTests {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Test
	public void contextLoads() {
		System.out.println(new Pbkdf2PasswordEncoder("secret", 10000, 128).encode("password"));
		System.out.println(new SCryptPasswordEncoder().encode("password"));
		System.out.println(new BCryptPasswordEncoder().encode("password"));

		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());

		System.out.println(new DelegatingPasswordEncoder("pbkdf2", encoders).encode("password"));

	}

	@Test
	public void testOauth() {
		restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("username", "doug@bailey.com");
		restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("password", "doug");
		System.out.println(restTemplate.getForObject("http://localhost:9091/couponapi/coupons/SUPERSALE", Coupon.class));
	}

}
