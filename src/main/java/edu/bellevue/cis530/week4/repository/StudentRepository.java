package edu.bellevue.cis530.week4.repository;

import edu.bellevue.cis530.week4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByMajor(String major);

    List<Student> findByGpaGreaterThan(Double gpa);

    List<Student> findByEnrollmentYearGreaterThan(Integer year);

    List<Student> findTop3ByOrderByGpaDesc();
}