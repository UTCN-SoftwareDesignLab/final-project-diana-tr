package ro.utcn.ds.finalproject.service.student;

import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.model.Student;
import ro.utcn.ds.finalproject.model.validation.Notification;

import java.util.List;

public interface StudentService {
    List<Student> getAll();

    Student findById(Long id);

    Student findByUsername(String username);

    Student create(StudentDto studentDto);

    void addSubjectToStudent(Long subjectId, Student student);

    Notification<Boolean> delete(Long id);

    void update(Student student);

    boolean studentExists(Long id);

    Boolean existsBySubjectId(Long id);

    Boolean existsBySubjectAndStudent(Long student_id, Long subject_id);
}
