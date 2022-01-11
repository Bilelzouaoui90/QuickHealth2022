package com.corp.cabinet.repository;

import com.corp.cabinet.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient,Long> {
}
