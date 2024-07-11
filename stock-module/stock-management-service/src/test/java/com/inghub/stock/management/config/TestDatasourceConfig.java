package com.inghub.stock.management.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@TestConfiguration
//@Testcontainers
//@TestInstance(Lifecycle.PER_CLASS)
@DirtiesContext
@EnableTransactionManagement
public class TestDatasourceConfig {
  
}
