package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/asset")
/**
 * Asset controller.
 */
public class AssetController {

  /**
   * DAO repository {@link AssetRepository}
   */
  private final AssetRepository assetRepository;

  /**
   * Constructor for AssetController. Initializes the assetRepository.
   *
   * @param assetRepository repository for the Asset entity
   */
  public AssetController(AssetRepository assetRepository) {
    this.assetRepository = assetRepository;
  }

  /**
   * Return all assets with a cost under a specified value.
   *
   * @param maxCost the maximum cost
   * @return ResponseEntity with a list of {@link Asset}s
   */
  @GetMapping
  public ResponseEntity<List<Asset>> getAssetsWithCostUnder(@RequestParam double maxCost) {
    final List<Asset> assets = assetRepository.findByCostLessThanEqualOrderByCostAsc(maxCost);

    if (assets.isEmpty()) {
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
    }

    return ResponseEntity.ok(assets);
  }

  /**
   * Return the sum of costs for each asset type.
   *
   * @return ResponseEntity with a map of {@link Asset.Type} to sums of costs
   */
  @GetMapping("/sum-for-types")
  public ResponseEntity<Map<Asset.Type, Double>> getSumOfCostsForAssetTypes() {
    final Map<Asset.Type, Double> assetTypeCostsMap = getMap();

    final List<Object[]> results = assetRepository.findSumOfCostsForAssetTypes();
    for (Object[] result : results) {
      final Asset.Type type = (Asset.Type) result[0];
      final Double sum = (Double) result[1];
      assetTypeCostsMap.put(type, sum);
    }

    return ResponseEntity.ok(assetTypeCostsMap);
  }

  /**
   * Create asset type costs map
   *
   * @return Map of {@link Asset.Type} and Double
   */
  private static Map<Asset.Type, Double> getMap() {
    Map<Asset.Type, Double> assetTypeCostsMap = new EnumMap<>(Asset.Type.class);

    for (Asset.Type type : Asset.Type.values()) {
      assetTypeCostsMap.put(type, 0.0);
    }
    return assetTypeCostsMap;
  }

}
