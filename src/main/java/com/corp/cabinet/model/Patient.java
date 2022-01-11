package com.corp.cabinet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String firstname;
    private  String lastname;
    private String email;
    private String phone;
    @OneToMany (mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Rdv> rdvs;

}
