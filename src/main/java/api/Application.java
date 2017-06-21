package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.Logger;

@SpringBootApplication
public class Application {

  static Logger log = Logger.getLogger(Application.class.getName());

  public static void main(String[] args) {
    log.info("Starting application");
    SpringApplication.run(Application.class, args);
    log.info("Application is exiting");
  }
}
