package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.*;

import java.util.*;

@Service
public class AccidentService {

    private AccidentRepository store;
    private AccidentTypeRepository accidentType;
    private RuleRepository rule;

    @Autowired
    public AccidentService(AccidentRepository store, AccidentTypeRepository accidentType, RuleRepository rule) {
        this.store = store;
        this.accidentType = accidentType;
        this.rule = rule;
    }

    public Collection<Accident> accidents() {
        return store.findAll();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public void create(Accident accident, String[] ids) {
        int id = accident.getType().getId();
        AccidentType type = accidentType.findById(id).get();
        accident.setType(type);

        List<Integer> rules = getIntListRules(ids);
        List<Rule> list = (List<Rule>) rule.findAllById(rules);
        accident.setRules(new HashSet<>(list));

        store.save(accident);
    }

    private List<Integer> getIntListRules(String[] rIds) {
        List<Integer> rules = new ArrayList<>();
        for (String rId : rIds) {
            rules.add(Integer.valueOf(rId));
        }
        return rules;
    }

    public List<AccidentType> types() {
        return (List<AccidentType>) accidentType.findAll();
    }

    public List<Rule> rules() {
        return (List<Rule>) rule.findAll();
    }
}
