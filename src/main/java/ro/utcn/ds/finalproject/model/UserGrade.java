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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectdetailid")
    private SubjectDetail subjectDetail;

    public UserGrade() {
    }

    public UserGrade(int grade, User user, Subject subject, SubjectDetail subjectDetail) {
        this.grade = grade;
        this.user = user;
        this.subject = subject;
        this.subjectDetail = subjectDetail;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectDetail getSubjectDetail() {
        return subjectDetail;
    }

    public void setSubjectDetail(SubjectDetail subjectDetail) {
        this.subjectDetail = subjectDetail;
    }
}
