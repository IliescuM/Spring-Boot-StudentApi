package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final IStudentRepository studentRepository;

    @Autowired
    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException
                    ("student with id "+id+" does not exits");
        }
        studentRepository.deleteById(id);

    }
@Transactional
// when we have this annotation the entity goes into a management state
    public void updateStudent(Long id,String name, String email) {
        boolean exists = studentRepository.findById(id).isPresent();

        Student student = studentRepository.findById(id).orElseThrow(()->
                new IllegalStateException("student with id "+ id+" does not exits"));
        if(name!=null &&
                name.length()>0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email!=null &&
           email.length() > 0 &&
            !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw  new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
