package com.licenta.MedicalClinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome() {

        return "homee";
    }

    @GetMapping("/homee")
    public String showHomee() {

        return "homee";
    }


    @GetMapping("/specialtiesPage")
    public String showSpecialties() {

        return "specialtiesPage";
    }

    @GetMapping("/doctorsPage")
    public String showDoctors() {

        return "doctorsPage";
    }

    @GetMapping("/aboutPage")
    public String showAboutPage() {

        return "aboutPage";
    }

    @GetMapping("/contactPage")
    public String showContactPage() {

        return "contactPage";
    }

}
