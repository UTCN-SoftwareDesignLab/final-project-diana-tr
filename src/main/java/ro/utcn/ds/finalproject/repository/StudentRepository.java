package ro.utcn.ds.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ds.finalproject.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO student_subject(student_id,subject_id) VALUES (?1,?2)", nativeQuery = true)
    void addSubjectToStudent(Long studentId, Long subjectId);
}
