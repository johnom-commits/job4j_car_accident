package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomInt = new AtomicInteger();

    public AccidentMem() {
        accidents.put(1, Accident.of("name1", "text1", "address1"));
        accidents.put(2, Accident.of("name2", "text2", "address2"));
        accidents.put(3, Accident.of("name3", "text3", "address3"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        atomInt.set(accidents.size());
        accidents.put(atomInt.incrementAndGet(), accident);
    }
}
