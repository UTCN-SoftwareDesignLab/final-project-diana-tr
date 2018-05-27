package ro.utcn.ds.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "subjectdetail")
public class SubjectDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subjectdetailid")
    private Long id;

    private int year;

    private int semester;

    public SubjectDetail() {
    }

    public SubjectDetail(int year, int semester) {
        this.year = year;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
