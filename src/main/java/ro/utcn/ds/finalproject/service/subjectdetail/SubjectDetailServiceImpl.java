package ro.utcn.ds.finalproject.service.subjectdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.converter.SubjectDetailDtoToSubjectDetailConverter;
import ro.utcn.ds.finalproject.dto.SubjectDetailDto;
import ro.utcn.ds.finalproject.model.SubjectDetail;
import ro.utcn.ds.finalproject.repository.SubjectDetailRepository;

import java.util.List;

@Service
public class SubjectDetailServiceImpl implements SubjectDetailService {
    @Autowired
    private SubjectDetailRepository subjectDetailRepository;

    @Autowired
    private SubjectDetailDtoToSubjectDetailConverter converter;

    @Override
    public List<SubjectDetail> getAll() {
        return subjectDetailRepository.findAll();
    }

    @Override
    public SubjectDetail findById(Long id) {
        return subjectDetailRepository.getOne(id);
    }

    @Override
    public SubjectDetail create(SubjectDetailDto subjectDetailDto) {
        SubjectDetail subjectDetail = converter.apply(subjectDetailDto);
        return subjectDetailRepository.save(subjectDetail);
    }

    @Override
    public void delete(Long id) {
        subjectDetailRepository.deleteById(id);
    }

    @Override
    public void update(SubjectDetail subjectDetail) {
        SubjectDetail newSubjectDetail = subjectDetailRepository.getOne(subjectDetail.getId());
        newSubjectDetail.setYear(subjectDetail.getYear());
        newSubjectDetail.setSemester(subjectDetail.getSemester());

        subjectDetailRepository.save(newSubjectDetail);
    }
}
