package com.project.software_project.Dao;

import com.project.software_project.bodies.FeedBackBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

//this dao is the one with no communication with ant repo
@Service
public class GeneralDao {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;


    public String SendFeedBack(String email,String name ,String text) throws MessagingException, UnsupportedEncodingException {
       try {
           MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message);
           helper.setFrom(email, name);
           helper.setTo("sporterapplication2@gmail.com");
           String subject = "FeedBack From " + name.toUpperCase();
           String content = text;
           helper.setSubject(subject);
           helper.setText(content, true);
           mailSender.send(message);
           return "sent successfully";
       }
       catch (Exception E)
       {
           return "Failed";
       }

    }
}
