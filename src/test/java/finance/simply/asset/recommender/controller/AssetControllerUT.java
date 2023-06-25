package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetControllerUT {

    private static final int MAX_COST = 1000;
    private AssetController assetController;

    private List<Asset> assets;
    @Mock
    private AssetRepository assetRepository;

    @BeforeEach
    public void setup() {
        assetController = new AssetController(assetRepository);
        assets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            assets.add(new Asset());
        }
    }

    @Test
    public void getAssetsWithCostUnder_happyPath() {
        when(assetRepository.findByCostLessThanEqualOrderByCostAsc(MAX_COST)).thenReturn(assets);

        final ResponseEntity<List<Asset>> response = assetController.getAssetsWithCostUnder(MAX_COST);

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(5);
    }

    @Test
    public void getAssetsWithCostUnder_failure_returns404() {
        when(assetRepository.findByCostLessThanEqualOrderByCostAsc(MAX_COST)).thenReturn(Collections.emptyList());

        final ResponseEntity<List<Asset>> response = assetController.getAssetsWithCostUnder(MAX_COST);
        assertThat(response.getStatusCode().is4xxClientError());
        assertThat(response).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(0);
    }

    @Test
    public void getAssetsWithCostUnder_exception_throwRuntimeException() {
        when(assetRepository.findByCostLessThanEqualOrderByCostAsc(MAX_COST)).thenThrow(new RuntimeException("Unexpected exception"));

        try {
            assetController.getAssetsWithCostUnder(MAX_COST);
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).isEqualTo("Unexpected exception");
        }
    }

    @Test
    public void getSumOfCostsForAssetTypes_happyPath_returnCorrectValues() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{Asset.Type.AGRICULTURE, 330_001.0});
        data.add(new Object[]{Asset.Type.CONSTRUCTION, 0.0});
        data.add(new Object[]{Asset.Type.TRANSPORT, 1_800_000.0});
        data.add(new Object[]{Asset.Type.WASTE, 269_999.99});

        when(assetRepository.findSumOfCostsForAssetTypes()).thenReturn(data);
        final ResponseEntity<Map<Asset.Type, Double>> response = assetController.getSumOfCostsForAssetTypes();

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(4);
    }

    @Test
    public void testGetSumOfCostsForAssetTypes_emptyDB_returnsEmptyCost() {
        when(assetRepository.findSumOfCostsForAssetTypes()).thenReturn(Collections.emptyList());

        final ResponseEntity<Map<Asset.Type, Double>> response = assetController.getSumOfCostsForAssetTypes();

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(4);
    }

    @Test
    public void testGetSumOfCostsForAssetTypes_exception_throwRuntimeException() {
        when(assetRepository.findSumOfCostsForAssetTypes()).thenThrow(new RuntimeException("Unexpected exception"));

        try {
            assetController.getSumOfCostsForAssetTypes();
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).isEqualTo("Unexpected exception");
        }
    }
}
