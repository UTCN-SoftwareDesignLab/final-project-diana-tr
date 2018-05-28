package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.TeacherDto;
import ro.utcn.ds.finalproject.model.Teacher;

@Service
public class TeacherToTeacherDtoConverter implements SuperConverter<Teacher, TeacherDto> {
    @Override
    public TeacherDto apply(final Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setUsername(teacher.getUsername());
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        return teacherDto;
    }
}
