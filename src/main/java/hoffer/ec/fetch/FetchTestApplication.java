package hoffer.ec.fetch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FetchTestApplication {

	/**
	 * Main method for the Java app, spins up the Tomcat server
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FetchTestApplication.class, args);
	}
}
