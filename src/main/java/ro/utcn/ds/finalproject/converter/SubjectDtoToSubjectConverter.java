package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;

@Service
public class SubjectDtoToSubjectConverter implements SuperConverter<SubjectDto, Subject> {
    @Override
    public Subject apply(final SubjectDto subjectDto) {
        final Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setSubjectName(subjectDto.getSubjectName());
        return subject;
    }
}
