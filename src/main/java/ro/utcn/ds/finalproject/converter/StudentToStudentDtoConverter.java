package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.model.Student;

@Service
public class StudentToStudentDtoConverter implements SuperConverter<Student, StudentDto> {
    @Override
    public StudentDto apply(final Student student) {
        final StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setUsername(student.getUsername());
        studentDto.setEmail(student.getEmail());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setSubjects(student.getSubjects());
        return studentDto;
    }
}
