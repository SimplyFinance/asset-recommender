package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAssetsWithCostUnder(@RequestParam(required = true) double maxCost) {
        final List<Asset> assets = assetService.getAssetsWithCostUnder(maxCost);
        return assets.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(assets)
                : ResponseEntity.ok(assets);
    }

    @GetMapping("/sum-for-types")
    public ResponseEntity<Map<Asset.Type, Double>> getSumOfCostsForAssetTypes() {
        // TODO: Implement

        Map<Asset.Type, Double> assetTypeCostsMap = new HashMap<>();
        return ResponseEntity.ok(assetTypeCostsMap);
    }

}