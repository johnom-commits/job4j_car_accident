package ru.job4j.accident.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @EqualsAndHashCode
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;

    public static Accident of(int id, String name, String text, String address) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        return accident;
    }
}
