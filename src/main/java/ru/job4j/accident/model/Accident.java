package ru.job4j.accident.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter @Getter @EqualsAndHashCode
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
    private Set<Rule> rules;
    private String rIds;

    public static Accident of(int id, String name, String text, String address, AccidentType type, Set<Rule> rules) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        accident.rules = rules;
        return accident;
    }
}
