package ca.cal.tp2.repository;

import ca.cal.tp2.model.Emprunt;
import ca.cal.tp2.model.EmpruntDetail;
import ca.cal.tp2.model.Emprunteur;
import java.sql.SQLException;
import java.util.List;

public interface IEmpruntDAO {
    void saveEmprunteur(Emprunteur emprunteur) throws SQLException;
    void saveEmprunt(Emprunt emprunt) throws SQLException;
}
