package ca.cal.tp2.repository;

import ca.cal.tp2.model.Document;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class DocumentDAO<T extends Document> implements IDocumentDAO<T> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2mokhtar.tp");
    private Class<T> entityClass;

    public DocumentDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T document) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(document);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new SQLException(e);
        } finally {
            em.close();
        }
    }


    @Override
    public List<T> findAvailable() throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<T> query = em.createQuery(
                "SELECT d FROM " + entityClass.getSimpleName() + " d WHERE d.nombreExemplaires > 0",
                entityClass);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            em.close();
        }
    }




}
