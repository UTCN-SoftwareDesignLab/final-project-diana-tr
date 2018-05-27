package ro.utcn.ds.finalproject.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subjectname")
    private String subjectName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subject_teacher", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers;

    public Subject() {
    }

    public Subject(String subjectName, Set<Teacher> teachers) {
        this.subjectName = subjectName;
        this.teachers = teachers;
    }

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


    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
