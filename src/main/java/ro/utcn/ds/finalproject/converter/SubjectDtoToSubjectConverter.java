package ro.utcn.ds.finalproject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;
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
        subject.setTeacher(teacherService.findById(subjectDto.getTeacher_id()));
        subject.setStudents(subjectDto.getStudents());
//        List<Teacher> teacherList = new ArrayList<>();
//        for (Long id : subjectDto.getTeacher_id()) {
//            Teacher teacher = teacherService.findById(id);
//            teacherList.add(teacher);
//        }
//        subject.setTeachers(teacherList);
        return subject;
    }
}
