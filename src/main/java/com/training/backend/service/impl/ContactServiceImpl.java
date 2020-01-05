package com.training.backend.service.impl;

import com.training.backend.converter.ContactConverter;
import com.training.backend.entity.ContactEntity;
import com.training.backend.model.ContactModel;
import com.training.backend.repository.ContactRepository;
import com.training.backend.service.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Log LOG = LogFactory.getLog(ContactServiceImpl.class);

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactConverter contactConverter;

    @Override
    public ContactModel addContact(ContactModel contactModel) {
        LOG.info("METHOD: addContact | DATA: " + contactModel);

        ContactEntity result = contactRepository.save(contactConverter.convertModelToEntity(contactModel));
        return contactConverter.convertEntityToModel(result);
    }

    @Override
    public List<ContactModel> listAllContacts() {
        LOG.info("METHOD: listAllContacts");

        return contactConverter.convertEntitesToModels(contactRepository.findAll());
    }

    @Override
    public ContactModel findContactById(int id) {
        LOG.info("METHOD: findContactById | DATA: contactId " + id);

        ContactEntity entity = contactRepository.findById(id);
        ContactModel model = contactConverter.convertEntityToModel(entity);
        return model;
    }

    @Override
    public void removeContact(int id) {
        LOG.info("METHOD: removeContact | DATA: contactId " + id);

        ContactModel contactModel = findContactById(id);
        ContactEntity entity = contactConverter.convertModelToEntity(contactModel);
        contactRepository.delete(entity);
    }
}
