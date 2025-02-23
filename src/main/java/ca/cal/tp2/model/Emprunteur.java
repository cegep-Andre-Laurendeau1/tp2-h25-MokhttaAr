package ca.cal.tp2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPRUNTEURS")
public class Emprunteur extends Utilisateur {

    public Emprunteur() {}

    public Emprunteur(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }



    @Override
    public String toString() {
        return "Emprunteur [userID=" + userID + ", name=" + name
                + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }
}
