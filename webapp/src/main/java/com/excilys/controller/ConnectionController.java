package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConnectionController {
    private static final String PAGE_403 = "403";

    @Autowired
    private MessageSource messageSource;

    //Spring Security see this :
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", messageSource.getMessage("form.login.wrong",
                    null, LocaleContextHolder.getLocale()));
        }

        if (logout != null) {
            model.addObject("msg", messageSource.getMessage("form.logout",
                    null, LocaleContextHolder.getLocale()));
        }
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return PAGE_403;
    }
}
