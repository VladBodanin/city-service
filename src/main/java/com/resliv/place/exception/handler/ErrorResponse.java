package com.resliv.place.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {

  private int statusCode;
  private String message;
}
