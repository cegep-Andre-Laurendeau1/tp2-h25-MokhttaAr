package ca.cal.tp2.repository;

import ca.cal.tp2.model.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EmprunteurDAO implements IEmprunteurDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2mokhtar.tp");

    @Override
    public void save(Emprunteur emprunteur) {
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


}
