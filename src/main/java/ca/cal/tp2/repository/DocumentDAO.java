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


    @Override
    public List<T> searchByCriteria(String title, String author, Integer year) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT d FROM " + entityClass.getSimpleName() + " d WHERE 1=1");

            if (title != null && !title.isEmpty()) {
                queryBuilder.append(" AND LOWER(d.titre) LIKE LOWER(:title)");
            }

            // Gérer les différents champs d'auteur selon le type de document
            if (author != null && !author.isEmpty()) {
                if (entityClass.getSimpleName().equals("Livre")) {
                    queryBuilder.append(" AND LOWER(d.auteur) LIKE LOWER(:author)");
                } else if (entityClass.getSimpleName().equals("CD")) {
                    queryBuilder.append(" AND LOWER(d.artiste) LIKE LOWER(:author)");
                } else if (entityClass.getSimpleName().equals("DVD")) {
                    queryBuilder.append(" AND LOWER(d.director) LIKE LOWER(:author)");
                }
            }

            // Année uniquement pour les livres
            if (year != null && entityClass.getSimpleName().equals("Livre")) {
                queryBuilder.append(" AND d.annee = :year");
            }

            TypedQuery<T> query = em.createQuery(queryBuilder.toString(), entityClass);

            if (title != null && !title.isEmpty()) {
                query.setParameter("title", "%" + title + "%");
            }

            if (author != null && !author.isEmpty()) {
                query.setParameter("author", "%" + author + "%");
            }

            if (year != null && entityClass.getSimpleName().equals("Livre")) {
                query.setParameter("year", year);
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            em.close();
        }
    }




}
