package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.OCTOBER;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(IStudentRepository repository){

        return args-> {
            Student mihnea = new Student(1L,
                    "Mihnea",
                    "mihnea.iliescu06@gmail.com",
                    LocalDate.of(2000, OCTOBER,5)

            );
            Student alex=  new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2002, OCTOBER,5)

            );

             repository.saveAll(List.of(mihnea,alex));
        };
    }
}
