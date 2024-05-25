package org.vtb.practice.task04;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vtb.practice.task04.streaming.Streamable;

@SpringBootApplication
public class StartApplication implements ApplicationRunner {
	final Streamable streamable;

	public StartApplication(Streamable streamable) {
		this.streamable = streamable;
	}

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Override
	public void run (ApplicationArguments args) {
		streamable.run();
	}
}
