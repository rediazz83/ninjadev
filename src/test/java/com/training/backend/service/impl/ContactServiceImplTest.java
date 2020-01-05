package com.training.backend.service.impl;

import com.training.backend.converter.ContactConverter;
import com.training.backend.entity.ContactEntity;
import com.training.backend.model.ContactModel;
import com.training.backend.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactConverter contactConverter;

    private ContactModel model;
    private List<ContactModel> models = new ArrayList<>();
    private ContactEntity entity;
    private List<ContactEntity> entities = new ArrayList<>();

    @Before
    public void setUp() {
        model = new ContactModel(1,"Rodrigo", "Diaz", "+56912233455", "SCL");
        models.add(model);

        entity = new ContactEntity(1, "Rodrigo", "Diaz", "+56912233455", "SCL");
        entities.add(entity);
    }

    @Test
    public void shouldReturnAnContactEntity() {
        when(contactConverter.convertModelToEntity(any(ContactModel.class))).thenReturn(entity);
        when(contactRepository.save(any(ContactEntity.class))).thenReturn(entity);
        when(contactConverter.convertEntityToModel(any(ContactEntity.class))).thenReturn(model);

        ContactModel result = contactService.addContact(model);

        assertNotNull("Should return an ContactModel", result);
    }

    @Test
    public void shouldReturnListOfContacts() {
        List<ContactModel> result = contactService.listAllContacts();

        assertNotNull("Should return a list of contacts", result);
    }

    @Test
    public void shouldReturnListOfContactsNotEmpty() {
        when(contactRepository.findAll()).thenReturn(entities);
        when(contactConverter.convertEntitesToModels(any())).thenReturn(models);
        List<ContactModel> result = contactService.listAllContacts();

        verify(contactRepository, times(1)).findAll();
        assertFalse("Should return list of contacts not empty", result.isEmpty());
    }

    @Test
    public void shouldCallToRepositoryLabelForFindContactById() {
        int id = 1;
        contactService.findContactById(id);

        verify(contactRepository, times(1)).findById(id);
    }

    @Test
    public void shouldReturnAnyContactForFindContactById() {
        int id = 1;
        when(contactRepository.findById(id)).thenReturn(entity);
        when(contactConverter.convertEntityToModel(any(ContactEntity.class))).thenReturn(model);

        ContactModel result = contactService.findContactById(id);

        assertEquals("Should return same first name", "Rodrigo", result.getFirstName());
        assertEquals("Should return same last name", "Diaz", result.getLastName());
        assertEquals("Should return same telephone", "+56912233455", result.getTelephone());
        assertEquals("Should return same city", "SCL", result.getCity());
    }

    @Test
    public void shouldCallFindContactByIdForRemoveContactMethod() {
        int id = 1;
        contactService.removeContact(id);

        verify(contactRepository, times(1)).findById(id);
    }

    @Test
    public void shouldCallDeleteForRemoveContactMethod() {
        int id = 1;
        when(contactRepository.findById(id)).thenReturn(entity);
        when(contactConverter.convertEntityToModel(any(ContactEntity.class))).thenReturn(model);
        when(contactConverter.convertModelToEntity(any(ContactModel.class))).thenReturn(entity);
        contactService.removeContact(id);

        verify(contactRepository, times(1)).findById(id);
        verify(contactRepository, times(1)).delete(entity);
    }
}
