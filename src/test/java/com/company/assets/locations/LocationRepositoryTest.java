package com.company.assets.locations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    private LocationRepository underTest;

    private final LocationEntity locationEntity = new LocationEntity("Living room");

    @BeforeEach public void addLocation(){
        underTest.save(locationEntity);
    }

    @AfterEach public void clearDB(){
        underTest.deleteAll();
    }

    @Test
    void shouldBeTrueLowercase() {

        //given
        String lowerCaseDesc = "living room";

        //when
        boolean result = underTest.existsByLocationDescription(lowerCaseDesc);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldBeTrueFirstUppercase() {

        //given
        String lowerCaseDesc = "Living room";

        //when
        boolean result = underTest.existsByLocationDescription(lowerCaseDesc);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldBeTruePascal() {

        //given
        String lowerCaseDesc = "Living Room";

        //when
        boolean result = underTest.existsByLocationDescription(lowerCaseDesc);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldBeTrueAllUppercase() {

        //given
        String lowerCaseDesc = "LIVING ROOM";

        //when
        boolean result = underTest.existsByLocationDescription(lowerCaseDesc);

        //then
        assertThat(result).isTrue();
    }
}