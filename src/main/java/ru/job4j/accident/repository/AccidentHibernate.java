package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public <T> void create(final T model) {
        tx(session -> session.save(model));
    }

    public Collection<Accident> getAccidents() {
        return tx(session -> session
                .createQuery("select a from Accident as a join fetch a.rules", Accident.class)
                .list());
    }

    public Accident findById(int id) {
        return tx(session -> session
                .createQuery("select a from Accident as a where a.id = :id", Accident.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    public AccidentType getAccidentTypeById(int id) {
        return tx(session -> session
                .createQuery("select a from AccidentType as a where a.id = :id", AccidentType.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    public Set<Rule> getRules(List<Integer> rules) {
        return new HashSet<>(
                tx(session -> session
                        .createQuery("select r from Rule as r where r.id in :ids", Rule.class)
                        .setParameterList("ids", rules)
                        .list()));
    }

    public List<AccidentType> getTypes() {
        return tx(session -> session
                .createQuery("select a from AccidentType as a", AccidentType.class)
                .list());
    }

    public List<Rule> getRules() {
        return tx(session -> session
                .createQuery("select r from Rule as r", Rule.class)
                .list());
    }
}
