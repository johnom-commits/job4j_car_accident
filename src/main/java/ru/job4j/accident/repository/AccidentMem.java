package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomInt = new AtomicInteger();
    private final List<AccidentType> types = new ArrayList<>();

    public AccidentMem() {
        accidents.put(1, Accident.of(1, "name1", "text1", "address1", AccidentType.of(1, "Две машины")));
        accidents.put(2, Accident.of(2, "name2", "text2", "address2", AccidentType.of(1, "Две машины")));
        accidents.put(3, Accident.of(3, "name3", "text3", "address3", AccidentType.of(1, "Две машины")));
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
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
}
