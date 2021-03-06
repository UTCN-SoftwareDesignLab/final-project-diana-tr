package ro.utcn.ds.finalproject.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class UserGradeDto {

    private Long id;

    @NotNull(message = "Grade is required")
    @Range(min = 1, max = 10, message = "Grade must be between 1 and 10")
    private int grade;

    private Long student_id;

    private Long subject_id;

    private Long subjectdetailid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Long getSubjectdetailid() {
        return subjectdetailid;
    }

    public void setSubjectdetailid(Long subjectdetailid) {
        this.subjectdetailid = subjectdetailid;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "UserGradeDto{" +
                "id=" + id +
                ", grade=" + grade +
                ", student_id=" + student_id +
                ", subject_id=" + subject_id +
                ", subjectdetailid=" + subjectdetailid +
                '}';
    }
}
