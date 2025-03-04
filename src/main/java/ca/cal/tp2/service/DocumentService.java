package ca.cal.tp2.service;

import ca.cal.tp2.dto.DocumentDTO;
import ca.cal.tp2.model.Document;
import ca.cal.tp2.repository.IDocumentDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DocumentService<T extends Document, D extends DocumentDTO> {
    protected IDocumentDAO<T> documentDAO;

    public DocumentService(IDocumentDAO<T> documentDAO) {
        this.documentDAO = documentDAO;
    }

    public void addDocument(T document) throws SQLException {
        documentDAO.save(document);
    }

    public List<D> getAvailableDocuments() throws SQLException {
        List<T> documents = documentDAO.findAvailable();
        return documents.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<D> searchDocuments(String title, String author, Integer year) throws SQLException {
        List<T> documents = documentDAO.searchByCriteria(title, author, year);
        return documents.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    // Méthode d'affichage générique
    public void afficherResultatsRecherche(List<? extends DocumentDTO> resultats, String type) {
        if (resultats.isEmpty()) {
            System.out.println("Aucun " + type + " trouvé");
        } else {
            System.out.println("Résultats trouvés (" + resultats.size() + ") :");
            for (DocumentDTO doc : resultats) {
                System.out.println("- " + doc.getTitre() + " (" + doc.getNombreExemplaires() + " exemplaires)");
            }
        }
    }



    protected abstract T convertDTOToEntity(D documentDTO);
    protected abstract D convertEntityToDTO(T document);
}
