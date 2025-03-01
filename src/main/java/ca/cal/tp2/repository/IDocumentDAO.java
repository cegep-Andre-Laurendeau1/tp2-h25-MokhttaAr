package ca.cal.tp2.repository;

import ca.cal.tp2.model.Document;
import java.sql.SQLException;
import java.util.List;

public interface IDocumentDAO<T extends Document> {
    void save(T document) throws SQLException;
    List<T> findAvailable() throws SQLException;
}
