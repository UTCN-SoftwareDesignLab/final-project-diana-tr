package ro.utcn.ds.finalproject.service.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.converter.SubjectDtoToSubjectConverter;
import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;
import ro.utcn.ds.finalproject.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectDtoToSubjectConverter converter;

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }


    @Override
    public Subject findById(Long id) {
        return subjectRepository.getOne(id);
    }

    @Override
    public Subject findByTeacherId(Long id) {
        return subjectRepository.findByTeacher_id(id);
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findBySubjectName(name);
    }

    @Override
    public Subject create(SubjectDto subjectDto) {
        Subject subject = converter.apply(subjectDto);
        return subjectRepository.save(subject);
    }

    @Override
    public void addStudentToSubject(Long studentId, Subject subject) {
        Long subjectId = subject.getId();
        subjectRepository.addStudentToSubject(studentId, subjectId);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void update(Subject subject) {
        Subject newSubject = subjectRepository.getOne(subject.getId());
        newSubject.setSubjectName(subject.getSubjectName());

        subjectRepository.save(newSubject);
    }
}
