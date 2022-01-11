package com.corp.cabinet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "rdvs")

public class Rdv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String date;
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private Doctor doctor;

    public Rdv(){
        this.patient=new Patient();
        this.doctor=new Doctor();
    }


}


