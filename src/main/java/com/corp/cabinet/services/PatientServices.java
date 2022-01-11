package com.corp.cabinet.services;


import com.corp.cabinet.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientServices {
    public Patient add_patient(Patient p);
    public Optional<Patient> find_patient (long id);
    public void delete_patient(long id);
    public Patient update_patient(Patient p);
    public List<Patient> findAll();
}
