package com.company.assets.locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

    //define the query here to search the table for the description provided
    // Applied LOWER() [line 17] to the input string and the table field to avoid false returns where users have(n't) input capitals in the right place
    @Query("" + "SELECT CASE WHEN COUNT(loc) > 0 THEN " +
    "TRUE ELSE FALSE END " +
    "FROM LocationEntity loc " +
    "WHERE LOWER(loc.description) = LOWER(?1)")
    boolean existsByLocationDescription(String description);

    List<LocationEntity> getLocationEntitiesByDescriptionIgnoreCase(String description);
}
