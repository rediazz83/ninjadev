package com.training.backend.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Test
    public void redirectToLoginTest() {
        String result = loginController.redirectToLogin();

        assertEquals("Should return 'redirect:/login'", "redirect:/login", result);
    }

    @Test
    public void showLoginFormByDefaultTest() {
        ModelAndView result = loginController.showLoginForm(null, null);

        assertNotNull("Should return a model", result);
        assertNotNull("Should return an userCredentials object", result.getModelMap().get("userCredentials"));
        assertNull("Should return a field called 'error' with null value", result.getModelMap().get("error"));
        assertNull("Should return a field called 'logout' with null value", result.getModelMap().get("logout"));
        assertEquals("Should return view name 'login'", "login", result.getViewName());
    }

    @Test
    public void showLoginFormLogoutTest() {
        ModelAndView result = loginController.showLoginForm(null, "logout");

        assertNotNull("Should return a model", result);
        assertNotNull("Should return an userCredentials object", result.getModelMap().get("userCredentials"));
        assertNull("Should return a field called 'error' with null value", result.getModelMap().get("error"));
        assertNotNull("Should return a field called 'logout' with value", result.getModelMap().get("logout"));
    }

    @Test
    public void showLoginFormTest() {
        ModelAndView result = loginController.showLoginForm("error", null);

        assertNotNull("Should return a model", result);
        assertNotNull("Should return an userCredentials object", result.getModelMap().get("userCredentials"));
        assertNotNull("Should return a field called 'error' with value", result.getModelMap().get("error"));
        assertNull("Should return a field called 'logout' with null value", result.getModelMap().get("logout"));
    }
}
