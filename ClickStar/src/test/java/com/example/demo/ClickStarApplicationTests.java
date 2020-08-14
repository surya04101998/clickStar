package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClickStarApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void fakeAssert() {
		assertEquals(1, 1);
	}

}
