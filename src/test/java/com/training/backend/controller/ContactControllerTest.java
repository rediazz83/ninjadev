package com.training.backend.controller;

import com.training.backend.model.ContactModel;
import com.training.backend.service.impl.ContactServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactServiceImpl contactService;

    @Test
    public void shouldReturnModelToContactForm() {
        ModelAndView result = contactController.showContactForm("", null);

        assertNotNull("Should return a model and view object", result);
    }

    @Test
    public void shouldReturnContactView() {
        ModelAndView result = contactController.showContactForm("", null);

        assertEquals("Should return a view called 'contactForm'", "contactform", result.getViewName());
    }

    @Test
    public void shouldReturnContainsContactModelField() {
        ModelAndView result = contactController.showContactForm("", null);

        assertTrue("Should return contains contact model field", result.getModel().containsKey("contactModel"));
    }

    @Test
    public void shouldReturnContainsContactModelInitialized() {
        ModelAndView result = contactController.showContactForm("", null);

        assertNotNull("Should return contains contact model field initialized", result.getModel().get("contactModel"));
    }

    @Test
    public void shouldReturnContainsErrorField() {
        ModelAndView result = contactController.showContactForm("", null);

        assertTrue("Should return contains error field", result.getModel().containsKey("error"));
    }

    @Test
    public void shouldReturnContainsErrorFieldWithValue() {
        ModelAndView result = contactController.showContactForm("", null);

        assertNotNull("Should return contains error field", result.getModel().get("error"));
    }

    @Test
    public void shouldReturnModelToCancelContactForm() {
        ModelAndView result = contactController.cancel();

        assertNotNull("Should return a model and view object", result);
    }

    @Test
    public void shouldReturnContactsViewToCancelAction() {
        ModelAndView result = contactController.cancel();

        assertEquals("Should return a view called '\"redirect:/contacts/show-contacts\"'", "redirect:/contacts/show-contacts", result.getViewName());
    }

    @Test
    public void shouldReturnModelToAddContact() {
        ContactModel contactModel = new ContactModel();
        ModelAndView result = contactController.addContact(contactModel);

        assertNotNull("Should return model to add contact", result);
    }

    @Test
    public void shouldReturnViewToAddContact() {
        ContactModel contactModel = new ContactModel();
        ModelAndView result = contactController.addContact(contactModel);

        assertEquals("Should return view to add contact", "redirect:/contacts/show-contacts", result.getViewName());
    }

    @Test
    public void shouldCallToAddContactMethod() {
        ContactModel contactModel = mock(ContactModel.class);
        contactController.addContact(contactModel);

        verify(this.contactService, times(1)).addContact(contactModel);
    }

    @Test
    public void shouldReturnContainsResultField() {
        ContactModel contactModel = mock(ContactModel.class);
        ModelAndView result = contactController.addContact(contactModel);

        assertTrue("Should return contains result field", result.getModel().containsKey("result"));
    }

    @Test
    public void shouldReturnContainsResultFieldWithValue() {
        ContactModel contactModel = mock(ContactModel.class);
        ModelAndView result = contactController.addContact(contactModel);

        assertNotNull("Should return contains result field", result.getModel().get("result"));
    }

    @Test
    public void shouldReturnModelAndViewForShowContacts() {
        ModelAndView result = contactController.showContacts();

        assertNotNull("Should return ModelAndView for showContacts", result);
    }

    @Test
    public void shouldCallAllContactsFromServiceLabel() {
        contactController.showContacts();

        verify(contactService, times(1)).listAllContacts();
    }

    @Test
    public void shouldReturnViewToContacts() {
        ModelAndView result = contactController.showContacts();

        assertEquals("Should return view to contacts", "contacts", result.getViewName());
    }

    @Test
    public void shouldReturnAllContactsField() {
        ModelAndView result = contactController.showContacts();

        assertTrue("Should return allContacts field", result.getModel().containsKey("allContacts"));
    }

    @Test
    public void shouldReturnAllContactsNotEmpty() {
        ContactModel model = new ContactModel(1, "Rodrigo", "Diaz", "+56912233455", "SCL");
        List<ContactModel> models = new ArrayList<>();
        models.add(model);
        when(contactService.listAllContacts()).thenReturn(models);
        ModelAndView modelAndView = contactController.showContacts();

        List<ContactModel> result = (List<ContactModel>) modelAndView.getModelMap().getAttribute("allContacts");

        assertEquals("Should return allContacts field not empty", 1, result.size());
    }

    @Test
    public void shouldReturnModelWhenCallToRemoveContactMethod() {
        int id = 1;
        ModelAndView result = contactController.removeContact(id);

        assertNotNull("Should return model when call to removeContact method", result);
    }
}
