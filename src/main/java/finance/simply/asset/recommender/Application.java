package finance.simply.asset.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
/**
 * Main entry point for the Spring Boot application.
 */
public class Application extends SpringBootServletInitializer {

  /**
   * Configure the application.
   *
   * @param application SpringApplicationBuilder
   * @return SpringApplicationBuilder
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  /**
   * Main method for the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Configuration for the embedded Tomcat server.
   *
   * @return WebServerFactory
   */
  @Bean
  public WebServerFactory servletContainer() {
    return new TomcatServletWebServerFactory();
  }

  /**
   * Configuration for the ObjectMapper.
   *
   * @return ObjectMapper
   */
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return mapper;
  }

}

