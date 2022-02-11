package com.company.assets.locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private final LocationService locationservice;

    public LocationController(LocationService locationservice) {
        this.locationservice = locationservice;
    }


    @GetMapping
    public List<LocationEntity> getAll() {
        return locationservice.getAllLocations();
    }

    @GetMapping("/get/id/{id}")
    public LocationEntity getLocationByID(
            @PathVariable(value = "id") int id) {
        return locationservice.getLocationByID(id);
    }

    @GetMapping("/get/desc/{desc}")
    public List<LocationEntity> getLocationsByDesc(
            @PathVariable(value = "desc") String description) {
        return locationservice.getLocationsByDesc(description);
    }

    @PostMapping("/add")
    public ResponseEntity<LocationEntity> createLocation(
            @RequestBody LocationEntity newLocation) {
        return locationservice.createLocation(newLocation);
    }

    @PutMapping("/update")
    public ResponseEntity<LocationEntity> updateLocation(
            @RequestBody LocationEntity newLocation) {
        return locationservice.updateLocation(newLocation);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeLocation(
            @PathVariable(value = "id") int id) {
        return locationservice.removeLocation(id);
    }
}