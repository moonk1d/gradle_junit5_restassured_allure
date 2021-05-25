package tests.breeds;

import static org.assertj.core.api.Assertions.assertThat;

import api.RestWrapper;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pojo.Breed;
import tests.ConfigurationSetupTierDownHook;

@ExtendWith(ConfigurationSetupTierDownHook.class)
public class BreedsTest {

  public final RestWrapper service = new RestWrapper();

  @Test
  public void getBreeds() {
    Response response = service.breeds.getBreeds();
    List<Breed> breeds = response.getBody().jsonPath().getList("", Breed.class);

    assertThat(breeds).anyMatch(breed -> breed.getOrigin().equals("France"));
  }

  @Test
  public void getBreeds_withLimit() {
    ValidatableResponse response = service.breeds.getBreeds("limit", 10)
        .then()
        .statusCode(200);
    List<Breed> breeds = response.extract().body().jsonPath().getList("", Breed.class);

    assertThat(breeds).hasSize(10);
  }

  @Test
  public void getBreeds_withLimit_negative() {
    SoftAssertions softly = new SoftAssertions();
    int expectedStatus = 400;
    Stream.of(-1, "dummy", "", 0)
        .forEach(
            value -> {
              Response response = service.breeds.getBreeds("limit", value);
              softly.assertThat(response.getStatusCode())
                  .withFailMessage(String
                      .format("\nlimit=[%s]\nexpected status: [%s], actual status: [%s]", value,
                          expectedStatus,
                          response.getStatusCode())).isEqualTo(expectedStatus);
            });

    softly.assertAll();
  }

  @Test
  public void getBreeds_withPage() {
    service.breeds.getBreeds("page", 2)
        .then()
        .statusCode(200);
  }

  @Test
  public void getBreeds_withPageAndLimit() {
    ValidatableResponse response = service.breeds.getBreeds(1, 15)
        .then()
        .statusCode(200);
    List<Breed> breeds = response.extract().body().jsonPath().getList("$", Breed.class);

    assertThat(breeds).hasSize(1);
  }


}
