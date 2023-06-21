package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.model.Asset;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

  @GetMapping
  public ResponseEntity<List<Asset>> getAssetsWithCostUnder(double maxCost) {
    // TODO: Implement

    List<Asset> assets = new ArrayList<>();
    return ResponseEntity.ok(assets);
  }

  @GetMapping("/sum-for-types")
  public ResponseEntity<Map<Asset.Type, Double>> getSumOfCostsForAssetTypes() {
    // TODO: Implement

    Map<Asset.Type, Double> assetTypeCostsMap = new HashMap<>();
    return ResponseEntity.ok(assetTypeCostsMap);
  }

}