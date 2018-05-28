package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.TeacherDto;
import ro.utcn.ds.finalproject.model.Teacher;

@Service
public class TeacherDtoToTeacherConverter implements SuperConverter<TeacherDto, Teacher> {
    @Override
    public Teacher apply(final TeacherDto teacherDto) {
        final Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setUsername(teacherDto.getUsername());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        return teacher;
    }
}
