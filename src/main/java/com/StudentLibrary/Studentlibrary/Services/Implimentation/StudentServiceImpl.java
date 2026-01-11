package com.StudentLibrary.Studentlibrary.Services.Implimentation;

import com.StudentLibrary.Studentlibrary.Model.Card;
import com.StudentLibrary.Studentlibrary.Model.Student;
import com.StudentLibrary.Studentlibrary.Repositories.StudentRepository;
import com.StudentLibrary.Studentlibrary.Services.StudentService;
import com.StudentLibrary.Studentlibrary.Services.CardService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardService cardService;

    @Override
    public ResponseEntity<String> createStudent(Student student) {
        Card card = cardService.createCard(student);
        log.info("Card created for student {} with cardId {}", student.getName(), card.getId());
        return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateStudent(Student student) {
        int updated = studentRepository.updateStudentDetails(student);

        if (updated > 0) {
            return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteStudent(int id) {
        cardService.deactivate(id);
        int deleted = studentRepository.deleteCustom(id);

        if (deleted > 0) {
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }
}
