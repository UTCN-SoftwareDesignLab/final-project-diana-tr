package ro.utcn.ds.finalproject.dto;

import ro.utcn.ds.finalproject.model.Student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SubjectDto {

    private Long id;

    @NotNull(message = "Subject name is required")
    @Size(min = 3, max = 40, message = "Subject name must be between 3 and 40 characters")
    private String subjectName;

    private Long teacher_id;

    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "SubjectDto{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", teacher_id=" + teacher_id +
                ", students=" + students +
                '}';
    }
}
