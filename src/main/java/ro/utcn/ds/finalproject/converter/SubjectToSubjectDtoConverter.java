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
        return subjectDto;
    }
}
