package com.company.assets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public AssetService() {
    }


    public List<AssetEntity> getAllAssets() {
        return assetRepository.findAll();
    }


    public AssetEntity getAsset(int id)
    {
        if (!assetRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Asset with id: " + id + "does not exists.");
        }
        return assetRepository.findById(id).get();
    }


    public ResponseEntity<AssetEntity> createAsset(
            AssetEntity newAsset)
    {
        if (assetRepository.existsById(newAsset.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else assetRepository.save(newAsset);
        return new ResponseEntity<>(newAsset, HttpStatus.CREATED);
    }


    public ResponseEntity<AssetEntity> updateAsset(
            AssetEntity newAsset)
    {
        newAsset.setDescription(newAsset.getDescription());
        newAsset.setSerialNum(newAsset.getSerialNum());
        newAsset.setPrice(newAsset.getPrice());
        newAsset.setLocation(newAsset.getLocation());
        newAsset.setSubLocation(newAsset.getSubLocation());
        final AssetEntity updatedAsset = assetRepository.save(newAsset);

        return ResponseEntity.ok(updatedAsset);
    }


    public ResponseEntity<AssetEntity> removeAsset(
            int id)
    {
        if (!assetRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else
            assetRepository.delete(getAsset(id));
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

