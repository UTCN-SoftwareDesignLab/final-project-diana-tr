package ro.utcn.ds.finalproject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.UserGrade;
import ro.utcn.ds.finalproject.service.student.StudentService;
import ro.utcn.ds.finalproject.service.subject.SubjectService;
import ro.utcn.ds.finalproject.service.subjectdetail.SubjectDetailService;

@Service
public class UserGradeDtoToUserGradeConverter implements SuperConverter<UserGradeDto, UserGrade> {
    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectDetailService subjectDetailService;

    @Override
    public UserGrade apply(final UserGradeDto userGradeDto) {
        final UserGrade userGrade = new UserGrade();
        userGrade.setId(userGradeDto.getId());
        userGrade.setGrade(userGradeDto.getGrade());
        userGrade.setStudent(studentService.findById(userGradeDto.getStudent_id()));
        userGrade.setSubject(subjectService.findById(userGradeDto.getSubject_id()));
        userGrade.setSubjectDetail(subjectDetailService.findById(userGradeDto.getSubjectdetailid()));
        return userGrade;
    }
}
