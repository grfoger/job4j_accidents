package ru.job4j.accidents;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.accidents.repositorydata.AccidentRepository;

@SpringBootTest
public class DataTest {

    @Autowired
    public AccidentRepository repository;

    @Test
    public void setAndGet() {
        repository.findAll().forEach(System.out::println);
        System.out.println(repository.findById(1).get());
    }
}
