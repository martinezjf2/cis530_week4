package edu.bellevue.cis530.week4.service;

import edu.bellevue.cis530.week4.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudentById(Long id);

    List<Student> getAllStudents();

    Student updateStudent(Long id, Student student);

    void deleteStudentById(Long id);

    List<Student> getByMajor(String major);

    List<Student> getByGpaGreaterThan(Double gpa);

    List<Student> getByEnrollmentYearGreaterThan(Integer year);

    List<Student> getTop3ByGpa();

    List<Student> getAllSortedByLastName(String direction);

    Page<Student> getStudentsPaged(int page, int size);

    List<Student> getByMajorJPQL(String major);

    int deleteByEnrollmentYearJPQL(Integer year);
}