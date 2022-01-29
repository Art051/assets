package com.company.assets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AssetController {

    @Autowired
    private AssetService assetService;


    @GetMapping("/assets")
    public List<AssetEntity> getAll(){
        return assetService.getAllAssets();
    }

    @GetMapping("/assets/get/{id}")
    public AssetEntity getAsset(
            @PathVariable(value = "id") int id)
    {
        return  assetService.getAsset(id);
    }

    @PostMapping("/assets/add")
    public ResponseEntity<AssetEntity> createAsset(
            @RequestBody AssetEntity newAsset){
        return assetService.createAsset(newAsset);
    }

    @PutMapping("/assets/update")
    public ResponseEntity<AssetEntity> updateAsset(
            @RequestBody AssetEntity newAsset) {
        return assetService.updateAsset(newAsset);
    }


    @DeleteMapping("/assets/remove/{id}")
    public ResponseEntity<AssetEntity> removeAsset(
            @PathVariable(value = "id") int id) {
        return assetService.removeAsset(id);
    }



    }
