package api;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import java.util.Map;

public class ImagesSearchService extends RestService {

  private static final String PATH = "/images/search";

  @Override
  protected String getBasePath() {
    return PATH;
  }

  public ValidatableResponse search() {
    return given(REQ_SPEC)
        .when().get()
        .then().spec(RESP_SPEC);
  }

  public ValidatableResponse search(String param, Object value) {
    return given(REQ_SPEC)
        .param(param, value)
        .when().get()
        .then().spec(RESP_SPEC);
  }

  public ValidatableResponse search(String format) {
    return given(REQ_SPEC)
        .param("format", format)
        .when().get()
        .then();
  }

  public ValidatableResponse search(Map<String, Object> params) {
    return given(REQ_SPEC)
        .params(params)
        .when().get()
        .then().spec(RESP_SPEC);
  }

}
