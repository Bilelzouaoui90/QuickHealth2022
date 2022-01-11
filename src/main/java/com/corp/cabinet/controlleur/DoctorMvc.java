package com.corp.cabinet.controlleur;

import com.corp.cabinet.model.Doctor;
import com.corp.cabinet.model.Recherche;
import com.corp.cabinet.services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class DoctorMvc {

    @Autowired
    DoctorServices agent;

    //Diplay of the link : http://localhost:8082/clinique
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("index");
        return mv;

    }



    //Diplay form add doctor : http://localhost:8082/clinique/doctors/add
    @RequestMapping(value = "/doctors/add",method = RequestMethod.GET)
    public ModelAndView form_Add() {
        ModelAndView mv = new ModelAndView();
        Doctor doctor = new Doctor();
        mv.addObject(doctor);
        mv.setViewName("doctor");
        mv.addObject("Formdoctor",doctor);
        return mv;
    }
    //envoyer la requette en formdoctor
    //Ajout de la partie controle des données
    /*
    @RequestMapping(value = "/doctors/save",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("Formdoctor") Doctor d, BindingResult result) {

        if(result.hasErrors()){
            return (new ModelAndView("doctor"));
        }else{
            agent.add_doctor(d);
            return new ModelAndView("redirect:/doctors/list");
        }

    }
    */
    @RequestMapping(value = "doctors/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("Formdoctor")Doctor doctors,@RequestParam("fileImage") MultipartFile multipartFile)throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
       doctors.setImage(fileName);

       Doctor save= agent.add_doctor(doctors);
        String uploadDir = "user-photos/" + save.getId();
        Path uploadPath= Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try ( InputStream inputStream=multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
        return new ModelAndView("redirect:/doctors/list");
    }
    @RequestMapping(value = "/doctors/list",method =RequestMethod.GET)
    public ModelAndView display_docteurs(){
        ModelAndView mv=new ModelAndView();
        List<Doctor> doctors=agent.findAll();
        mv.addObject("doctors",doctors);
        mv.setViewName("doctors");
        return mv;
    }
    //Delete
    @RequestMapping(value = "/doctors/delete/{id}",method =RequestMethod.GET)
    public ModelAndView delete_doctors(@PathVariable("id")long id){
        agent.delete_doctor(id);
        return new ModelAndView("redirect:/doctors/list");
    }


    @RequestMapping(value = "/doctors/update/{id}",method = RequestMethod.GET)
            public  ModelAndView update(@PathVariable("id") long id){
        Doctor doctor=agent.find_doctor(id).get();
        ModelAndView mv=new ModelAndView();
        mv.setViewName("editdoctor");
        mv.addObject("Formdoctor",doctor);
        return mv;

    }
    @RequestMapping(value = "/doctors/recherche",method = RequestMethod.GET)
    public ModelAndView display_Serch(){
        ModelAndView mv=new ModelAndView();
        Recherche recherche=new Recherche() ;
        mv.addObject("recherche",recherche);
        mv.setViewName("recherche");
        return mv;
    }

@RequestMapping(value = "/doctors/search",method = RequestMethod.POST)
    public ModelAndView recherche(@ModelAttribute("recherche") Recherche recherche){
        List<Doctor> res=agent.recherche_sepecialité(recherche.getSpecialite());
        recherche.setDoctors(res);
        ModelAndView mv=new ModelAndView();
        mv.addObject("recherche",recherche);
        mv.setViewName("recherche");
        return mv;

}
    @RequestMapping(value = "/doctors/avancee",method = RequestMethod.GET)
    public ModelAndView display_advanced_search(){
        ModelAndView mv=new ModelAndView();
        Recherche recherche=new Recherche();

        mv.addObject("recherche",recherche);
        mv.setViewName("Advancedsearch");
        return mv;

    }
    @RequestMapping(value = "/doctors/advancedsearch",method = RequestMethod.POST)
    public ModelAndView advanced_search(@ModelAttribute("recherche") Recherche recherche){
        List<Doctor> res=agent.recherche_advanced(recherche.getSpecialite(),recherche.getPseudo());
        recherche.setDoctors(res);
        ModelAndView mv=new ModelAndView();
        mv.addObject("recherche",recherche);
        mv.setViewName("advancedsearch");
        return mv;

    }


}
