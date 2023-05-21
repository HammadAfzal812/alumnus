package edu.imdadia.alumnus;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan({"edu.imdadia"})
public class AlumnusApplication {

  private static final Logger LOG = LoggerFactory.getLogger(AlumnusApplication.class);

  public static void main(final String[] args) {
    Application.launch(JavaFXApplication.class, args);
//    SpringApplication.run(InventoryApplication.class, args);
    LOG.info(
        "=========================================Info =========================================");
    LOG.debug(
        "=========================================Debug=========================================");
  }

}
