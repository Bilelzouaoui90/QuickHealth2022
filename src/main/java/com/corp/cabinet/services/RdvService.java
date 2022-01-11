package com.corp.cabinet.services;

import com.corp.cabinet.model.Rdv;

import java.util.List;
import java.util.Optional;

public interface RdvService {

    public void Add_rdv(Rdv rdv);
    public Optional<Rdv> find_rdv(long id);
    public void delete_rdv(long id);
    public Rdv  update_rdv(Rdv rdv);
    public List<Rdv> findAll();

}
