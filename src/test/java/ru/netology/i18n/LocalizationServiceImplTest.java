package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class LocalizationServiceImplTest {

    @Test
    public void localizationServiceTest() {
        Country firstCountry = Country.BRAZIL;
        Country secondCountry = Country.RUSSIA;
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        assertThat(localizationService.locale(firstCountry), equalTo("Welcome"));
        assertThat("Добро пожаловать", equalTo(localizationService.locale(secondCountry)));
    }
}