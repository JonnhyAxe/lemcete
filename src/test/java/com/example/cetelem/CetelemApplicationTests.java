package com.example.cetelem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@SpringBootTest
class CetelemApplicationTests {

	@Test
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)

	void contextLoads() {
	}

}
