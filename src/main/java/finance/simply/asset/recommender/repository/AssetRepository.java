package finance.simply.asset.recommender.repository;

import finance.simply.asset.recommender.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    @Query("SELECT a FROM Asset a WHERE a.cost <= :maxCost")
    List<Asset> findByMaxCost(@Param("maxCost") double maxCost);

    @Query("SELECT a FROM Asset a WHERE a.type = :assetType")
    List<Asset> findByAssetType(@Param("assetType") Asset.Type assetType);
}
