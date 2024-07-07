package com.inghub.stock.contract.common.exception;

import java.io.Serializable;

/**
 * This exception is used when there is a problem with the communication between services
 */
public class ServiceException extends PlaceholderException {
  private static final String ERROR_MESSAGE = "Service (%s) failed to process";

  public ServiceException(Serializable placeholders) {
    super(ERROR_MESSAGE, placeholders);
  }

}
