package com.resliv.place.exception.handler;

public enum ErrorReason {
  INVALID_COUNTRY(1001);

  private final int errorCode;

  ErrorReason(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }
}
