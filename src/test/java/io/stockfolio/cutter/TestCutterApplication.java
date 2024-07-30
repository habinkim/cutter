package io.stockfolio.cutter;

import org.springframework.boot.SpringApplication;

public class TestCutterApplication {

	public static void main(String[] args) {
		SpringApplication.from(CutterApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
