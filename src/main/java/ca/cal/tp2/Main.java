package ca.cal.tp2;

import ca.cal.tp2.service.EmprunteurService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        TcpServer.createTcpServer();

        EmprunteurService service = new EmprunteurService();

        String nom = "Jean Dupont";
        String email = "jean.dupont@example.com";
        String telephone = "0123456789";

        service.registerEmprunteur(nom, email, telephone);

        System.out.println("Enregistrement terminé.");
    }
}
