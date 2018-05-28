package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.model.Student;

@Service
public class StudentDtoToStudentConverter implements SuperConverter<StudentDto, Student> {
    @Override
    public Student apply(final StudentDto studentDto) {
        final Student student = new Student();
        student.setId(studentDto.getId());
        student.setEmail(studentDto.getEmail());
        student.setUsername(studentDto.getUsername());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setSubjects(studentDto.getSubjects());
        return student;
    }
}
