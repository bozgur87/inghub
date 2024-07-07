package com.inghub.stock.contract.common.exception;

import java.io.Serializable;

/**
 * Used when the data sent with request is invalid or when a validation check of the data fails.
 */
public class InvalidDataException extends PlaceholderException {
  public InvalidDataException(String message) {
    super(message);
  }

  public InvalidDataException(String message, Serializable... placeholders) {
    super(message, placeholders);
  }
}
