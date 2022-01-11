package com.corp.cabinet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @NotEmpty
    @Size(min = 3,max = 10,message = "La taille doit etre entre 3 et caractére")
    @Pattern(regexp = "[a-zA-Z]+",message = "le prénom doit contenir que des alphabet")
    private String firstname;
    @NotEmpty
    @Size(min = 3,max = 10,message = "La taille doit etre entre 3 et caractére")
    @Pattern(regexp = "[a-zA-Z]+",message = "le prénom doit contenir que des alphabet")
    private  String lastname;
    @Email
    private String adresse;
    @NotEmpty
    @Size(min = 3,max = 10,message = "veuillez respecter le model suivant")
    private String phone;
    @NotEmpty
    private String specialite;

    private String image;
    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;

        return "/user-photos/" + id + "/" + image;
    }

    @OneToMany (mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Rdv> rdvs;

}
