package com.luca.tinder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luca.tinder.controller.PerfilesController;


@SpringBootTest
class LucaTinderApplicationTests {

	@Autowired
    private PerfilesController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	
	
	}

}
