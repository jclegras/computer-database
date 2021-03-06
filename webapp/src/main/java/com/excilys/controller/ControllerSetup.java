package com.excilys.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ControllerSetup {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Optionally allows transforming an empty string into a null value
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
