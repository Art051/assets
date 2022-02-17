package com.company.assets.assets;

import com.company.assets.exceptions.ApiRequestException;
import com.company.assets.locations.LocationRepository;
import com.company.assets.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public String invalidEntID(String entity) {
        return "This " + entity + " ID does not exist, please enter a different ID";
    }

    public List<AssetEntity> getAllAssets() {
        return assetRepository.findAll();
    }

    public AssetEntity getAssetByID(int id) {
        if (!assetRepository.existsById(id)) {
            throw new ApiRequestException(invalidEntID("asset"));
        }
        return assetRepository.findById(id).get();
    }

    public List<AssetEntity> getAssetsByDesc(String description) {
        return assetRepository.getAssetEntitiesByDescription(description);
    }

    public List<AssetEntity> getAssetsByLocationID(int locationID){
        return assetRepository.getAssetEntitiesByLocationEntity_LocationID(locationID);
    }

    public ResponseEntity<AssetEntity> createAsset(AssetEntity newAsset) {
        if (assetRepository.existsById(newAsset.getAssetID())) {
            throw new ApiRequestException("This asset ID already exists");
        }
        if (!locationRepository.existsById(newAsset.getLocationEntity().getLocationID())) {
            throw new ApiRequestException(invalidEntID("location"));
        }
        if (!userRepository.existsById(newAsset.getUserEntity().getUserID())) {
            throw new ApiRequestException(invalidEntID("user"));
        }
        else assetRepository.save(newAsset);
        return new ResponseEntity<>(newAsset, HttpStatus.CREATED);
    }

    public ResponseEntity<AssetEntity> updateAsset(AssetEntity newAsset)
    {
        if (!assetRepository.existsById(newAsset.getAssetID())) {
            throw new ApiRequestException(invalidEntID("asset"));
        }
        if (!locationRepository.existsById(newAsset.getLocationEntity().getLocationID())) {
            throw new ApiRequestException(invalidEntID("location"));
        }
        if (!userRepository.existsById(newAsset.getUserEntity().getUserID())) {
            throw new ApiRequestException(invalidEntID("user"));
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
            throw new ApiRequestException(invalidEntID("asset"));
        }
        else {
            assetRepository.delete(getAssetByID(id));
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Asset " + id + ": " + "\"" + assetDesc + "\"" + " has been deleted.");
        }
    }
}