package com.company.assets.locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LocationController {

    @Autowired
    private final LocationService locationservice;

    public LocationController(LocationService locationservice) {
        this.locationservice = locationservice;
    }


    @GetMapping("/locations")
    public List<LocationEntity> getAll(){
        return locationservice.getAllLocations();
    }

    @GetMapping("/locations/get/{id}")
    public LocationEntity getLocation(
            @PathVariable(value = "id") int id)
    {
        return  locationservice.getLocation(id);
    }

    @PostMapping("/locations/add")
    public ResponseEntity<LocationEntity> createLocation(
            @RequestBody LocationEntity newLocation){
        return locationservice.createLocation(newLocation);
    }

    @PutMapping("/locations/update")
    public ResponseEntity<LocationEntity> updateLocation(
            @RequestBody LocationEntity newLocation) {
        return locationservice.updateLocation(newLocation);
    }


    @DeleteMapping("/locations/remove/{id}")
    public ResponseEntity<String> removeLocation(
            @PathVariable(value = "id") int id) {
        return locationservice.removeLocation(id);
    }



    }
