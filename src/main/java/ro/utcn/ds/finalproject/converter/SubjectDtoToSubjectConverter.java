package ro.utcn.ds.finalproject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;
import ro.utcn.ds.finalproject.model.Teacher;
import ro.utcn.ds.finalproject.service.teacher.TeacherService;

@Service
public class SubjectDtoToSubjectConverter implements SuperConverter<SubjectDto, Subject> {
    @Autowired
    private TeacherService teacherService;

    @Override
    public Subject apply(final SubjectDto subjectDto) {
        final Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setSubjectName(subjectDto.getSubjectName());
        Teacher teacher = teacherService.findById(subjectDto.getTeacher_id());
        subject.setTeacher(teacher);
        subject.setStudents(subjectDto.getStudents());
        return subject;
    }
}
