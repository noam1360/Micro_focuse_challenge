package phoneBookApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * Main class
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        LOGGER.info("Starting Application");
        SpringApplication.run(Application.class, args);
    }
}
