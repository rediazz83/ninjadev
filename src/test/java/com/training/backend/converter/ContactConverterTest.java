package com.training.backend.converter;

import com.training.backend.entity.ContactEntity;
import com.training.backend.model.ContactModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ContactConverterTest {

    private ContactConverter contactConverter;

    @Before
    public void setUp() {
        contactConverter = new ContactConverter();
    }

    @Test
    public void shouldReturnModelFromEntity() {
        ContactEntity entity = mock(ContactEntity.class);
        ContactModel result = contactConverter.convertEntityToModel(entity);

        assertNotNull("Should return model from entity", result);
    }

    @Test
    public void shouldReturnModelListFromEntityList() {
        List<ContactEntity> entities = new ArrayList<>();
        entities.add(mock(ContactEntity.class));
        List<ContactModel> result = contactConverter.convertEntitesToModels(entities);

        assertNotNull("Should return models from entities", result);
    }

    @Test
    public void shouldReturnModelFromEntityWithSameValues(){
        ContactEntity entity = new ContactEntity(1, "Rodrigo", "Diaz", "+56912233455", "SCL");
        ContactModel result = contactConverter.convertEntityToModel(entity);

        assertEquals("Should return same first name", "Rodrigo", result.getFirstName());
        assertEquals("Should return same last name", "Diaz", result.getLastName());
        assertEquals("Should return same telephone", "+56912233455", result.getTelephone());
        assertEquals("Should return same city", "SCL", result.getCity());
    }


    @Test
    public void shouldReturnModelListFromEntityListWithSameValues() {
        List<ContactEntity> entities = new ArrayList<>();
        entities.add(new ContactEntity(1, "Rodrigo", "Diaz", "+56912233455", "SCL"));
        List<ContactModel> result = contactConverter.convertEntitesToModels(entities);

        assertEquals("Should return same first name", "Rodrigo", result.get(0).getFirstName());
        assertEquals("Should return same last name", "Diaz", result.get(0).getLastName());
        assertEquals("Should return same telephone", "+56912233455", result.get(0).getTelephone());
        assertEquals("Should return same city", "SCL", result.get(0).getCity());
    }

    @Test
    public void shouldReturnEntityFromModel() {
        ContactModel model = mock(ContactModel.class);
        ContactEntity result = contactConverter.convertModelToEntity(model);

        assertNotNull("Should return entity from model", result);
    }

    @Test
    public void shouldReturnEntityLisFromModelList() {
        List<ContactModel> models = new ArrayList<>();
        models.add(mock(ContactModel.class));
        List<ContactEntity> result = contactConverter.convertModelsToEntities(models);

        assertNotNull("Should return entities from models", result);
    }

    @Test
    public void shouldReturnEntityLisFromModelListSameValues() {
        List<ContactModel> models = new ArrayList<>();
        models.add(new ContactModel(1, "Rodrigo", "Diaz", "+56912233455", "SCL"));
        List<ContactEntity> result = contactConverter.convertModelsToEntities(models);

        assertEquals("Should return same first name", "Rodrigo", result.get(0).getFirstName());
        assertEquals("Should return same last name", "Diaz", result.get(0).getLastName());
        assertEquals("Should return same telephone", "+56912233455", result.get(0).getTelephone());
        assertEquals("Should return same city", "SCL", result.get(0).getCity());
    }

    @Test
    public void shouldReturnEntityFromModelWithSameValues(){
        ContactModel model = new ContactModel(1, "Rodrigo", "Diaz", "+56912233455", "SCL");
        ContactEntity result = contactConverter.convertModelToEntity(model);

        assertEquals("Should return same first name", "Rodrigo", result.getFirstName());
        assertEquals("Should return same last name", "Diaz", result.getLastName());
        assertEquals("Should return same telephone", "+56912233455", result.getTelephone());
        assertEquals("Should return same city", "SCL", result.getCity());
    }
}
