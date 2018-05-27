package ro.utcn.ds.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.ds.finalproject.model.SubjectDetail;

@Repository
public interface SubjectDetailRepository extends JpaRepository<SubjectDetail, Long> {


}
