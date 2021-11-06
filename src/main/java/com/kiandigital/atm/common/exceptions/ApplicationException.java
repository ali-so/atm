package com.kiandigital.atm.common.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    private String message;

    public ApplicationException(String message) {
        this.message = message;
    }

}
