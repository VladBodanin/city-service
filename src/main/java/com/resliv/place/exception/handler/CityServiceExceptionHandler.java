package com.resliv.place.exception.handler;

import com.resliv.place.exception.EntityAlreadyPresentException;
import com.resliv.place.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class CityServiceExceptionHandler {

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex) {
    log.error(ex.getMessage(), ex);
    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {

    log.error(ex.getMessage(), ex);
    return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "This method is not allowed");
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(NotFoundException ex) {
    log.error(ex.getMessage(), ex);

    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(EntityAlreadyPresentException.class)
  public ResponseEntity<ErrorResponse> handleEntityAlreadyPresentException(
      EntityAlreadyPresentException ex) {
    log.error(ex.getMessage(), ex);

    return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
  }

  private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);

    return new ResponseEntity<>(errorResponse, httpStatus);
  }
}
