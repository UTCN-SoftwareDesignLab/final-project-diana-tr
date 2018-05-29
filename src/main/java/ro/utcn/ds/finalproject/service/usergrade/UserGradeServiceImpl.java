package ro.utcn.ds.finalproject.service.usergrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.converter.UserGradeDtoToUserGradeConverter;
import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.UserGrade;
import ro.utcn.ds.finalproject.repository.UserGradeRepository;
import ro.utcn.ds.finalproject.service.mail.EmailService;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.List;

@Service
public class UserGradeServiceImpl implements UserGradeService, EmailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private UserGradeRepository userGradeRepository;
    @Autowired
    private UserGradeDtoToUserGradeConverter converter;

    @Override
    public List<UserGrade> getAll() {
        return userGradeRepository.findAll();
    }

    @Override
    public List<UserGrade> getAllByStudentId(Long id) {
        return userGradeRepository.getAllByStudent_id(id);
    }


    @Override
    public List<UserGrade> getAllBySubjectId(Long id) {
        return userGradeRepository.getAllBySubject_id(id);
    }

    @Override
    public UserGrade findById(Long id) {
        return userGradeRepository.getOne(id);
    }

    @Override
    public UserGrade create(UserGradeDto userGradeDto) {
        UserGrade userGrade = converter.apply(userGradeDto);
        sendMail(userGrade);
        return userGradeRepository.save(userGrade);
    }

    @Override
    public void delete(Long id) {
        userGradeRepository.deleteById(id);
    }

    @Override
    public void update(UserGrade userGrade) {
        UserGrade newUserGrade = userGradeRepository.getOne(userGrade.getId());
        newUserGrade.setGrade(userGrade.getGrade());
        newUserGrade.setSubjectDetail(userGrade.getSubjectDetail());
        newUserGrade.setStudent(userGrade.getStudent());
        newUserGrade.setSubject(userGrade.getSubject());

        userGradeRepository.save(newUserGrade);
        sendMail(newUserGrade);
    }

    @Override
    public boolean existsById(Long id) {
        if (userGradeRepository.existsById(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void sendMail(Object object) {
        UserGrade userGrade = (UserGrade) object;
        MimeMessagePreparator preparator = getMessagePreparator(userGrade);
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final UserGrade userGrade) {

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setFrom("sendermailsd@gmail.com");
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(userGrade.getStudent().getEmail()));
            mimeMessage.setText("Dear " + userGrade.getStudent().getFirstName()
                    + ", You have received the grade " + userGrade.getGrade() + " for subject " + userGrade.getSubject().getSubjectName() + ".");
            mimeMessage.setSubject("Grade");
        };
        return preparator;
    }
}
