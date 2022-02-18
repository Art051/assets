package com.company.assets.assets;

import com.company.assets.exceptions.ApiRequestException;
import com.company.assets.locations.LocationRepository;
import com.company.assets.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    private final AssetRepository assetRepository;
    @Autowired
    private final LocationRepository locationRepository;
    @Autowired
    private final UserRepository userRepository;


    public AssetService(AssetRepository assetRepository,
                        LocationRepository locationRepository,
                        UserRepository userRepository) {
        this.assetRepository = assetRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    public String invalidQuery(String entity, String field) {
        return "This " + entity + " " + field + " does not exist, please enter a valid query.";
    }


    public List<AssetEntity> getAllAssets() {
        return assetRepository.findAll();
    }

    public AssetEntity getAssetByID(int id) {
        if (!assetRepository.existsById(id)) {
            throw new ApiRequestException(invalidQuery("asset", "ID"));
        }
        else {
            return assetRepository.findById(id).get();
        }
    }

    public List<AssetEntity> getAssetsByDesc(String description) {
        if (assetRepository.getAssetEntitiesByDescriptionIgnoreCase(description).isEmpty()) {
            throw new ApiRequestException(invalidQuery("asset", "description"));
        }
        else {
            return assetRepository.getAssetEntitiesByDescriptionIgnoreCase(description);
        }
    }

    public List<AssetEntity> getAssetsByLocationID(int locationID) {
        if (!locationRepository.existsById(locationID)) {
            throw new ApiRequestException(invalidQuery("location","ID"));
        }
        else {
            return assetRepository.getAssetEntitiesByLocationEntity_LocationID(locationID);
        }
    }

    public List<AssetEntity> getAssetsByLocationDesc(String description) {
        if (locationRepository.getLocationEntitiesByDescriptionIgnoreCase(description).isEmpty()) {
            throw new ApiRequestException(invalidQuery("location", "description"));
        }
        else {
            return assetRepository.getAssetEntitiesByLocationEntity_DescriptionIgnoreCase(description);
        }
    }

    public double getTotalAssetValue(){
        return assetRepository.sumValues();
    }

    public ResponseEntity<AssetEntity> createAsset(AssetEntity newAsset) {
        if (assetRepository.existsById(newAsset.getAssetID())) {
            throw new ApiRequestException("This asset ID already exists.");
        }
        if (!locationRepository.existsById(newAsset.getLocationEntity().getLocationID())) {
            throw new ApiRequestException(invalidQuery("location","ID"));
        }
        if (!userRepository.existsById(newAsset.getUserEntity().getUserID())) {
            throw new ApiRequestException(invalidQuery("location","ID"));
        }
        else {
            assetRepository.save(newAsset);
        }
        return new ResponseEntity<>(newAsset, HttpStatus.CREATED);
    }

    public List<AssetEntity> getHighValues(int highValueLimit) {
        if(assetRepository.getAssetEntitiesByValueGreaterThanEqual(BigDecimal.valueOf(highValueLimit)).isEmpty()){
            throw new ApiRequestException("There are no assets which are greater than or equal to " + highValueLimit + " specified limit.");
        }
        else {
            return assetRepository.getAssetEntitiesByValueGreaterThanEqual(BigDecimal.valueOf(highValueLimit));
        }
    }

    public ResponseEntity<AssetEntity> updateAsset(AssetEntity newAsset) {
        if (!assetRepository.existsById(newAsset.getAssetID())) {
            throw new ApiRequestException(invalidQuery("asset","ID"));
        }
        if (!locationRepository.existsById(newAsset.getLocationEntity().getLocationID())) {
            throw new ApiRequestException(invalidQuery("location","ID"));
        }
        if (!userRepository.existsById(newAsset.getUserEntity().getUserID())) {
            throw new ApiRequestException(invalidQuery("user","ID"));
        }
        else {
            newAsset.setDescription(newAsset.getDescription());
            newAsset.setDescription(newAsset.getDescription());
            newAsset.setMake(newAsset.getMake());
            newAsset.setModel(newAsset.getModel());
            newAsset.setValue(newAsset.getValue());
            newAsset.setUserEntity(newAsset.getUserEntity());
            newAsset.setLocationEntity(newAsset.getLocationEntity());
            final AssetEntity updatedAsset = assetRepository.save(newAsset);
            return ResponseEntity.ok(updatedAsset);
        }
    }

    public ResponseEntity<String> removeAsset(int id) {
        String assetDesc = getAssetByID(id).getDescription();
        if (!assetRepository.existsById(id)) {
            throw new ApiRequestException(invalidQuery("asset","ID"));
        }
        else {
            assetRepository.delete(getAssetByID(id));
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Asset " + id + ": " + "\"" + assetDesc + "\"" + " has been deleted.");
        }
    }
}