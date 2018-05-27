package ro.utcn.ds.finalproject.service.usergrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.converter.UserGradeDtoToUserGradeConverter;
import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.UserGrade;
import ro.utcn.ds.finalproject.repository.UserGradeRepository;

import java.util.List;

@Service
public class UserGradeServiceImpl implements UserGradeService {
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
    }
}
