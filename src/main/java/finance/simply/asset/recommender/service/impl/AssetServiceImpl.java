package finance.simply.asset.recommender.service.impl;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AssetServiceImpl implements AssetService {
    @Override
    public List<Asset> getAssetsWithCostUnder(double maxCost) {
        return null;
    }

    @Override
    public Map<Asset.Type, Double> getSumOfAllTypes() {
        return null;
    }
}
