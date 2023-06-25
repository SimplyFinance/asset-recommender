package finance.simply.asset.recommender.repository;

import finance.simply.asset.recommender.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * Repository interface for the {@link Asset} entity.
 */
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    /**
     * Find all assets with a cost less than or equal to a specified value.
     *
     * @param maxCost the maximum cost
     * @return list of {@link Asset}
     */
    List<Asset> findByCostLessThanEqualOrderByCostAsc(double maxCost);

    /**
     * Get the sum of costs for each asset type.
     *
     * @return list of object arrays, each containing an asset type and the sum of its costs
     */
    @Query("SELECT type, SUM(cost) FROM Asset GROUP BY type")
    List<Object[]> findSumOfCostsForAssetTypes();
}
