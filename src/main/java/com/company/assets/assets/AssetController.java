package com.company.assets.assets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }


    @GetMapping
    public List<AssetEntity> getAll() {
        return assetService.getAllAssets();
    }

    @GetMapping("/get/id/{id}")
    public AssetEntity getAssetByID(
            @PathVariable(value = "id") int id) {
        return assetService.getAssetByID(id);
    }

    @GetMapping("/get/desc/{desc}")
    public List<AssetEntity> getAssetsByDesc(
            @PathVariable(value = "desc") String description) {
        return assetService.getAssetsByDesc(description);
    }

    @GetMapping("/get/location/{locationID}")
    public List<AssetEntity> getAssetsByLocationID(
            @PathVariable(value = "locationID") int id) {
        return assetService.getAssetsByLocationID(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AssetEntity> createAsset(
            @RequestBody AssetEntity newAsset) {
        return assetService.createAsset(newAsset);
    }

    @PutMapping("/update")
    public ResponseEntity<AssetEntity> updateAsset(
            @RequestBody AssetEntity newAsset) {
        return assetService.updateAsset(newAsset);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeAsset(
            @PathVariable(value = "id") int id) {
        return assetService.removeAsset(id);
    }
}