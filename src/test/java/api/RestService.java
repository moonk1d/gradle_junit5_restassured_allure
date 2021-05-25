package api;

import api.configuration.AppConfiguration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public abstract class RestService {

  private static final String API_KEY = "636b6451-612d-4c57-8622-166eddd650ab";
  private static final String BASE_URI = AppConfiguration.getEnvironment().getUrl();
  private static final String VERSION = "/v1";
  protected RequestSpecification REQ_SPEC;
  protected ResponseSpecification RESP_SPEC;

  public RestService() {
    this.REQ_SPEC = new RequestSpecBuilder()
        .setBaseUri(BASE_URI + VERSION)
        .addHeader("x-api-key", API_KEY)
        .setBasePath(getBasePath())
        .addFilter(new RequestLoggingFilter())
        .addFilter(new ResponseLoggingFilter())
        .addFilter(new AllureRestAssured())
        .setContentType(ContentType.JSON)
        .build();

    this.RESP_SPEC = new ResponseSpecBuilder()
        .expectContentType(ContentType.JSON)
        .build();
  }

  protected abstract String getBasePath();

}
