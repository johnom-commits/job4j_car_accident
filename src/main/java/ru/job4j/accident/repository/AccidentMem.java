package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Set<Rule> rules = new HashSet<>();
        rules.add(Rule.of(1, "Статья. 1"));
        accidents.put(1, Accident.of(1, "name1", "text1", "address1", AccidentType.of(1, "Две машины"), rules));
        accidents.put(2, Accident.of(2, "name2", "text2", "address2", AccidentType.of(1, "Две машины"), rules));
        accidents.put(3, Accident.of(3, "name3", "text3", "address3", AccidentType.of(1, "Две машины"), rules));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accidents.put(accidents.size() + 1, accident);
    }

    public Optional<Map.Entry<Integer, Accident>> findById(int id) {
         return accidents.entrySet().stream().filter(entry -> id == entry.getValue().getId()).findFirst();
    }
}
