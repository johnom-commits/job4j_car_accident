package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(1, Accident.of(1, "name1", "text1", "address1"));
        accidents.put(2, Accident.of(2, "name2", "text2", "address2"));
        accidents.put(3, Accident.of(3, "name3", "text3", "address3"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accidents.put(accidents.size() + 1, accident);
    }

    public Accident findById(int id) {
        Accident accident = null;
        Optional<Map.Entry<Integer, Accident>> optionalEntry = accidents.entrySet().stream()
                .filter(entry -> id == entry.getValue().getId())
                .findFirst();
        if (optionalEntry.isPresent()) {
            accident = optionalEntry.get().getValue();
        }
        return accident;
    }
}
