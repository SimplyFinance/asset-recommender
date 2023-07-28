package finance.simply.asset.recommender.service;

import finance.simply.asset.recommender.model.Asset;

import java.util.List;
import java.util.Map;

public interface AssetService {
    List<Asset> getAssetsWithCostUnder(double maxCost);
    Map<Asset.Type, Double> getSumOfAllTypes();
}
