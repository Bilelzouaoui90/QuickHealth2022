package com.corp.cabinet.controlleur;

import com.corp.cabinet.model.Doctor;
import com.corp.cabinet.model.Patient;
import com.corp.cabinet.model.Rdv;
import com.corp.cabinet.services.DoctorServices;
import com.corp.cabinet.services.PatientServices;
import com.corp.cabinet.services.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/rdvsContr")
public class RdvMvc {

    @Autowired
    RdvService agent;
    @Autowired
    RdvService agentrdv;

    @Autowired
    DoctorServices agentdoc;

    @Autowired
    PatientServices agentpat;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView display_form(){
        Rdv rdv=new Rdv();
        List<Doctor> doctors=agentdoc.findAll();
      List<Patient> patients= agentpat.findAll();
      ModelAndView mv=new ModelAndView();
        mv.setViewName("rdv");
        mv.addObject("FormRdv",rdv);
        mv.addObject("doctors",doctors);
        mv.addObject("patients",patients);
        return mv;

    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("FormRdv") Rdv rdv){
        agentrdv.Add_rdv(rdv);
        return  new ModelAndView("redirect:/rdvsContr/list");
    }

    @RequestMapping(value = "/list",method =RequestMethod.GET)
    public ModelAndView display_rdvs(){
        ModelAndView mv=new ModelAndView();
        List<Rdv> rdvs= agent.findAll();
        mv.addObject("rdvs",rdvs);
        mv.setViewName("rdvss");
        return mv;
    }
    @RequestMapping(value = "/delete/{id}",method =RequestMethod.GET)
    public ModelAndView delete_rdv(@PathVariable("id")long id){
        agent.delete_rdv(id);
        return new ModelAndView("redirect:/rdvsContr/list");
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public  ModelAndView update(@PathVariable("id") long id){
        List<Doctor> doctors=agentdoc.findAll();
        List<Patient> patients= agentpat.findAll();
        Rdv rdv=agent.find_rdv(id).get();
        ModelAndView mv=new ModelAndView();
        mv.setViewName("editRdv");
        mv.addObject("FormRdv",rdv);
        mv.addObject("doctors",doctors);
        mv.addObject("patients",patients);

        return mv;

    }



}
