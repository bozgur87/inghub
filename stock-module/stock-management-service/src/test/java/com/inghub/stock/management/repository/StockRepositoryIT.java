package com.inghub.stock.management.repository;

import com.inghub.stock.management.config.TestDatasourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestDatasourceConfig.class)
public class StockRepositoryIT {

  @Autowired
  private StockRepository repository;

  @Test
  void givenContainerWhenRunningInstanceThenSucceed() {
    // assertTrue(container.isRunning());
  }

}
