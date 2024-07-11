package com.inghub.stock.management.handler;

import com.inghub.stock.contract.common.exception.DatabaseException;
import com.inghub.stock.contract.common.exception.PlaceholderException;
import com.inghub.stock.contract.error.ApiError;
import com.inghub.stock.contract.error.InvalidParams;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import static java.util.Objects.nonNull;

@RestControllerAdvice
public class BaseControllerAdvice {

  @ExceptionHandler({Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handleIntervalServerError(Exception ex, WebRequest request) {
    final List<InvalidParams> params = new ArrayList<InvalidParams>();
    params.add(new InvalidParams(ex.getClass().getName(), ex.getMessage()));
    return new ApiError(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
            "Unexpected Error", params);
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiError handleMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
    final List<InvalidParams> params = new ArrayList<InvalidParams>();
    params.add(new InvalidParams(ex.getClass().getName(), ex.getMessage()));
    return new ApiError(Integer.toString(HttpStatus.BAD_REQUEST.value()), 
            HttpStatus.BAD_REQUEST.getReasonPhrase(), params);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiError handleMethodArgumentTypeMismatch(MethodArgumentNotValidException ex, WebRequest request) {
    final List<InvalidParams> params = new ArrayList<InvalidParams>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      params.add(new InvalidParams(error.getField(), error.getDefaultMessage()));
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      params.add(new InvalidParams(error.getObjectName(), error.getDefaultMessage()));
    }

    return new ApiError(Integer.toString(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.getReasonPhrase(),
            params);
  }

  @ExceptionHandler({DatabaseException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handleMethodArgumentTypeMismatch(DatabaseException ex, WebRequest request) {
    final List<InvalidParams> params = new ArrayList<InvalidParams>();
    params.add(new InvalidParams(ex.getClass().getName(), getMessage(ex)));
    return new ApiError(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), params);
  }

  private String getMessage(PlaceholderException ex) {
    Serializable[] placeholders = ex.getPlaceholders();
    if (nonNull(placeholders)) {
      return String.format(ex.getMessage(), placeholders);
    }
    return ex.getMessage();
  }

}
