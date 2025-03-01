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

    public void addDocument(D documentDTO) throws SQLException {
        T document = convertDTOToEntity(documentDTO);
        documentDAO.save(document);
    }

    public List<D> getAvailableDocuments() throws SQLException {
        List<T> documents = documentDAO.findAvailable();
        return documents.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }



    protected abstract T convertDTOToEntity(D documentDTO);
    protected abstract D convertEntityToDTO(T document);
}
