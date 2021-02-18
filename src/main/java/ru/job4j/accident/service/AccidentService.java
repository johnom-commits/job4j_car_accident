package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private AccidentJdbcTemplate store;

    @Autowired
    public AccidentService(AccidentJdbcTemplate store) {
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

        List<Integer> rules = getIntListRules(ids);
        accident.setRules(store.getRules(rules));

        store.create(accident);
    }

    private List<Integer> getIntListRules(String[] rIds) {
        List<Integer> rules = new ArrayList<>();
        for (String rId : rIds) {
            rules.add(Integer.valueOf(rId));
        }
        return rules;
    }

    public List<AccidentType> types() {
        return store.getTypes();
    }

    public List<Rule> rules() {
        return store.getRules();
    }
}
