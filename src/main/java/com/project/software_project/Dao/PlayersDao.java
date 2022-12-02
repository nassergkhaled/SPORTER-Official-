package com.project.software_project.Dao;

import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.Reposorty.PlayersRepo;
import com.project.software_project.bodies.EditProfileBody;
import com.project.software_project.bodies.PhoneDigitsAPIBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PlayersDao {
    @Autowired
    private PlayersRepo PlayerRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    public Integer OTP_OperationPlayer(String email) throws MessagingException, UnsupportedEncodingException {
        Integer OTP = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        PlayersEntity player = PlayerRepository.findAllByEmail(email);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("sporterapplication2@gmail.com", "SPORTER Support");
        helper.setTo(email);
        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
        String content = "<p>Hello player " + player.getFullname() + "</p>"
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

    public Integer LoginPlayer(String email, String password) {
        try {
            Optional<PlayersEntity> PlayerEntity;
            PlayerEntity = Optional.ofNullable(PlayerRepository.findAllByEmailAndPassword(email, password));
            if (PlayerEntity.isPresent()) {
                return PlayerEntity.get().getId();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }


    }


    public String SignUpPlayer(PlayersEntity Player) {
         int Flag=-1;
        try {
            if (this.PlayerRepository.existsByEmail(Player.getEmail())) {
                return "Email Is Already In Use";
            } else if (this.PlayerRepository.existsByPhone(Player.getPhone())) {
                return "This Phone Number Is Already in Use ";
            }  if ((Player.getCoachid() < 101) || (Player.getCoachid() > 999)) {
                Flag++;
                Player.setCoachid(100);
                Player.setStrategy((short) 1);
            }  if (Player.getGymid()<101 || Player.getGymid()>999) {
                Flag++;
                Player.setGymid(100);
                Player.setStrategy((short) 3);
            } if (Flag==1) {
                System.out.println("I am an application player");
                Player.setStrategy((short) 2);
            }
            this.PlayerRepository.save(Player);
            return " Success";
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return "Failed";
        }

    }

    public String ResetPassword(String NewPass, String Email) {
        try {
            Optional<PlayersEntity> PlayerEntity;
            PlayerEntity = Optional.ofNullable(this.PlayerRepository.findAllByEmail(Email));
            PlayerEntity.get().setPassword(NewPass);
            this.PlayerRepository.save(PlayerEntity.get());
            return "Success";
        } catch (Exception e) {
            return "Failed";
        }
    }

    public List<PlayersEntity> viewAll() {
        return this.PlayerRepository.findAll();
    }

    public String EditInfo(EditProfileBody body) {
        try {
            Optional<PlayersEntity> Player;
            Player = Optional.ofNullable(this.PlayerRepository.findAllByEmail(body.getEmail()));
            if (Player.isEmpty()) {
                Player = Optional.ofNullable(this.PlayerRepository.findAllByPhone(body.getPhone()));
                if (Player.isPresent()) {
                    Player.get().setEmail(body.getEmail());
                    Player.get().setFullname(body.getName());
                    Player.get().setGoal(body.getGoal());
                    this.PlayerRepository.save(Player.get());
                    return "Success";
                } else {
                    return "You Can't Edit Phone & Email At The Same Time ";
                }
            } else {
                Player.get().setPhone(body.getPhone());
                Player.get().setFullname(body.getName());
                Player.get().setGoal(body.getGoal());
                this.PlayerRepository.save(Player.get());
                return "Success";
            }
        } catch (Exception e) {
            return "Failed";
        }
    }

    public PhoneDigitsAPIBody phonedigitsplayer(String email) {
        PhoneDigitsAPIBody Response = new PhoneDigitsAPIBody();
        try {
            Optional<PlayersEntity> Player;
            Player = Optional.ofNullable(this.PlayerRepository.findAllByEmail(email.toString()));
            if (Player.isPresent()) {
                Response.setFirstFour("+" + Player.get().getPhone().subSequence(0, 4).toString());
                Response.setLastTwo(Player.get().getPhone().subSequence(Player.get().getPhone().length() - 2, Player.get().getPhone().length()).toString());
            } else {
                Integer R = -1;
                Response.setFirstFour(R.toString());
                Response.setLastTwo(R.toString());
            }
            return Response;
        } catch (Exception E) {
            Integer R = -1;
            Response.setFirstFour(R.toString());
            Response.setLastTwo(R.toString());
            return Response;
        }
    }

    public List<PlayersEntity> ShowAll() {
        return this.PlayerRepository.findAll();
    }
}

