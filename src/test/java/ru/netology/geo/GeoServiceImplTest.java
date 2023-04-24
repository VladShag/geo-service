package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    @Test
    public void geoServiceLocationTest() {
        String firstIp = "96.555.17.33";
        String secondIp = "172.637.22.77";
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location firstLocation = new Location("New York", Country.USA, null, 0);
        Location secondLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        assertThat(firstLocation.getCountry(), equalTo(geoService.byIp(firstIp).getCountry()));
        assertThat(secondLocation.getCountry(), equalTo(geoService.byIp(secondIp).getCountry()));
    }
}