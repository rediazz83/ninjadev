package com.training.backend.controller;

import com.training.backend.model.ContactModel;
import com.training.backend.service.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.training.backend.constants.ViewConstants.CONTACTS_VIEW;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    private static final Log LOG = LogFactory.getLog(ContactController.class);
    private final static String CONTACT_FORM_VIEW = "contactform";

    @GetMapping("/contact-form")
    public ModelAndView showContactForm(@RequestParam(name = "error", required = false) String error) {
        LOG.info("METHOD: showContactForm");

        ModelAndView modelAndView = new ModelAndView(CONTACT_FORM_VIEW);
        modelAndView.addObject("contactModel", new ContactModel());
        modelAndView.addObject("error", error);

        LOG.info("TEMPLATE: " + CONTACT_FORM_VIEW + " | DATA: error "
                +  modelAndView.getModel().get("error")
                + ", contactModel " + modelAndView.getModel().get("contactModel").toString());

        return modelAndView;
    }

    @GetMapping("/cancel")
    public ModelAndView cancel() {
        LOG.info("METHOD: cancel");
        return new ModelAndView("redirect:/contacts/show-contacts");
    }

    @PostMapping("/add-contact")
    public ModelAndView addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel) {
        LOG.info("METHOD: addContact | DATA: " + contactModel);

        contactService.addContact(contactModel);

        ModelAndView modelAndView = new ModelAndView("redirect:/contacts/show-contacts");
        modelAndView.addObject("result", "1");

        LOG.info("TEMPLATE: " + modelAndView.getViewName());
        return modelAndView;
    }

    @GetMapping("show-contacts")
    public ModelAndView showContacts() {
        LOG.info("METHOD: showContacts");

        ModelAndView modelAndView = new ModelAndView(CONTACTS_VIEW);

        modelAndView.addObject("allContacts", contactService.listAllContacts());

        LOG.info("TEMPLATE: " + modelAndView.getViewName());
        return modelAndView;
    }

    @GetMapping("/remove-contact")
    public ModelAndView removeContact(@RequestParam(name = "id", required = true) long id) {
        LOG.info("METHOD: removeContact | DATA: idContact " + id);

        contactService.removeContact(id);
        return showContacts();
    }
}
