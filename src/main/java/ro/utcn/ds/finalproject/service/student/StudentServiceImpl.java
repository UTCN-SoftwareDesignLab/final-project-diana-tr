package ro.utcn.ds.finalproject.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.converter.StudentDtoToStudentConverter;
import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.model.Student;
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
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
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
}
