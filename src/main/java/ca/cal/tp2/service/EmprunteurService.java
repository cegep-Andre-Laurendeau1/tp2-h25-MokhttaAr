package ca.cal.tp2.service;

import ca.cal.tp2.repository.EmprunteurDAO;
import ca.cal.tp2.model.Emprunteur;

public class EmprunteurService {

    private EmprunteurDAO emprunteurDAO = new EmprunteurDAO();

    public void registerEmprunteur(String name, String email, String phone) {
        Emprunteur emprunteur = new Emprunteur(name, email, phone);
        emprunteurDAO.save(emprunteur);
        System.out.println("Emprunteur enregistré : " + emprunteur);
    }
}
