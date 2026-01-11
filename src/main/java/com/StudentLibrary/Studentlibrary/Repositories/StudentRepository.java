package com.StudentLibrary.Studentlibrary.Repositories;

import com.StudentLibrary.Studentlibrary.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Query("update Student s set s.emailId = :newEmail where s.emailId = :oldEmail")
    int updateStudentEmail(@Param("oldEmail") String oldEmail,
                           @Param("newEmail") String newEmail);

    @Modifying
    @Query("delete from Student s where s.id = :id")
    int deleteCustom(@Param("id") int id);

    @Modifying
    @Query("update Student s set s.emailId = :#{#student.emailId}, " +
           "s.name = :#{#student.name}, " +
           "s.age = :#{#student.age}, " +
           "s.country = :#{#student.country} " +
           "where s.id = :#{#student.id}")
    int updateStudentDetails(@Param("student") Student student);

    @Query("select s from Student s where s.emailId = :mail")
    List<Student> findByMail(@Param("mail") String mail);

    @Query(value = "select * from student where email_id = :mail", nativeQuery = true)
    List<Student> findByMailNative(@Param("mail") String mail);
}
