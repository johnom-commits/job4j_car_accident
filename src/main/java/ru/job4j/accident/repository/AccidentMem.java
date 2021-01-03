package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomInt = new AtomicInteger();
    private final List<AccidentType> types = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();

    public AccidentMem() {
        Set<Rule> setRules = new HashSet<>();
        setRules.add(Rule.of(1, "Статья. 1"));
        accidents.put(1, Accident.of(1, "name1", "text1", "address1", AccidentType.of(1, "Две машины"), setRules));
        accidents.put(2, Accident.of(2, "name2", "text2", "address2", AccidentType.of(1, "Две машины"), setRules));
        accidents.put(3, Accident.of(3, "name3", "text3", "address3", AccidentType.of(1, "Две машины"), setRules));
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        atomInt.set(accidents.size());
        accidents.put(atomInt.incrementAndGet(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<AccidentType> getTypes() {
        return types;
    }

    public AccidentType getAccidentTypeById(int id) {
        return types.get(id);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Set<Rule> getRules(int[] rIds) {
        Set<Rule> set = new HashSet<>();
        Arrays.stream(rIds).forEach(i -> set.add(rules.get(i - 1)));
        return set;
    }
}
