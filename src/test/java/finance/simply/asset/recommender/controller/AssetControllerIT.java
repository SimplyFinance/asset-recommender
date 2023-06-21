package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.Application;
import finance.simply.asset.recommender.model.Asset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssetControllerIT {

  private static final String ASSETS_WITH_COST_UNDER_URL = "/api/asset?maxCost={cost}";

  private static final String SUM_OF_COSTS_FOR_ASSET_TYPES_URL = "/api/asset/sum-for-types";

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void getAssetsWithCostUnder() {
    ResponseEntity<List<Asset>> response = restTemplate.exchange(ASSETS_WITH_COST_UNDER_URL,
                                                                 HttpMethod.GET,
                                                                 null,
                                                                 new ParameterizedTypeReference<List<Asset>>() {},
                                                                 100_000);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(5);
  }

  @Test
  void getAssetsWithCostUnder_noAssets() {
    ResponseEntity<List<Asset>> response = restTemplate.exchange(ASSETS_WITH_COST_UNDER_URL,
                                                                 HttpMethod.GET,
                                                                 null,
                                                                 new ParameterizedTypeReference<List<Asset>>() {},
                                                                 1_000);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).hasSize(0);
  }

  @Test
  void getSumOfCostsForAssetTypes() {
    ResponseEntity<Map<Asset.Type, Double>> response = restTemplate.exchange(SUM_OF_COSTS_FOR_ASSET_TYPES_URL,
                                                                             HttpMethod.GET,
                                                                             null,
                                                                             new ParameterizedTypeReference<Map<Asset.Type, Double>>() {});

    Map<Asset.Type, Double> expectedResponse = new HashMap<>();
    expectedResponse.put(Asset.Type.AGRICULTURE, 330_001.0);
    expectedResponse.put(Asset.Type.CONSTRUCTION, 0.0);
    expectedResponse.put(Asset.Type.TRANSPORT, 1_800_000.0);
    expectedResponse.put(Asset.Type.WASTE, 269_999.99);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(4);
    assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(expectedResponse);
  }

}