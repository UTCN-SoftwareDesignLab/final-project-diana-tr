package ro.utcn.ds.finalproject.dto;

public class UserGradeDto {

    private Long id;

    private int grade;

    private Long user_id;

    private Long idsubject;

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getIdsubject() {
        return idsubject;
    }

    public void setIdsubject(Long idsubject) {
        this.idsubject = idsubject;
    }

    public Long getSubjectdetailid() {
        return subjectdetailid;
    }

    public void setSubjectdetailid(Long subjectdetailid) {
        this.subjectdetailid = subjectdetailid;
    }
}
