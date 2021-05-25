package response.specifications;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public enum RS {

  NOT_FOUND(new ResponseSpecBuilder()
      .expectStatusCode(404)
      .expectBody("message", equalTo("NOT_FOUND"))
      .build()),

  CREATED(new ResponseSpecBuilder()
      .expectStatusCode(200)
      .expectBody("message", equalTo("SUCCESS"))
      .build());

  ResponseSpecification specification;

  RS(ResponseSpecification specification) {
    this.specification = specification;
  }

  public ResponseSpecification getSpec() {
    return specification;
  }
}
