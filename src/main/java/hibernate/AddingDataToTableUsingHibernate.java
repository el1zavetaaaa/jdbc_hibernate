package hibernate;

import hibernate.dao.AddingDataToTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddingDataToTableUsingHibernate {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            AddingDataToTable addingDataToTable = new AddingDataToTable();
            try {
                session.getTransaction().begin();
                addingDataToTable.initialization(session);
                session.getTransaction().commit();
            } catch (Exception e) {

                session.getTransaction().rollback();
                throw new RuntimeException();
            }

        }
    }
}
