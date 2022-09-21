package com.leovegas.walletmanager.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Simin
 * @created 09/09/2022 - 12:31 AM
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String s) {
        super(s);
    }
}
