package ro.utcn.ds.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "usergrade")
public class UserGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idusergrade")
    private Long id;

    private int grade;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectdetailid")
    private SubjectDetail subjectDetail;

    public UserGrade() {
    }

    public UserGrade(int grade, Student student, Subject subject, SubjectDetail subjectDetail) {
        this.grade = grade;
        this.student = student;
        this.subject = subject;
        this.subjectDetail = subjectDetail;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

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

    public SubjectDetail getSubjectDetail() {
        return subjectDetail;
    }

    public void setSubjectDetail(SubjectDetail subjectDetail) {
        this.subjectDetail = subjectDetail;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
