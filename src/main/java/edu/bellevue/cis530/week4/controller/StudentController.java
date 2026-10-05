package edu.bellevue.cis530.week4.controller;

import edu.bellevue.cis530.week4.entity.Student;
import edu.bellevue.cis530.week4.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(
            @PathVariable String major) {

        return ResponseEntity.ok(
                studentService.getByMajor(major));
    }

    @GetMapping("/gpa/{gpa}")
    public ResponseEntity<List<Student>> getStudentsByGpa(
            @PathVariable Double gpa) {

        return ResponseEntity.ok(
                studentService.getByGpaGreaterThan(gpa));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Student>> getStudentsByEnrollmentYear(
            @PathVariable Integer year) {

        return ResponseEntity.ok(
                studentService.getByEnrollmentYearGreaterThan(year));
    }

    @GetMapping("/top3")
    public ResponseEntity<List<Student>> getTop3StudentsByGpa() {
        return ResponseEntity.ok(
                studentService.getTop3ByGpa());
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Student>> getStudentsSorted(
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                studentService.getAllSortedByLastName(direction));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getStudentsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                studentService.getStudentsPaged(page, size));
    }
}