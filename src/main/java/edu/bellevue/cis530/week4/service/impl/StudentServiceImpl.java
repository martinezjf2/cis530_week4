package edu.bellevue.cis530.week4.service.impl;

import edu.bellevue.cis530.week4.entity.Student;
import edu.bellevue.cis530.week4.exception.ResourceNotFoundException;
import edu.bellevue.cis530.week4.repository.StudentRepository;
import edu.bellevue.cis530.week4.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = getStudentById(id);

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMajor(student.getMajor());
        existingStudent.setGpa(student.getGpa());
        existingStudent.setEnrollmentYear(student.getEnrollmentYear());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getByMajor(String major) {
        return studentRepository.findByMajor(major);
    }

    @Override
    public List<Student> getByGpaGreaterThan(Double gpa) {
        return studentRepository.findByGpaGreaterThan(gpa);
    }

    @Override
    public List<Student> getByEnrollmentYearGreaterThan(Integer year) {
        return studentRepository.findByEnrollmentYearGreaterThan(year);
    }

    @Override
    public List<Student> getTop3ByGpa() {
        return studentRepository.findTop3ByOrderByGpaDesc();
    }

    @Override
    public List<Student> getAllSortedByLastName(String direction) {

        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        return studentRepository.findAll(
                Sort.by(sortDirection, "lastName"));
    }

    @Override
    public Page<Student> getStudentsPaged(int page, int size) {
        return studentRepository.findAll(
                PageRequest.of(page, size));
    }
}