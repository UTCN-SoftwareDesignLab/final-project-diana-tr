package ro.utcn.ds.finalproject.service.teacher;

import ro.utcn.ds.finalproject.dto.TeacherDto;
import ro.utcn.ds.finalproject.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAll();

    Teacher findById(Long id);

    Teacher findByUsername(String username);

    Teacher create(TeacherDto teacherDto);

    void delete(Long id);

    void update(Teacher teacher);
}
