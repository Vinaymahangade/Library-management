package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    ResponseEntity<String> createStudent(Student student);

    ResponseEntity<String> updateStudent(Student student);

    ResponseEntity<String> deleteStudent(int id);
}
