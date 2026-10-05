package edu.bellevue.cis530.week4.repository;

import edu.bellevue.cis530.week4.entity.Student;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByMajor(String major);

    List<Student> findByGpaGreaterThan(Double gpa);

    List<Student> findByEnrollmentYearGreaterThan(Integer year);

    List<Student> findTop3ByOrderByGpaDesc();

    @Query("SELECT s FROM Student s WHERE s.major = :major")
    List<Student> findStudentsByMajorJPQL(@Param("major") String major);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.enrollmentYear = :year")
    int deleteStudentsByEnrollmentYearJPQL(@Param("year") Integer year);
}
