package com.company.assets.locations;

import com.company.assets.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public String invalidQuery(String entity, String field) {
        return "This " + entity + " " + field + " does not exist, please enter a valid query.";
    }


    public List<LocationEntity> getAllLocations() {
        return locationRepository.findAll();
    }

    public LocationEntity getLocationByID(int id) {
        if (!locationRepository.existsById(id)) {
            throw new ApiRequestException(invalidQuery("location","ID"));
        }
        return locationRepository.findById(id).get();
    }

    public List<LocationEntity> getLocationsByDesc(String description) {
        if (locationRepository.getLocationEntitiesByDescriptionIgnoreCase(description).isEmpty()) {
            throw new ApiRequestException(invalidQuery("asset", "description"));
        }
        else {
            return locationRepository.getLocationEntitiesByDescriptionIgnoreCase(description);
        }
    }

    public ResponseEntity<LocationEntity> createLocation(LocationEntity newLocation) {
        if (locationRepository.existsById(newLocation.getLocationID())) {
            throw new ApiRequestException("This location ID already exists");
        }
        else {
            locationRepository.save(newLocation);
            return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<LocationEntity> updateLocation(LocationEntity newLocation) {
        if (!locationRepository.existsById(newLocation.getLocationID())) {
            throw new ApiRequestException(invalidQuery("location","ID"));
        }
        else {
            newLocation.setDescription(newLocation.getDescription());
            newLocation.setDescription(newLocation.getDescription());
            final LocationEntity updatedLocation = locationRepository.save(newLocation);
            return ResponseEntity.ok(updatedLocation);
        }
    }

    public ResponseEntity<String> removeLocation(int id) {
        String locationDesc = getLocationByID(id).getDescription();
        if (!locationRepository.existsById(id)) {
            throw new ApiRequestException(invalidQuery("location","ID"));
        }
        else {
            locationRepository.delete(getLocationByID(id));
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Location " + id + ": " + "\"" + locationDesc +  "\"" + " has been deleted.");
        }
    }
}