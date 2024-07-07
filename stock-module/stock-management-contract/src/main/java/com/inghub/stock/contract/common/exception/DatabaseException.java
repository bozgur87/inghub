package com.inghub.stock.contract.common.exception;

import java.io.Serializable;

/**
 * Used when there is an error communicating with the database or the database is giving an
 * unexpected response
 */
public class DatabaseException extends PlaceholderException {
  public DatabaseException(String message) {
    super(message);
  }

  public DatabaseException(String message, Serializable... placeholders) {
    super(message, placeholders);
  }
}
