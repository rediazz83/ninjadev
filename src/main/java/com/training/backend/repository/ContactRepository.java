package com.training.backend.repository;

import com.training.backend.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Serializable> {

    ContactEntity findById(long id);
}
