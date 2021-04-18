package com.resliv.place.exception;

import com.resliv.place.exception.handler.ErrorReason;

public class PlaceServiceException extends RuntimeException {

  private final ErrorReason errorReason;

  public PlaceServiceException(String message, ErrorReason errorReason) {
    super(message);
    this.errorReason = errorReason;
  }

  public ErrorReason getErrorReason() {
    return errorReason;
  }
}
