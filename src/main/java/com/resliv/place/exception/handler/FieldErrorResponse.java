package com.resliv.place.exception.handler;

import java.util.List;

public class FieldErrorResponse extends ErrorResponse {

  private final List<FieldError> fieldErrors;

  FieldErrorResponse(int code, String message, List<FieldError> fieldErrors) {
    super(code, message);
    this.fieldErrors = fieldErrors;
  }

  public List<FieldError> getFieldErrors() {
    return fieldErrors;
  }

  static class FieldError {
    private final String field;
    private final String message;

    FieldError(String field, String message) {
      this.field = field;
      this.message = message;
    }

    public String getField() {
      return field;
    }

    public String getMessage() {
      return message;
    }
  }
}
