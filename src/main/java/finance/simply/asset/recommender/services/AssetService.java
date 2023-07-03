package finance.simply.asset.recommender.services;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAssetsWithCostUnder(double maxCost) {
        return assetRepository.findAll().stream()
                .filter(asset -> asset.getCost() <= maxCost)
                .sorted(Comparator.comparing(Asset::getName))
                .collect(Collectors.toList());
    }
}
