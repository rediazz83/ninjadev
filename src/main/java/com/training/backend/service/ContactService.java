package com.training.backend.service;

import com.training.backend.entity.ContactEntity;
import com.training.backend.model.ContactModel;

import java.util.List;

public interface ContactService {

    ContactModel addContact(ContactModel contactModel);

    List<ContactModel> listAllContacts();

    ContactModel findContactById(long id);

    void removeContact(long id);
}
