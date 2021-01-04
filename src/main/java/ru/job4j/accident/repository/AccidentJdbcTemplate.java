package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> getAccidents() {
        return jdbc.query("SELECT id, name, text, address, type_id FROM accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    int id = rs.getInt("id");
                    accident.setId(id);
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getAccidentTypeById(rs.getInt("type_id")));
                    accident.setRules(getRulesOfAccidentId(id));
                    return accident;
                });
    }

    public List<Rule> getRules() {
        return jdbc.query("SELECT id, name FROM rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<AccidentType> getTypes() {
        return jdbc.query("SELECT id, name FROM accident_type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public Accident findById(int id) {
        return jdbc.queryForObject("SELECT id, name, text, address, type_id FROM accidents WHERE id = ?",
                (rs, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setAddress(rs.getString("address"));
                    accident.setText(rs.getString("text"));
                    accident.setType(getAccidentTypeById(rs.getInt("type_id")));
                    accident.setRules(getRulesOfAccidentId(id));
                    return accident;
                }, id);
    }

    public Set<Rule> getRules(List<Integer> ids) {
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        return new HashSet<>(
                jdbc.query(String.format("SELECT id, name FROM rules WHERE id IN (%s)", inSql),
                        (rs, row) -> {
                            Rule rule = new Rule();
                            rule.setId(rs.getInt("id"));
                            rule.setName(rs.getString("name"));
                            return rule;
                        }, ids.toArray())
        );
    }

    private Set<Rule> getRulesOfAccidentId(int id) {
        return new HashSet<>(
                jdbc.query("SELECT a.rule_id AS id, r.name FROM accidents_rules AS a INNER JOIN rules AS r on a.rule_id = r.id WHERE a.acc_id = ?;",
                        (rs, row) -> {
                            Rule rule = new Rule();
                            rule.setId(rs.getInt("id"));
                            rule.setName(rs.getString("name"));
                            return rule;
                        }, id)
        );
    }

    public AccidentType getAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "SELECT name FROM accident_type WHERE id = ?",
                (rs, rowNum) -> {
                    AccidentType type = new AccidentType();
                    type.setId(id);
                    type.setName(rs.getString("name"));
                    return type;
                }, id);
    }

    public void create(Accident a) {
        String textSQL = "INSERT INTO accidents (name, text, address, type_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            var ps = con.prepareStatement(textSQL, new String[]{"id"});
            ps.setString(1, a.getName());
            ps.setString(2, a.getText());
            ps.setString(3, a.getAddress());
            ps.setInt(4, a.getType().getId());
            return ps;
        }, keyHolder);

        for (Rule rule : a.getRules()) {
            jdbc.update("INSERT INTO accidents_rules (acc_id, rule_id) VALUES (?, ?)",
                    keyHolder.getKey(), rule.getId());
        }
    }
}
