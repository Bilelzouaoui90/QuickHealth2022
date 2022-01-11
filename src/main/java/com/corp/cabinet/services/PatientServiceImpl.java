package com.corp.cabinet.services;

import com.corp.cabinet.model.Patient;
import com.corp.cabinet.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PatientServiceImpl implements PatientServices{
    @Autowired
    PatientRepository agent;

    @Override
    public Patient add_patient(Patient p) {
        return agent.save(p);
    }

    @Override
    public Optional<Patient> find_patient(long id) {
        return agent.findById(id);
    }

    @Override
    public void delete_patient(long id) {
        agent.deleteById(id);

    }

    @Override
    public Patient update_patient(Patient p) {
        return agent.save(p);
    }

    @Override
    public List<Patient> findAll() {
        return (List<Patient> )agent.findAll();
    }
}
