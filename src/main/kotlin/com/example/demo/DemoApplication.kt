package com.example.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class DemoApplication { }

/**
 * The main method makes it possible to run the application as a plain Java
 * application which starts embedded web server via Spring Boot.
 *
 * @param args
 *            command line parameters
 */
fun main(args: Array<String>) {
	SpringApplication.run(DemoApplication::class.java, *args)
}