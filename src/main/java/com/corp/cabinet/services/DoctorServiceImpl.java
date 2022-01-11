package com.corp.cabinet.services;

import com.corp.cabinet.model.Doctor;
import com.corp.cabinet.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
@Service
public class DoctorServiceImpl implements DoctorServices {

    @Autowired
    DoctorRepository agent;


    @Override
    public Doctor add_doctor(Doctor d) {
        return agent.save(d);
    }

    @Override
    public Optional<Doctor> find_doctor(long id) {
        return agent.findById(id);
    }

    @Override
    public void delete_doctor(long id) {
        agent.deleteById(id);
    }

    @Override
    public Doctor update_doctor(Doctor d) {
        return agent.save(d);
    }

    @Override
    public List<Doctor> findAll() {
        return (List<Doctor> ) agent.findAll();
    }

    @Override
    public List<Doctor> recherche_sepecialité(String spec) {
        return agent.findBySpecialite(spec);
    }

    @Override
    public List<Doctor> recherche_advanced(String spec, String pseudo) {
        return agent.search(spec,"%"+pseudo+"%");
    }


}
