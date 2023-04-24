import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class Tests {
    @Test
    public void geoServiceLocationTest() {
        String firstIp = "96.555.17.33";
        String secondIp = "172.637.22.77";
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location firstLocation = new Location("New York", Country.USA, null,  0);
        Location secondLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        assertThat(firstLocation.getCountry(), equalTo(geoService.byIp(firstIp).getCountry()));
        assertThat(secondLocation.getCountry(), equalTo(geoService.byIp(secondIp).getCountry()));
    }
    @Test
    public void localizationServiceTest() {
        Country firstCountry = Country.BRAZIL;
        Country secondCountry = Country.RUSSIA;
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        assertThat(localizationService.locale(firstCountry), equalTo("Welcome"));
        assertThat("Добро пожаловать", equalTo(localizationService.locale(secondCountry)));
    }
    @Test
    public void sendMessageInRussia() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.123.12.19")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Assertions.assertEquals(messageSender.send(headers), "Добро пожаловать");
    }
    @Test
    public void sendMessageInEnglish() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.32.55.32")).thenReturn(new Location("New York", Country.USA, null,  0));
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.32.55.32");
        Assertions.assertEquals(messageSender.send(headers), "Welcome");
    }

}
