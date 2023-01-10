package com.project.software_project.Dao;

import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.Reposorty.CoachesRepo;
import com.project.software_project.Reposorty.GymsRepo;
import com.project.software_project.Reposorty.PlayersRepo;
import com.project.software_project.bodies.EditProfileBody;
import com.project.software_project.bodies.PhoneDigitsAPIBody;
import com.project.software_project.bodies.PlayerLogInBody;
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
    private  GymsRepo gymsRepo;
    @Autowired
    private CoachesRepo coachesRepo;
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

    public PlayerLogInBody LoginPlayer(String email, String password) {
        PlayerLogInBody response =new PlayerLogInBody();
        try {
            Optional<PlayersEntity> PlayerEntity;
            PlayerEntity = Optional.ofNullable(PlayerRepository.findAllByEmailAndPassword(email, password));
            if (PlayerEntity.isPresent()) {
                response.setId(PlayerEntity.get().getId());
                response.setGuest(PlayerEntity.get().getGuest());
            } else {
                PlayerEntity=Optional.ofNullable(PlayerRepository.findAllByEmail(email));
                if(PlayerEntity.isPresent())
                {
                 response.setGuest("Invalid Password");
                }
                else
                {
                    response.setGuest("No Such A User With The Entered Email");
                }
               response.setId(0);
            }
        } catch (Exception e) {
            response.setId(0);
        }
        return response;
    }


    public String SignUpPlayer(PlayersEntity Player) {
         int Flag=-1;
        try {
            if (this.PlayerRepository.existsByEmail(Player.getEmail())) {
                return "Email Is Already In Use";
            } else if (this.PlayerRepository.existsByPhone(Player.getPhone())) {
                return "This Phone Number Is Already in Use ";
            }
            if(Player.getGuest().equals("1")){
                //Guest
                Player.setCoachid(100);
                Player.setGymid(100);
                this.PlayerRepository.save(Player);
                return " Success + Guest ";
            }
            if ((Player.getCoachid() < 101) || (Player.getCoachid() > 999)) {
                //Gym Player
                Flag++;
                Player.setCoachid(100);
                Player.setStrategy((short) 1);
                GymsEntity newGym=gymsRepo.findAllById(Player.getGymid());
                int oldNumberOfPlayers =newGym.numberOfPlayers;
                oldNumberOfPlayers++;
                newGym.setNumberOfPlayers(oldNumberOfPlayers);
                this.gymsRepo.save(newGym);
            }  if (Player.getGymid()<101 || Player.getGymid()>999) {
                //Coach Player
                Flag++;
                Player.setGymid(100);
                Player.setStrategy((short) 3);
            } if (Flag==1) {
                //Application player
                Player.setStrategy((short) 2);
            }
            this.PlayerRepository.save(Player);
            return " Success";
        } catch (Exception e) {
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

    public String signPlayerToACoach(Integer playerId, Integer coachId) {
        try {
            Optional<PlayersEntity>player = Optional.ofNullable(this.PlayerRepository.findAllById(playerId));
            Optional<CoachesEntity>coach=Optional.ofNullable(this.coachesRepo.findAllById(coachId));
            if(player.isEmpty()){return "No Player With Given Id";}
            if(coach.isPresent())
            {
                player.get().setGuest("0");
                player.get().setStrategy((short) 3);
                player.get().setCoachid(coachId);
                this.PlayerRepository.save(player.get());
                return "Success";
            }
          else{return "No Coach With Given Id";}
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }
}

