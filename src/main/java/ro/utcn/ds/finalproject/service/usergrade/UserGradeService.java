package ro.utcn.ds.finalproject.service.usergrade;

import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.UserGrade;

import java.util.List;

public interface UserGradeService {

    List<UserGrade> getAll();

    List<UserGrade> getAllByStudentId(Long id);

    List<UserGrade> getAllBySubjectId(Long id);

    UserGrade findById(Long id);

    UserGrade create(UserGradeDto userGradeDto);

    void delete(Long id);

    void update(UserGrade userGrade);
}
