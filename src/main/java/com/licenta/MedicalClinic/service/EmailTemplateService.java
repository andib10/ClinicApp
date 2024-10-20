package com.licenta.MedicalClinic.service;

import com.licenta.MedicalClinic.entity.Doctor;
import com.licenta.MedicalClinic.entity.Patient;
import com.licenta.MedicalClinic.entity.User;
import com.licenta.MedicalClinic.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailTemplateService {

    @Autowired
    private TemplateEngine templateEngine;

    public String buildAppointmentConfirmationEmail(Patient patient, Doctor doctor, LocalDateTime slotStart) {
        Context context = new Context();
        context.setVariable("firstName", patient.getUser().getFirstName());
        context.setVariable("specialty", doctor.getSpecialty().getName());
        context.setVariable("doctorFirstName", doctor.getUser().getFirstName());
        context.setVariable("doctorLastName", doctor.getUser().getLastName());
        context.setVariable("time", slotStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        return templateEngine.process("email/appointment-confirmation", context);
    }

    public String buildAppointmentNotificationForDoctor(Doctor doctor, Patient patient, LocalDateTime slotStart) {
        Context context = new Context();
        context.setVariable("doctorFirstName", doctor.getUser().getFirstName());
        context.setVariable("doctorLastName", doctor.getUser().getLastName());
        context.setVariable("patientFirstName", patient.getUser().getFirstName());
        context.setVariable("patientLastName", patient.getUser().getLastName());
        context.setVariable("specialty", doctor.getSpecialty().getName());
        context.setVariable("time", slotStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        return templateEngine.process("email/appointment-notification-doctor", context);
    }

    public String buildAppointmentCancellationEmailForPatient(Patient patient, Doctor doctor, LocalDateTime slotStart) {
        Context context = new Context();
        context.setVariable("firstName", patient.getUser().getFirstName());
        context.setVariable("specialty", doctor.getSpecialty().getName());
        context.setVariable("doctorFirstName", doctor.getUser().getFirstName());
        context.setVariable("doctorLastName", doctor.getUser().getLastName());
        context.setVariable("time", slotStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return templateEngine.process("email/appointment-cancellation-patient", context);
    }

    public String buildAppointmentCancellationEmailForDoctor(Doctor doctor, Patient patient, LocalDateTime slotStart) {
        Context context = new Context();
        context.setVariable("doctorFirstName", doctor.getUser().getFirstName());
        context.setVariable("doctorLastName", doctor.getUser().getLastName());
        context.setVariable("patientFirstName", patient.getUser().getFirstName());
        context.setVariable("patientLastName", patient.getUser().getLastName());
        context.setVariable("specialty", doctor.getSpecialty().getName());
        context.setVariable("time", slotStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return templateEngine.process("email/appointment-cancellation-doctor", context);
    }

    public String buildRegistrationEmail(WebUser webUser) {
        Context context = new Context();
        context.setVariable("userName", webUser.getUserName());
        context.setVariable("firstName", webUser.getFirstName());
        context.setVariable("lastName", webUser.getLastName());
        context.setVariable("email", webUser.getEmail());
        context.setVariable("phoneNumber", webUser.getPhoneNumber());
        context.setVariable("birthDate", webUser.getBirthDate());
        context.setVariable("CNP", webUser.getCNP());

        return templateEngine.process("email/registration-confirmation-email", context);
    }

    public String buildAdminRegistrationEmail(User user) {
        Context context = new Context();
        context.setVariable("userName", user.getUserName());
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("email", user.getEmail());

        return templateEngine.process("email/admin-account-creation-email", context);
    }

    public String buildDoctorRegistrationEmail(User user) {
        Context context = new Context();
        context.setVariable("userName", user.getUserName());
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("email", user.getEmail());

        return templateEngine.process("email/doctor-account-creation-email", context);
    }
}
