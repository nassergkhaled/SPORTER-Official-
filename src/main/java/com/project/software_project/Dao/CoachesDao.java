package com.project.software_project.Dao;

import com.project.software_project.Dto.LogInBody;
import com.project.software_project.Dto.ResetPasswordBody;
import com.project.software_project.Dto.UpdatePasswordBody;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.Reposorty.CoachesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CoachesDao
{
    @Autowired
    private CoachesRepo CouchReposotry;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean LoginCoachDao(String email, String password)
    {
        try {
            Optional<CoachesEntity> CouchEntity;
            CouchEntity= Optional.ofNullable(CouchReposotry.findAllByEmailAndPassword(email, password));
            if(CouchEntity.isPresent()){return Boolean.TRUE;}
            else{return Boolean.FALSE;}
        }
        catch (Exception  e)
        {
            return Boolean.FALSE;
        }
    }


    public String SignUpCoachDao(CoachesEntity Coach)
    {
        try {
            if(this.CouchReposotry.existsByEmail(Coach.getEmail())){return "Email Is Already In Use";}
            else if (this.CouchReposotry.existsByPhone(Coach.getPhone())) {return "This Phone Number Is Already in Use ";}
            this.CouchReposotry.save(Coach);
            return " Success";
        }
        catch (Exception  e)
        {
            return "Failed";
        }

    }

    public String ResetPassword(ResetPasswordBody resetPasswordBody) {
        try{
            Optional<CoachesEntity> CoachEntity;
            CoachEntity=Optional.ofNullable(this.CouchReposotry.findById(resetPasswordBody.getId())).get();
            CoachEntity.get().setPassword(resetPasswordBody.getNewPassword());
            this.CouchReposotry.save(CoachEntity.get());
            return "Success";
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }

    public String UpdatePassword(UpdatePasswordBody body) {
        try {
            Optional<CoachesEntity> CoachEntity;
            CoachEntity= Optional.ofNullable(this.CouchReposotry.findAllByEmailAndPassword(body.getEmail(), body.getOld()));
            CoachEntity.get().setPassword(body.getPassword());
            this.CouchReposotry.save(CoachEntity.get());
            return "Success";
        }
        catch (Exception  e)
        {
            return "Failed";
        }
    }

    public boolean LoginGoogleCoachDao(Object email) {
        if(this.CouchReposotry.existsByEmail(email.toString())) {
            System.out.println(email.toString());
            return Boolean.TRUE;
        }
        else {return Boolean.FALSE;}
    }

    public Integer OTP_OpertionCoach(String email) throws MessagingException, UnsupportedEncodingException {
        Integer OTP = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        CoachesEntity Coach =CouchReposotry.findAllByEmail(email);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("sporterapplication2@gmail.com", "SPORTER Support");
        helper.setTo(email);
        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
        String content = "<p>Hello " + Coach.getFullname() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP.toString() + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);

        return OTP;
    }
}
