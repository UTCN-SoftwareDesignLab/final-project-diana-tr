package ro.utcn.ds.finalproject.service.subject;

import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAll();

    Subject findById(Long id);

    Subject findByTeacherId(Long id);

    Subject findByName(String name);

    Subject create(SubjectDto subjectDto);

    void addStudentToSubject(Long studentId, Subject subject);

    void delete(Long id);

    void update(Subject subject);
}
