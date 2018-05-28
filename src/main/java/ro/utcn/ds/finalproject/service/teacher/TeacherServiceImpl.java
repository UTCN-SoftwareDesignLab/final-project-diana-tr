package ro.utcn.ds.finalproject.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.converter.TeacherDtoToTeacherConverter;
import ro.utcn.ds.finalproject.dto.TeacherDto;
import ro.utcn.ds.finalproject.model.Teacher;
import ro.utcn.ds.finalproject.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherDtoToTeacherConverter converter;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.getOne(id);
    }

    @Override
    public Teacher findByUsername(String username) {
        return teacherRepository.findByUsername(username);
    }

    @Override
    public Teacher create(TeacherDto teacherDto) {
        Teacher teacher = converter.apply(teacherDto);
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void update(Teacher teacher) {
        Teacher newTeacher = teacherRepository.getOne(teacher.getId());
        newTeacher.setUsername(teacher.getUsername());
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setFirstName(teacher.getFirstName());
        newTeacher.setLastName(teacher.getLastName());
        teacherRepository.save(newTeacher);
    }
}
