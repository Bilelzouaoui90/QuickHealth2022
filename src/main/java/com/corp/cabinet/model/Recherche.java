package com.corp.cabinet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor

public class Recherche {
    private String specialite;
    private List<Doctor> doctors;
    private String pseudo;


}
