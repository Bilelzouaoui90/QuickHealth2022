package com.corp.cabinet.services;

import com.corp.cabinet.model.Doctor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DoctorServices {
    public Doctor add_doctor(Doctor d);

    public Optional<Doctor> find_doctor (long id);
    public void delete_doctor(long id);
    public Doctor update_doctor(Doctor d);
    public List<Doctor> findAll();
    public List<Doctor> recherche_sepecialité(String spec);
    public List<Doctor> recherche_advanced(String spec,String pseudo);

}
