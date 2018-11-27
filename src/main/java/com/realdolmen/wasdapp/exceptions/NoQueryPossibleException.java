/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp.exceptions;

/**
 *
 * @author demun
 */
public class NoQueryPossibleException extends Exception{
    private static final String MESSAGE = "U naam mag NIET leeg zijn."; 
    public NoQueryPossibleException() {
    }

    public NoQueryPossibleException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return MESSAGE; //To change body of generated methods, choose Tools | Templates.
    }
}
