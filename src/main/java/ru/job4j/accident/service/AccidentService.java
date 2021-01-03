package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
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

    public void create(Accident accident, String[] ids) {
        AccidentType type = store.getAccidentTypeById(accident.getType().getId());
        accident.setType(type);

        int[] array = getIntArrayRules(ids);
        accident.setRules(store.getRules(array));

        store.create(accident);
    }

    private int[] getIntArrayRules(String[] rIds) {
        int[] array = new int[rIds.length];
        for (int j = 0; j < array.length; j++) {
            array[j] = Integer.parseInt(rIds[j]);
        }
        return array;
    }

    public List<AccidentType> types() {
        return store.getTypes();
    }

    public List<Rule> rules() {
        return store.getRules();
    }
}
