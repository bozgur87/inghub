package com.inghub.stock.management.controller;

import com.inghub.stock.contract.model.amount.Amount;
import com.inghub.stock.contract.model.stock.StockRequest;
import com.inghub.stock.contract.model.stock.StockResponse;
import com.inghub.stock.management.config.AuthorizedUserTests;
import com.inghub.stock.management.config.TestConfig;
import com.inghub.stock.management.domain.StockMockDataProvider;
import com.inghub.stock.management.repository.StockRepository;
import com.inghub.stock.management.repository.entities.StockEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestConfig.class)
class StockControllerTest extends AuthorizedUserTests {

  @MockBean
  private StockRepository stockRepository;

  @LocalServerPort
  private Integer port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void givenEmptyRequestWhenCreateStockThenReturnBadRequest() {
    RestAssured.given()
      .contentType(ContentType.JSON)
      .when()
      .post("/api/v1/stock")
      .then()
      .statusCode(400);
  }

  @Test
  void givenCorrectRequestWhenCreateStockThenReturnCreated() {
    BDDMockito.given(stockRepository.save(any()))
            .willReturn(StockMockDataProvider.initializeStockEntity());
    StockRequest request = StockMockDataProvider.generateStockRequest();
    
    StockResponse response = RestAssured.given()
      .contentType(ContentType.JSON)
      .body(request)
      .when()
      .post("/api/v1/stock")
      .then()
      .statusCode(201)
      .extract()
      .as(StockResponse.class);
    assertResponse(response, request);
  }

  @Test
  void givenExistingDataWhenDeleteStockThenReturnNoContentWithSuccess() {
    RestAssured.given()
      .accept(ContentType.ANY)
      .when()
      .delete("/api/v1/stock/{id}", 1L)
      .then()
      .statusCode(204);
  }

  @Test
  void givenExistingDataWhenUpdateStockThenReturnSuccess() {
    StockEntity entity = StockMockDataProvider.initializeStockEntity();
    BDDMockito.given(stockRepository.findById(anyLong()))
            .willReturn(Optional.ofNullable(entity));
    entity.setPrice(BigDecimal.valueOf(100));
    BDDMockito.given(stockRepository.saveAndFlush(any()))
            .willReturn(entity);
    Amount request = StockMockDataProvider.generateAmount();
    request.setValue(100L);

    StockResponse response = RestAssured.given()
      .contentType(ContentType.JSON)
      .accept(ContentType.ANY)
      .body(request)
      .when()
      .patch("/api/v1/stock/1/amount")
      .then()
      .statusCode(200)
      .extract()
      .as(StockResponse.class);
      assertResponse(response, request);
  }

  private void assertResponse(StockResponse actual, StockRequest expected) {
    assertThat(actual).isNotNull();
    assertThat(actual.getName()).isEqualTo(expected.getName());
    assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
    assertResponse(actual, expected.getAmount());
  }

  private void assertResponse(StockResponse actual, Amount expected) {
    assertThat(actual).isNotNull();
    assertThat(actual.getAmount()).isNotNull();
    assertThat(actual.getAmount().getCurrency()).isEqualTo(expected.getCurrency());
    assertThat(actual.getAmount().getValue()).isEqualTo(expected.getValue());
    assertThat(actual.getAmount().getMinorUnit()).isEqualTo(expected.getMinorUnit());
  }
}
