package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private AccidentMem store;

    @Autowired
    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public Collection<Accident> accidents() {
        return store.getAccidents();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public void create(Accident accident) {
        AccidentType type = store.getAccidentTypeById(accident.getType().getId());
        accident.setType(type);
        store.create(accident);
    }

    public List<AccidentType> types() {
        return store.getTypes();
    }
}
