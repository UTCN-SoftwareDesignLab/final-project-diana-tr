package ro.utcn.ds.finalproject.service.subject;

import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAll();

    Subject findById(Long id);

    Subject create(SubjectDto subjectDto);

    void delete(Long id);

    void update(Subject subject);
}
