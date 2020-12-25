package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private static HashMap<Integer, Accident> accidents = new HashMap<>();

    public static HashMap<Integer, Accident> getAccidents() {
        accidents.put(1, Accident.of("name1", "text1", "address1"));
        accidents.put(2, Accident.of("name2", "text2", "address2"));
        accidents.put(3, Accident.of("name3", "text3", "address3"));
        return accidents;
    }
}
