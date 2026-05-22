package fr.lil4s;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CitySearchTest {

    @Mock
    private CityRepository cityRepository;

    private CitySearch citySearch;

    @BeforeEach
    public void setUp() {
        citySearch = new CitySearch(cityRepository);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenSearchCriteriaIsLessThan2CharactersButNotWildCard(){
        String searchString = "a";

        assertThatThrownBy(
            citySearch.search(searchString)
        )
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Search criteria should have 2 characters or more.");
    }

    @Test
    void shouldReturnCitiesStartingWithSearchCriteria(){
        List<String> cities = List.of("Vancouver", "Varsovie", "Valence", "Paris", "Lille");
        when(cityRepository.getCities()).thenReturn(cities);

        String searchCriteria = "Va";
        List<String> expectedCities = List.of("Vancouver", "Valence", "Varsovie");

        List<String> actualCities = citySearch.search(searchCriteria);

        assertThat(actualCities).isEqualTo(expectedCities);
    }

    @Test
    void shouldReturnCitiesStartingWithSearchCriteriaInUppercase(){
        List<String> cities = List.of("Vancouver", "Varsovie", "Valence", "Paris", "Lille");
        when(cityRepository.getCities()).thenReturn(cities);

        String searchCriteria = "VA";
        List<String> expectedCities = List.of("Vancouver", "Valence", "Varsovie");

        List<String> actualCities = citySearch.search(searchCriteria);

        assertThat(actualCities).isEqualTo(expectedCities);
    }

    @Test
    void shouldReturnCitiesContainingSearchCriteria(){
        List<String> cities = List.of("Vancouver", "Varsovie", "Valence", "Paris", "Lille");
        when(cityRepository.getCities()).thenReturn(cities);

        String searchCriteria = "so";
        List<String> expectedCities = List.of("Varsovie");

        List<String> actualCities = citySearch.search(searchCriteria);

        assertThat(actualCities).isEqualTo(expectedCities);
    }

    @Test
    void shouldReturnCitiesWhenSearchCriteriaIsWildCard(){
        List<String> cities = List.of("Vancouver", "Varsovie", "Valence", "Paris", "Lille");
        when(cityRepository.getCities()).thenReturn(cities);

        String searchCriteria = "*";
        List<String> expectedCities = List.of("Vancouver", "Varsovie", "Valence", "Paris", "Lille");

        List<String> actualCities = citySearch.search(searchCriteria);

        assertThat(actualCities).isEqualTo(expectedCities);
    }
}
