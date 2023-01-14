package com.project.software_project.Dao;

import com.project.software_project.Dto.CoachesDto;
import com.project.software_project.Dto.PlayersDto;
import com.project.software_project.bodies.EditProfileBody;
import com.project.software_project.bodies.PhoneDigitsAPIBody;
import com.project.software_project.bodies.StringBody;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Reposorty.CoachesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CoachesDao {
    @Autowired
    private CoachesRepo couchReposotry;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    public Integer LoginCoach(String email, String password) {
        try {
            Optional<CoachesEntity> CoachEntity;
            CoachEntity = Optional.ofNullable(couchReposotry.findAllByEmailAndPassword(email, password));
            if (CoachEntity.isPresent()) {
                return CoachEntity.get().getId();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public String SignUpCoach(CoachesEntity Coach) {
        try {
            if (this.couchReposotry.existsByEmail(Coach.getEmail())) {
                return "Email Is Already In Use";
            } else if (this.couchReposotry.existsByPhone(Coach.getPhone())) {
                return "This Phone Number Is Already in Use ";
            }
            this.couchReposotry.save(Coach);
            return " Success";
        } catch (Exception e) {
            return "Failed";
        }

    }

    public String ResetPassword(String NewPass, String Email) {
        try {
            Optional<CoachesEntity> CoachEntity;
            CoachEntity = Optional.ofNullable(this.couchReposotry.findAllByEmail(Email));
            CoachEntity.get().setPassword(NewPass);
            this.couchReposotry.save(CoachEntity.get());
            return "Success";
        } catch (Exception e) {
            return "Failed";
        }
    }

    public Integer OTP_OperationCoach(String email) throws MessagingException, UnsupportedEncodingException {
        Integer OTP = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        CoachesEntity Coach = couchReposotry.findAllByEmail(email);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("sporterapplication2@gmail.com", "SPORTER Support");
        helper.setTo(email);
        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
        String content = "<p>Hello coach " + Coach.getFullname() + "</p>"
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

    public List<CoachesEntity> viewAll() {
        return this.couchReposotry.findAll();
    }

    public Integer getIdFromCoachName(StringBody coachName) {
        try {
            Optional<CoachesEntity> Coach;
            Coach = Optional.ofNullable(this.couchReposotry.findAllByFullname(coachName.getBodystring()));
            if (Coach.get().getId() == 100) return -1;
            return Coach.get().getId();
        } catch (Exception e) {
            return -1;
        }

    }

    public PhoneDigitsAPIBody phonedigitscoach(String email) {
        PhoneDigitsAPIBody Response = new PhoneDigitsAPIBody();
        try {
            Optional<CoachesEntity> Coach;
            Coach = Optional.ofNullable(this.couchReposotry.findAllByEmail(email.toString()));
            if (Coach.isPresent()) {
                Response.setFirstFour("+" + Coach.get().getPhone().subSequence(0, 4).toString());
                Response.setLastTwo(Coach.get().getPhone().subSequence(Coach.get().getPhone().length() - 2, Coach.get().getPhone().length()).toString());
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

        public List<CoachesDto> ShowAll() {
        try {
            Optional<List<CoachesEntity>>coaches=Optional.ofNullable(this.couchReposotry.findAll());
            List<CoachesDto>response=CoachesDto.convertDtoToEntityWithoutPrivateData(coaches.get());
            response.remove(0/*CoachesDto.toDto(this.CouchReposotry.findAllById(100))*/);
            return response;
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }

    public CoachesDto returnAllCoachData(String email) {
        try {
            Optional<CoachesEntity>Coach=Optional.ofNullable(this.couchReposotry.findAllByEmail(email));
            if(Coach.isPresent())
            {
                return  CoachesDto.toDtoWithoutPrivateData(Coach.get());
            }
            else {return new CoachesDto();}

        }
        catch (Exception e){
            return new CoachesDto();
        }

    }

    public List<PlayersDto> returnCoachPlayers(String email) {
        try {
            Optional<CoachesEntity> coach=Optional.ofNullable(this.couchReposotry.findAllByEmail(email));
            if(coach.isEmpty()){return new ArrayList<>();}
            else
            {
                return PlayersDto.convertDtoToEntity(this.couchReposotry.findAllByEmail(email).getPlayersOfCoach());
            }

        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }

    public CoachesDto returnAllCoachData(Integer id) {
        try {
            Optional<CoachesEntity>Coach=Optional.ofNullable(this.couchReposotry.findAllById(id));
            if(Coach.isPresent())
            {
                return  CoachesDto.toDtoWithoutPrivateData(Coach.get());
            }
            else {return new CoachesDto();}

        }
        catch (Exception e){
            return new CoachesDto();
        }
    }

    public String editInfo(EditProfileBody body) {
        try {
            Optional <CoachesEntity> coach =Optional.ofNullable(this.couchReposotry.findAllByEmail(body.getEmail()));
            if(coach.isEmpty())
            {
                coach=Optional.ofNullable(this.couchReposotry.findAllByPhone(body.getPhone()));
                if(coach.isEmpty()){return "You Can't Edit Phone & Email At The Same Time ";}
                coach.get().setEmail(body.getEmail());
            }
            else
            {
                coach.get().setPhone(body.getPhone());
            }
            coach.get().setGoal(body.getGoal());
            coach.get().setFullname(body.getName());
            this.couchReposotry.save(coach.get());
            return "Success";

        }
        catch (Exception e )
        {
            return "Failed";
        }
    }
}
