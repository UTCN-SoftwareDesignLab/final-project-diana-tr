package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;

@Service
public class SubjectToSubjectDtoConverter implements SuperConverter<Subject, SubjectDto> {
    @Override
    public SubjectDto apply(final Subject subject) {
        final SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setSubjectName(subject.getSubjectName());
        subjectDto.setTeacher_id(subject.getTeacher().getId());
        subjectDto.setStudents(subject.getStudents());
//        List<Long> ids = new ArrayList<>();
//        for (Teacher teacher : subject.getTeachers()) {
//            ids.add(teacher.getId());
//        }
//        subjectDto.setTeacher_id(ids);
        return subjectDto;
    }
}
