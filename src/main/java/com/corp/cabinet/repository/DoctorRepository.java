package com.corp.cabinet.repository;

import com.corp.cabinet.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor,Long> {
    //slect *from doctors where specialité=:spec
    public List<Doctor> findBySpecialite(String spec);

    @Query(value = "Select m from Doctor m where m.specialite= :spec and (m.firstname LIKE :pseudo or m.lastname LIKE :pseudo)")

    public List<Doctor> search(@Param("spec") String spec,@Param("pseudo") String pseudo);

}
