package ca.cal.tp2.service;

import ca.cal.tp2.dto.EmpruntDTO;
import ca.cal.tp2.dto.EmpruntDetailDTO;
import ca.cal.tp2.mapper.EmpruntMapper;
import ca.cal.tp2.model.*;
import ca.cal.tp2.repository.EmpruntDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;
import java.util.List;


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


    public List<EmpruntDTO> getEmpruntsForEmprunteur(long emprunteurId) throws SQLException {
        try {
            List<Emprunt> emprunts = empruntDAO.findByEmprunteur(emprunteurId);
            return EmpruntMapper.toDTOList(emprunts);
        } catch (Exception e) {
            throw new SQLException("Erreur lors de la récupération des emprunts: " + e.getMessage(), e);
        }
    }

    public void afficherEmpruntsEmprunteurDTO(long emprunteurId) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            Emprunteur emprunteur = em.find(Emprunteur.class, emprunteurId);
            if (emprunteur == null) {
                System.out.println("❌ Emprunteur non trouvé avec ID: " + emprunteurId);
                return;
            }

            List<EmpruntDTO> empruntsDTO = getEmpruntsForEmprunteur(emprunteurId);

            if (empruntsDTO.isEmpty()) {
                System.out.println("L'emprunteur " + emprunteur.getName() + " n'a pas d'emprunts actifs.");
                return;
            }

            System.out.println("\n=== EMPRUNTS DE " + emprunteur.getName().toUpperCase() + " ===");
            System.out.println("Email: " + emprunteur.getEmail());
            System.out.println("Téléphone: " + emprunteur.getPhoneNumber());
            System.out.println("\nListe des documents empruntés:");

            for (EmpruntDTO emprunt : empruntsDTO) {
                System.out.println("\nEMPRUNT #" + emprunt.getBorrowID() + " - Date: " + emprunt.getDateEmprunt());

                for (EmpruntDetailDTO detail : emprunt.getItems()) {
                    System.out.println("- " + detail.getDocumentTitre());
                    System.out.println("  Date de retour prévue: " + detail.getDateRetourPrevue());
                    if (detail.getDateRetourActuelle() != null) {
                        System.out.println("  Retourné le: " + detail.getDateRetourActuelle());
                    } else {
                        System.out.println("  Statut: " + detail.getStatus());
                    }
                }
            }
        } finally {
            em.close();
        }
    }



}