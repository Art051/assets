package com.company.assets.assets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Integer> {

    List<AssetEntity> getAssetEntitiesByDescription(String description);
    List<AssetEntity> getAssetEntitiesByLocationEntity_LocationID(int locationID);
}