package finance.simply.asset.recommender.service;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.model.Deal;
import finance.simply.asset.recommender.repository.AssetRepository;
import finance.simply.asset.recommender.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    /**
     * Find Unsold Assets which will have deal_id NULL.
     *
     * @return List of Assets.
     */
    public List<Asset> getUnsoldAssets() {
        return assetRepository.findByDealIdIsNull();
    }

}
