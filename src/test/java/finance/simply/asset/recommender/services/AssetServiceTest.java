package finance.simply.asset.recommender.services;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssetServiceTest {

    private AssetRepository assetRepository;

    private AssetService assetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assetRepository = mock(AssetRepository.class);
        assetService = new AssetService(assetRepository);
    }

    @Test
    void returnsAssetsWhereCostIsLessOrEqualToMaxCost() {
        final double maxCost = 75000;
        when(assetRepository.findAll()).thenReturn(getAllAssets());

        final List<Asset> result = assetService.getAssetsWithCostUnder(maxCost);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Asset 1");
        assertThat(result.get(1).getName()).isEqualTo("Asset 2");
    }

    @Test
    void noAssetsAreReturnedWhenThereAreNoneWithCostLessOrEqualToMaxCost() {
        final double maxCost = 100000;
        when(assetRepository.findAll()).thenReturn(Collections.emptyList());

        final List<Asset> result = assetService.getAssetsWithCostUnder(maxCost);

        assertThat(result).isEmpty();
    }

    @Test
    void returnsSumOfCostsForAllAssetTypesInTheRepositoryAndAddsTheMissingAssetsInTheRepository() {
        when(assetRepository.findAll()).thenReturn(getAllAssets());

        final Map<Asset.Type, Double> assetTypeCostsMap = assetService.getSumOfCostsForAllAssetTypesInTheRepository();

        assertThat(assetTypeCostsMap).hasSize(4);
        assertThat(assetTypeCostsMap.get(Asset.Type.AGRICULTURE)).isEqualTo(50000);
        assertThat(assetTypeCostsMap.get(Asset.Type.TRANSPORT)).isEqualTo(75000);
        assertThat(assetTypeCostsMap.get(Asset.Type.CONSTRUCTION)).isEqualTo(100000);
        assertThat(assetTypeCostsMap.get(Asset.Type.WASTE)).isEqualTo(0.0);
    }

    @Test
    void returnsAllAssetTypesWithCostZeroWhenNoneAreAvailableInTheRepository() {
        final List<Asset> allAssets = Collections.emptyList();
        when(assetRepository.findAll()).thenReturn(allAssets);

        final Map<Asset.Type, Double> assetTypeCostsMap = assetService.getSumOfCostsForAllAssetTypesInTheRepository();

        assertThat(assetTypeCostsMap).hasSize(4);
        assertThat(assetTypeCostsMap.get(Asset.Type.AGRICULTURE)).isEqualTo(0.0);
        assertThat(assetTypeCostsMap.get(Asset.Type.TRANSPORT)).isEqualTo(0.0);
        assertThat(assetTypeCostsMap.get(Asset.Type.CONSTRUCTION)).isEqualTo(0.0);
        assertThat(assetTypeCostsMap.get(Asset.Type.WASTE)).isEqualTo(0.0);
    }

    private static List<Asset> getAllAssets() {
        return Arrays.asList(
                new Asset(1, "Asset 1", 50000, Asset.Type.AGRICULTURE),
                new Asset(2, "Asset 2", 75000, Asset.Type.TRANSPORT),
                new Asset(3, "Asset 3", 100000, Asset.Type.CONSTRUCTION)
        );
    }
}
