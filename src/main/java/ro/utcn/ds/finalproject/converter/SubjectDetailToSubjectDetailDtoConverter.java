package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.SubjectDetailDto;
import ro.utcn.ds.finalproject.model.SubjectDetail;

@Service
public class SubjectDetailToSubjectDetailDtoConverter implements SuperConverter<SubjectDetail, SubjectDetailDto> {
    @Override
    public SubjectDetailDto apply(final SubjectDetail subjectDetail) {
        SubjectDetailDto subjectDetailDto = new SubjectDetailDto();
        subjectDetailDto.setId(subjectDetail.getId());
        subjectDetailDto.setSemester(subjectDetail.getSemester());
        subjectDetailDto.setYear(subjectDetail.getYear());
        return subjectDetailDto;
    }
}
