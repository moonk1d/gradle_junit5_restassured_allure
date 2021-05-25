package api;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class BreedsService extends RestService {

  private static final String PATH = "/breeds";

  @Override
  protected String getBasePath() {
    return PATH;
  }

  public Response getBreeds() {
    return given(REQ_SPEC).when().get();
  }

  public Response getBreeds(String param, Object value) {
    return given(REQ_SPEC)
        .param(param, value)
        .when().get();
  }

  public Response getBreeds(int limit, int page) {
    return given(REQ_SPEC)
        .param("limit", limit)
        .param("page", page)
        .when().get();
  }
}
