package com.project.software_project.Dao;

import com.project.software_project.Dto.LogInBody;
import com.project.software_project.Dto.ResetPasswordBody;
import com.project.software_project.Dto.UpdatePasswordBody;
import com.project.software_project.Entity.PlayerStrategy;
import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.Reposorty.PlayersRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PlayersDao {
    @Autowired
    private PlayersRepo PlayerRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;
    public Integer OTP_OpertionPlayer(String email) throws MessagingException, UnsupportedEncodingException {
        Integer OTP = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        PlayersEntity player =PlayerRepository.findAllByEmail(email);
                    MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("sporterapplication2@gmail.com", "SPORTER Support");
            helper.setTo(email);
            String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
            String content = "<p>Hello " + player.getFullname() + "</p>"
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
    public Boolean  LoginPlayerDao (String email, String password) {
        try {
            Optional <PlayersEntity> PlayerEntity;
            PlayerEntity= Optional.ofNullable(PlayerRepository.findAllByEmailAndPassword(email, password));
            if(PlayerEntity.isPresent()){return Boolean.TRUE;}
            else{return Boolean.FALSE;}
        }
        catch (Exception  e)
        {
            return Boolean.FALSE;
        }


    }
    public String SignUpPlayerDao(PlayersEntity Player)    {
        try {
            if(this.PlayerRepository.existsByEmail(Player.getEmail())){return "Email Is Already In Use";}
            else if (this.PlayerRepository.existsByPhone(Player.getPhone())) {return "This Phone Number Is Already in Use ";}
            this.PlayerRepository.save(Player);
            return " Success";
        }
        catch (Exception  e)
        {
            System.out.println(e.toString());
            return "Failed";
        }

    }
    public String UpdatePassword(UpdatePasswordBody body) {
        try {
            Optional<PlayersEntity> PlayerEntity;
            PlayerEntity= Optional.ofNullable(PlayerRepository.findAllByEmailAndPassword(body.getEmail(), body.getOld()));
            PlayerEntity.get().setPassword(body.getPassword());
            this.PlayerRepository.save(PlayerEntity.get());
            return "Success";
        }
        catch (Exception  e)
        {
            return "Failed";
        }

    }
    public String ResetPassword(ResetPasswordBody resetPasswordBody) {
        try{
            Optional<PlayersEntity> PlayerEntity;
            PlayerEntity= Optional.ofNullable(this.PlayerRepository.findAllById(resetPasswordBody.getId()));
            PlayerEntity.get().setPassword(resetPasswordBody.getNewPassword());
            this.PlayerRepository.save(PlayerEntity.get());
            return "Success";
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }



}
