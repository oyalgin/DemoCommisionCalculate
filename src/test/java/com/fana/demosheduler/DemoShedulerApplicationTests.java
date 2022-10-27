package com.fana.demosheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@SpringBootTest
class DemoShedulerApplicationTests {

	@Autowired
	private CommandLineRunner clr;

	@Test
	void contextLoads() {
	}



}
