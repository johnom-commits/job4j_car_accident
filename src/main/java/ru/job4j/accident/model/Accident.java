package ru.job4j.accident.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accidents")
@Setter @Getter @EqualsAndHashCode @NoArgsConstructor
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 2000)
    private String name;
    @Column(length = 2000)
    private String text;
    @Column(length = 200)
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "accidents_rules", joinColumns = {
            @JoinColumn(name = "acc_id", nullable = false, foreignKey = @ForeignKey(name = "ACCIDENT_ID_FK"))},
            inverseJoinColumns = {@JoinColumn(name = "rule_id",
                    nullable = false, foreignKey = @ForeignKey(name = "RULE_ID_FK"))})
    private Set<Rule> rules;

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
