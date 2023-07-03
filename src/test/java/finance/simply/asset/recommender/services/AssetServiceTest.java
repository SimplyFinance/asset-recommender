package finance.simply.asset.recommender.services;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssetServiceTests {

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
        final List<Asset> assets = Arrays.asList(
                new Asset(1, "Asset 1", 50000, Asset.Type.AGRICULTURE),
                new Asset(2, "Asset 2", 75000, Asset.Type.AGRICULTURE)
        );
        when(assetRepository.findAll()).thenReturn(assets);

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
}
