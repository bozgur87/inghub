package com.inghub.stock.contract.common.exception;

import java.io.Serializable;

public abstract class PlaceholderException extends RuntimeException {
  private final Serializable[] placeholders;

  public PlaceholderException(String message) {
    super(message);
    this.placeholders = null;
  }

  public PlaceholderException(String message, Serializable... placeholders) {
    super(message);
    this.placeholders = placeholders;
  }

  public Serializable[] getPlaceholders() {
    return placeholders;
  }

}
