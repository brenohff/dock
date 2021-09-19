package com.brenohff.dock.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus status;
    private Long timestamp;
}
