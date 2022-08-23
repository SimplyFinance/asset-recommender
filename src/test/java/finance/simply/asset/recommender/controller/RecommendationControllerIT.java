package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application.properties"})
@RunWith(SpringRunner.class)
public class RecommendationControllerIT {

  private static final String TEST_ENDPOINT_URL = "/api/recommendation?date={date}";

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getRecommendations1() {
    LocalDate date = LocalDate.of(2022, 1, 1);
    ResponseEntity<String> response =
            restTemplate.exchange(TEST_ENDPOINT_URL, HttpMethod.GET, null, String.class, date);

      assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        assertEquals(
                "{\"2\":[{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null},{\"id\":4,\"name\":\"Sprinkler\",\"cost\":125000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}],\"5\":[{\"id\":10,\"name\":\"Biomass Boiler\",\"cost\":75000.0,\"assetType\":{\"id\":4,\"name\":\"WASTE\"},\"deal\":null}]}",
                response.getBody());
  }

  @Test
  public void getRecommendations2() {
    LocalDate date = LocalDate.of(2023, 1, 2);
    ResponseEntity<String> response =
            restTemplate.exchange(TEST_ENDPOINT_URL, HttpMethod.GET, null, String.class, date);

   
     assertEquals(HttpStatus.OK, response.getStatusCode());
        String expected = "{\"1\":[{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}],\"2\":[{\"id\":4,\"name\":\"Sprinkler\",\"cost\":125000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null},{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}]}";
        String actual = response.getBody();
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);
  }

  @Test
  public void getRecommendations3() {
    LocalDate date = LocalDate.of(2024, 8, 18);
    ResponseEntity<String> response =
            restTemplate.exchange(TEST_ENDPOINT_URL, HttpMethod.GET, null, String.class, date);

    assertEquals(HttpStatus.OK, response.getStatusCode());
   
        String expected = "{\"1\":[{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}],\"2\":[{\"id\":4,\"name\":\"Sprinkler\",\"cost\":125000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null},{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}],\"5\":[{\"id\":10,\"name\":\"Biomass Boiler\",\"cost\":75000.0,\"assetType\":{\"id\":4,\"name\":\"WASTE\"},\"deal\":null}]}";
        String actual = response.getBody();
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);
  }
  
  
   @Test
    public void getRecommendations4() {
        LocalDate date = LocalDate.of(2016, 9, 28);
        ResponseEntity<String> response =
                restTemplate.exchange(TEST_ENDPOINT_URL, HttpMethod.GET, null, String.class, date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        String expected = "{\"1\":[{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}],\"2\":[{\"id\":4,\"name\":\"Sprinkler\",\"cost\":125000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null},{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}]}";
        String actual = response.getBody();
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getRecommendations5() {
        LocalDate date = LocalDate.of(2015, 1, 30);
        ResponseEntity<String> response =
                restTemplate.exchange(TEST_ENDPOINT_URL, HttpMethod.GET, null, String.class, date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        String expected = "{\"1\":[{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}],\"2\":[{\"id\":4,\"name\":\"Sprinkler\",\"cost\":125000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null},{\"id\":3,\"name\":\"Baler\",\"cost\":55000.0,\"assetType\":{\"id\":1,\"name\":\"AGRICULTURE\"},\"deal\":null}],\"5\":[{\"id\":10,\"name\":\"Biomass Boiler\",\"cost\":75000.0,\"assetType\":{\"id\":4,\"name\":\"WASTE\"},\"deal\":null}]}";
        String actual = response.getBody();
        System.out.println(expected);
        System.out.println(actual);
        
  

}
