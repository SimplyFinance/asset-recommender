package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAssetsWithCostUnder(
            @RequestParam(required = true) double maxCost) {
        final List<Asset> assets = assetRepository.findAll().stream()
                .filter(asset -> asset.getCost() <= maxCost)
                .sorted(Comparator.comparing(Asset::getName))
                .collect(Collectors.toList());
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/sum-for-types")
    public ResponseEntity<Map<Asset.Type, Double>> getSumOfCostsForAssetTypes() {
        // TODO: Implement

        Map<Asset.Type, Double> assetTypeCostsMap = new HashMap<>();
        return ResponseEntity.ok(assetTypeCostsMap);
    }

}