package ro.utcn.ds.finalproject.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.converter.StudentDtoToStudentConverter;
import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.model.Student;
import ro.utcn.ds.finalproject.model.validation.Notification;
import ro.utcn.ds.finalproject.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentDtoToStudentConverter converter;


    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.getOne(id);
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public Student create(StudentDto studentDto) {
        Student student = converter.apply(studentDto);
        return studentRepository.save(student);
    }

    @Override
    public void addSubjectToStudent(Long subjectId, Student student) {
        Long studentId = student.getId();
        studentRepository.addSubjectToStudent(studentId, subjectId);
        System.out.println("reached");
        System.out.println(studentId);
        System.out.println(subjectId);
    }

    @Override
    public Notification<Boolean> delete(Long id) {
        Notification<Boolean> deleteNotification = new Notification<>();
        if (id == null) {
            deleteNotification.addError("Please enter the id!");
            deleteNotification.setResult(false);
            return deleteNotification;
        }
        if (id < 0) {
            deleteNotification.addError("Please enter a positive value!");
            deleteNotification.setResult(false);
            return deleteNotification;
        }
        if (!studentExists(id)) {
            deleteNotification.addError("The student with the specified id doesn't exist!");
            deleteNotification.setResult(false);
            return deleteNotification;
        }
        if (existsBySubjectId(id)) {
            deleteNotification.addError("The student can't be deleted!");
            deleteNotification.setResult(false);
            return deleteNotification;

        }
        deleteNotification.setResult(true);
        studentRepository.deleteById(id);
        return deleteNotification;
    }

    @Override
    public void update(Student student) {
        Student newStudent = studentRepository.getOne(student.getId());
        newStudent.setUsername(student.getUsername());
        newStudent.setEmail(student.getEmail());
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());

        studentRepository.save(newStudent);
    }

    @Override
    public boolean studentExists(Long id) {
        if (studentRepository.existsById(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean existsBySubjectId(Long id) {
        if (studentRepository.existsBySubjectId(id) == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean existsBySubjectAndStudent(Long student_id, Long subject_id) {
        if (studentRepository.existsBySubjectAndStudent(student_id, subject_id) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
