package ca.cal.tp2.repository;

import ca.cal.tp2.model.Emprunt;
import ca.cal.tp2.model.EmpruntDetail;
import ca.cal.tp2.model.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class EmpruntDAO implements IEmpruntDAO{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2mokhtar.tp");

    @Override
    public void saveEmprunteur(Emprunteur emprunteur) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emprunteur);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void saveEmprunt(Emprunt emprunt) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (emprunt.getBorrowID() == 0) {
                em.persist(emprunt);
            } else {
                em.merge(emprunt);
            }
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
    public List<Emprunt> findByEmprunteur(long emprunteurId) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Emprunt> query = em.createQuery(
                    "SELECT DISTINCT e FROM Emprunt e " +
                            "JOIN FETCH e.items i " +
                            "JOIN FETCH i.document " +
                            "WHERE e.emprunteur.userID = :emprunteurId",
                    Emprunt.class);
            query.setParameter("emprunteurId", emprunteurId);
            return query.getResultList();
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            em.close();
        }
    }




}
