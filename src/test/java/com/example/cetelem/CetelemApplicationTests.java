package com.example.cetelem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@SpringBootTest
@Profile("dev")
class CetelemApplicationTests {

	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	void contextLoads() {
	}

}
