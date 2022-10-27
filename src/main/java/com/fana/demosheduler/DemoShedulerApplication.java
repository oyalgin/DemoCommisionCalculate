package com.fana.demosheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoShedulerApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DemoShedulerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoShedulerApplication.class, args);
	}

	@Autowired
	private ConfigurableApplicationContext context;

	@Override
	public void run(String... args) throws Exception {
		logger.info("Command line runner started");
		//scheduleConfig.scheduleTask();

		Thread.sleep(99000);
			System.exit(SpringApplication.exit(context));
	}
}
