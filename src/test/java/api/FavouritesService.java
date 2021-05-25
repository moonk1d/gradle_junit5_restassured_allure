package api;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import requests.favourites.CreateFavouriteRequest;

public class FavouritesService extends RestService {

  private static final String PATH = "/favourites";

  @Override
  protected String getBasePath() {
    return PATH;
  }

  public ValidatableResponse getFavourites() {
    return given(REQ_SPEC).when().get().then().spec(RESP_SPEC);
  }

  public ValidatableResponse getFavourites(String subId) {
    return given(REQ_SPEC).param("sub_id", subId).when().get().then().spec(RESP_SPEC);
  }

  public ValidatableResponse getFavourites(int id) {
    return given(REQ_SPEC).when().get("/{favourite_id}", id).then().spec(RESP_SPEC);
  }

  public ValidatableResponse createFavourites(CreateFavouriteRequest request) {
    return given(REQ_SPEC).body(request).when().post().then().spec(RESP_SPEC);
  }

}
