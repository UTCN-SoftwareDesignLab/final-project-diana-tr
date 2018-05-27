package ro.utcn.ds.finalproject.service.subjectdetail;

import ro.utcn.ds.finalproject.dto.SubjectDetailDto;
import ro.utcn.ds.finalproject.model.SubjectDetail;

import java.util.List;

public interface SubjectDetailService {
    List<SubjectDetail> getAll();

    SubjectDetail findById(Long id);

    SubjectDetail create(SubjectDetailDto subjectDetailDto);

    void delete(Long id);

    void update(SubjectDetail subjectDetail);
}
