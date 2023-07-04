package finance.simply.asset.recommender.services;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAssetsWithCostUnder(double maxCost) {
        return assetRepository.findAll().stream()
                .filter(asset -> asset.getCost() <= maxCost)
                .sorted(Comparator.comparing(Asset::getName))
                .collect(Collectors.toList());
    }

    public Map<Asset.Type, Double> getSumOfCostsForAllAssetTypesInTheRepository() {
        final Map<Asset.Type, Double> allAssetsFromRepository = getAllAssetsFromRepository();

        return getAllAssetsIncludingMissingTypesInTheRepository(allAssetsFromRepository);
    }

    private Map<Asset.Type, Double> getAllAssetsFromRepository() {
        return assetRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(
                                Asset::getType,
                                Collectors.summingDouble(Asset::getCost)
                        ));
    }

    private Map<Asset.Type, Double> getAllAssetsIncludingMissingTypesInTheRepository(Map<Asset.Type, Double> allAssetsFromRepository) {
        final Map<Asset.Type, Double> copyOfAllAssetsFromRepository = new HashMap<>();
        copyOfAllAssetsFromRepository.putAll(allAssetsFromRepository);

        for (Asset.Type type : Asset.Type.values()) {
            copyOfAllAssetsFromRepository.computeIfAbsent(type, k -> 0.0);
        }
        return copyOfAllAssetsFromRepository;
    }
}
