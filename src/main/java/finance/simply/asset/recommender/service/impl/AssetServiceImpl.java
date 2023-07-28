package finance.simply.asset.recommender.service.impl;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import finance.simply.asset.recommender.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //TODO - Implement
        return null;
    }
}
