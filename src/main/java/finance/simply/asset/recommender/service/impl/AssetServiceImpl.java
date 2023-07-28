package finance.simply.asset.recommender.service.impl;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import finance.simply.asset.recommender.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> getAssetsWithCostUnder(double maxCost) {
        return assetRepository.findByMaxCost(maxCost);
    }

    @Override
    public Map<Asset.Type, Double> getSumOfAllTypes() {
        final Map<Asset.Type, Double> allAssetTypesMap = getMapOfAllAssetTypes();
        return findAssetsByTypeAndSumCosts(allAssetTypesMap);
    }

    private Map<Asset.Type, Double> getMapOfAllAssetTypes() {
        final Map<Asset.Type, Double> assetTypesMap = new HashMap<>();

        for (Asset.Type type : Asset.Type.values()) {
            assetTypesMap.put(type, 0.0);
        }

        return assetTypesMap;
    }

    private Map<Asset.Type, Double> findAssetsByTypeAndSumCosts(Map<Asset.Type, Double> assetTypeCostsMap) {
        assetTypeCostsMap.forEach((type, sumOfCost) -> {
            final List<Asset> assetsByType = assetRepository.findByAssetType(type);
            assetsByType.forEach(asset -> assetTypeCostsMap.put(type,  assetTypeCostsMap.get(type) + asset.getCost()));
        });

        return assetTypeCostsMap;
    }
}
