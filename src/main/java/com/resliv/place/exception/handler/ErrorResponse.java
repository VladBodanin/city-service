package com.resliv.place.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@Data
public class ErrorResponse {

  private int statusCode;
  private String message;
}
