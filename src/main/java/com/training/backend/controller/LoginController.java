package com.training.backend.controller;


import com.training.backend.model.UserCredentialsModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.training.backend.constants.ViewConstants.CONTACTS_VIEW;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);
    private static final String LOGIN_VIEW = "login";
    private static final String REDIRECT_LOGIN_VIEW = "redirect:/login";
    private static final String REDIRECT_LOGIN_ERROR_VIEW = "redirect:/login?error";

    @GetMapping("/")
    public String redirectToLogin() {
        LOG.info("METHOD: redirectToLogin");
        return REDIRECT_LOGIN_VIEW;
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm(@RequestParam(name = "error", required = false) String error,
                                      @RequestParam(name = "logout", required = false) String logout) {
        LOG.info("METHOD: showLoginForm");

        ModelAndView modelAndView = new ModelAndView(LOGIN_VIEW);
        modelAndView.addObject("userCredentials", new UserCredentialsModel());
        modelAndView.addObject("error", error);
        modelAndView.addObject("logout", logout);

        LOG.info("TEMPLATE: " + LOGIN_VIEW + " | DATA: error "
            +  modelAndView.getModel().get("error") + ", logout: " + modelAndView.getModel().get("logout")
            + ", userCredentials " + modelAndView.getModel().get("userCredentials").toString());

        return modelAndView;
    }

    @PostMapping("/logincheck")
    public String loginCheck(@ModelAttribute("userCredentials") UserCredentialsModel userCredentials) {
        LOG.info("METHOD: loginCheck");

        String view = REDIRECT_LOGIN_ERROR_VIEW;
        if(userCredentials.validateCredentials()) {
            view  = "redirect:/contacts/show-contacts";
        }

        LOG.info("TEMPLATE: " + view);
        return view;
    }
}
