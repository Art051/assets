package com.company.assets.assets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Integer> {

    List<AssetEntity> getAssetEntitiesByDescriptionIgnoreCase(String description);
    List<AssetEntity> getAssetEntitiesByLocationEntity_LocationID(int locationID);
    List<AssetEntity> getAssetEntitiesByLocationEntity_DescriptionIgnoreCase(String description);
    List<AssetEntity> getAssetEntitiesByValueGreaterThanEqual(BigDecimal value);

    @Query("SELECT SUM(a.value) FROM AssetEntity a")
    Double sumValues();
}