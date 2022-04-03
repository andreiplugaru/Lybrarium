package com.fiipradctic.Lybrarium.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiException {
    private  String message;
    private  HttpStatus httpStatus;
    private  ZonedDateTime time;
}
