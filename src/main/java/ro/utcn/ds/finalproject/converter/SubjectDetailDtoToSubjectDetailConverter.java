package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.SubjectDetailDto;
import ro.utcn.ds.finalproject.model.SubjectDetail;

@Service
public class SubjectDetailDtoToSubjectDetailConverter implements SuperConverter<SubjectDetailDto, SubjectDetail> {
    @Override
    public SubjectDetail apply(final SubjectDetailDto subjectDetailDto) {
        final SubjectDetail subjectDetail = new SubjectDetail();
        subjectDetail.setId(subjectDetailDto.getId());
        subjectDetail.setSemester(subjectDetailDto.getSemester());
        subjectDetail.setYear(subjectDetailDto.getYear());
        return subjectDetail;
    }
}
