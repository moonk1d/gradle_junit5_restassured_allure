package tests.favourites;

import static org.assertj.core.api.Assertions.assertThat;

import api.RestWrapper;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pojo.Favourite;
import requests.favourites.CreateFavouriteRequest;
import response.ErrorResponseBody;
import response.specifications.RS;
import tests.ConfigurationSetupTierDownHook;

@ExtendWith(ConfigurationSetupTierDownHook.class)
public class FavouritesTest {

  public final RestWrapper service = new RestWrapper();

  @Test
  @DisplayName("Get favourites list")
  @Description("Some detailed test description")
  public void getFavouritesList() {
    ValidatableResponse response = service.favourites.getFavourites()
        .statusCode(200);
    assertThat(response.extract().jsonPath()
        .getList("$", Favourite.class))
        .allMatch(favourite -> favourite.getId() != null);
  }

  @Test
  public void getFavouritesListWithSubId() {
    String subId = "anv";
    ValidatableResponse response = service.favourites
        .getFavourites(subId)
        .statusCode(200);

    assertThat(response.extract().jsonPath()
        .getList("$", Favourite.class))
        .allMatch(favourite -> favourite.getSubId().equals(subId));
  }

  @Test
  public void getFavouritesListWithSubId_emptyList() {
    String subId = "dummySubId";
    ValidatableResponse response = service.favourites
        .getFavourites(subId)
        .statusCode(200);

    assertThat(response.extract().jsonPath()
        .getList("$", Favourite.class).isEmpty()).isTrue();
  }

  @Test
  public void getFavouritesById() {
    int id = 2067037;
    ValidatableResponse response = service.favourites
        .getFavourites(id)
        .statusCode(200);

    assertThat(response.extract().as(Favourite.class).getId()).isEqualTo(id);
  }

  @Test
  public void getFavouritesById_notFound() {
    int id = 123456;

    ValidatableResponse response = service.favourites
        .getFavourites(id)
        .statusCode(404);

    assertThat(response.extract().as(ErrorResponseBody.class).getMessage()).isEqualTo("NOT_FOUND");
  }

  @Test
  public void getFavouritesById_notFound_usingResponseSpec() {
    int id = 123456;

    service.favourites
        .getFavourites(id)
        .spec(RS.NOT_FOUND.getSpec());
  }

  @Test
  public void createFavourite() {
    CreateFavouriteRequest request = CreateFavouriteRequest.builder()
        .imageId("3KG57GfMW")
        .build();
    ValidatableResponse response = service.favourites
        .createFavourites(request)
        .statusCode(200);

    assertThat(response.extract().jsonPath().getString("message")).isEqualTo("SUCCESS");
  }

  @Test
  public void createFavourite_usingResponseSpec() {
    CreateFavouriteRequest request = CreateFavouriteRequest.builder()
        .imageId("3KG57GfMW")
        .build();
    service.favourites
        .createFavourites(request)
        .spec(RS.CREATED.getSpec());
  }


}
