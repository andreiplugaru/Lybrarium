package com.fiipradctic.Lybrarium.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class ErrorMessage {
    private Date timeStamp;
    private String message;

}
