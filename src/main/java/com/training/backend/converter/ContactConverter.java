package com.training.backend.converter;

import com.training.backend.entity.ContactEntity;
import com.training.backend.model.ContactModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactConverter {

    private static final Log LOG = LogFactory.getLog(ContactConverter.class);

    public ContactModel convertEntityToModel(ContactEntity entity) {
        LOG.info("METHOD: convertEntityToModel | DATA: " + entity);

        return new ContactModel(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getTelephone(), entity.getCity());
    }

    public ContactEntity convertModelToEntity(ContactModel model) {
        LOG.info("METHOD: convertModelToEntity | DATA: " + model);

        return new ContactEntity(model.getId(), model.getFirstName(), model.getLastName(), model.getTelephone(), model.getCity());
    }

    public List<ContactModel> convertEntitesToModels(List<ContactEntity> entities) {
        LOG.info("METHOD: convertEntitesToModels | DATA: entites size " + entities.size());

        List<ContactModel> models = new ArrayList<>();
        for(ContactEntity entity: entities) {
            models.add(convertEntityToModel(entity));
        }

        return models;
    }

    public List<ContactEntity> convertModelsToEntities(List<ContactModel> models) {
        LOG.info("METHOD: convertModelsToEntities | DATA: models size " + models.size());

        List<ContactEntity> entities = new ArrayList<>();

        for(ContactModel model: models) {
            entities.add(convertModelToEntity(model));
        }

        return entities;
    }
}
