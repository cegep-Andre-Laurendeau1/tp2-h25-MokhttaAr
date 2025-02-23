package ca.cal.tp2.repository;

import ca.cal.tp2.model.Emprunteur;
import java.sql.SQLException;

public interface IEmprunteurDAO {
    void save(Emprunteur emprunteur) throws SQLException;

}
