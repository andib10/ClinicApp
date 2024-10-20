package com.licenta.MedicalClinic.controller;

import com.licenta.MedicalClinic.dao.PatientRepository;
import com.licenta.MedicalClinic.entity.Patient;
import com.licenta.MedicalClinic.entity.User;
import com.licenta.MedicalClinic.service.EmailService;
import com.licenta.MedicalClinic.service.EmailTemplateService;
import com.licenta.MedicalClinic.service.PatientService;
import com.licenta.MedicalClinic.service.UserService;
import com.licenta.MedicalClinic.user.WebUser;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;
    private PatientService patientService;
    private EntityManager entityManager;
    private PatientRepository patientRepository;
    private EmailService emailService;
    private EmailTemplateService emailTemplateService;

    @Autowired
    public RegistrationController(UserService userService, PatientService patientService, EntityManager entityManager,
                                  PatientRepository patientRepository, EmailService emailService, EmailTemplateService emailTemplateService) {
        this.userService = userService;
        this.patientService = patientService;
        this.entityManager = entityManager;
        this.patientRepository =patientRepository;
        this.emailService = emailService;
        this.emailTemplateService = emailTemplateService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("webUser", new WebUser());

        return "register/registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser theWebUser,
            BindingResult theBindingResult,
            HttpSession session, Model theModel, RedirectAttributes attributes) {

        String userName = theWebUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()){
            return "register/registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError", "Username deja existent.");
            attributes.addFlashAttribute("registrationError", "Username deja existent.");


            logger.warning("User name already exists.");
            return "register/registration-form";
        }

        // create user account and store in the databse
        User savedUser = userService.save(theWebUser);


        // send registration confirmation email
        String emailContent = emailTemplateService.buildRegistrationEmail(theWebUser);
        emailService.sendHtmlEmail(theWebUser.getEmail(), "Registration Confirmation", emailContent);


        // place user in the web http session for later use
        session.setAttribute("user", theWebUser);

        return "register/registration-confirmation";
    }

}
