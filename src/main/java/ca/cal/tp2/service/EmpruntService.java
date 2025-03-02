package ca.cal.tp2.service;

import ca.cal.tp2.model.*;
import ca.cal.tp2.repository.EmpruntDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;


public class EmpruntService {
    private EmpruntDAO empruntDAO = new EmpruntDAO();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2mokhtar.tp");



    public void registerEmprunteur(String name, String email, String phone) {
        Emprunteur emprunteur = new Emprunteur(name, email, phone);
        empruntDAO.saveEmprunteur(emprunteur);
        System.out.println("Emprunteur enregistré : " + emprunteur);
    }



    public boolean emprunterDocument(long documentId, long emprunteurId) throws SQLException {
        EntityManager em = emf.createEntityManager();
        Document document = null;
        Emprunteur emprunteur = null;

        try {

            document = em.find(Document.class, documentId);
            if (document == null) {
                System.out.println("❌ Emprunt impossible: document non trouvé");
                return false;
            }

            if (!document.verifieDisponibilite()) {
                System.out.println("❌ Emprunt impossible: aucun exemplaire disponible pour \""
                        + document.getTitre() + "\"");
                return false;
            }

            emprunteur = em.find(Emprunteur.class, emprunteurId);
            if (emprunteur == null) {
                System.out.println("❌ Emprunt impossible: emprunteur non trouvé");
                return false;
            }


            Emprunt emprunt = new Emprunt();
            emprunt.setEmprunteur(emprunteur);

            EmpruntDetail detail = new EmpruntDetail();
            detail.setDocument(document);
            emprunt.addItem(detail);


            em.getTransaction().begin();
            document.decrementExemplaires();
            em.getTransaction().commit();


            empruntDAO.saveEmprunt(emprunt);

            System.out.println("✅ Emprunt réussi: \"" + document.getTitre() +
                    "\" par " + emprunteur.getName());
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("❌ Erreur lors de l'emprunt: " + e.getMessage());
            throw new SQLException(e);
        } finally {
            em.close();
        }
    }



}