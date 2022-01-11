package com.corp.cabinet.controlleur;


import com.corp.cabinet.model.Doctor;
import com.corp.cabinet.model.Patient;

import com.corp.cabinet.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PatientMvc {

    @Autowired
    PatientServices agent;
/*
    //Diplay of the link : http://localhost:8082/clinique
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("index");
        return mv;

    }

 */


    @RequestMapping(value = "/patients/add",method = RequestMethod.GET)
    public ModelAndView form_Add() {
        ModelAndView mv = new ModelAndView();
        Patient patient = new Patient();
        mv.addObject(patient);
        mv.setViewName("patient");
        mv.addObject("Formpatient",patient);
        return mv;
    }

    //envoyer la requette en formdoctor
    @RequestMapping(value = "/patients/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("Formpatient") Patient p) {
        agent.add_patient(p);
        return new ModelAndView("redirect:/patients/list");
    }

    @RequestMapping(value = "/patients/list",method =RequestMethod.GET)
    public ModelAndView display_patients(){
        ModelAndView mv=new ModelAndView();
        List<Patient> patients=agent.findAll();
        mv.addObject("patients",patients);
        mv.setViewName("patients");
        return mv;
    }
    @RequestMapping(value = "/patients/delete/{id}",method =RequestMethod.GET)
    public ModelAndView delete_patients(@PathVariable("id")long id){
        agent.delete_patient(id);
        return new ModelAndView("redirect:/patients/list");
    }
    @RequestMapping(value = "/patients/update/{id}",method = RequestMethod.GET)
    public  ModelAndView update(@PathVariable("id") long id){
       Patient patient=agent.find_patient(id).get();
        ModelAndView mv=new ModelAndView();
        mv.setViewName("editpatient");
        mv.addObject("Formpatient",patient);
        return mv;
    }

}
