package ca.cal.tp2;

       import ca.cal.tp2.model.CD;
       import ca.cal.tp2.model.DVD;
       import ca.cal.tp2.model.Livre;
       import ca.cal.tp2.repository.DocumentDAO;
       import ca.cal.tp2.service.EmprunteurService;
       import ca.cal.tp2.dto.LivreDTO;
       import ca.cal.tp2.dto.CDDTO;
       import ca.cal.tp2.dto.DVDDTO;
       import ca.cal.tp2.mapper.DocumentMapper;


       import java.sql.SQLException;

       public class Main {
           public static void main(String[] args) throws SQLException {
               TcpServer.createTcpServer();


               EmprunteurService service = new EmprunteurService();
               String nom = "Jean Dupont";
               String email = "jean.dupont@example.com";
               String telephone = "0123456789";
               service.registerEmprunteur(nom, email, telephone);


               DocumentDAO<Livre> livreDAO = new DocumentDAO<>(Livre.class);
               DocumentDAO<CD> cdDAO = new DocumentDAO<>(CD.class);
               DocumentDAO<DVD> dvdDAO = new DocumentDAO<>(DVD.class);


               Livre livre = new Livre(
                       0, // ID sera généré automatiquement
                       "Les Misérables",
                       3, // nombre d'exemplaires
                       "978-2253096344",
                       "Victor Hugo",
                       "Le Livre de Poche",
                       1472
               );
               livreDAO.save(livre);
               System.out.println("Livre ajouté avec succès: " + livre);



               CD cd = new CD(
                       0,
                       "Thriller",
                       2,
                       "Michael Jackson",
                       42,
                       "Pop"
               );
               cdDAO.save(cd);
               System.out.println("CD ajouté avec succès: " + cd);


               DVD dvd = new DVD(
                       0,
                       "Le Seigneur des Anneaux",
                       4,
                       "Peter Jackson",
                       178,
                       "PG-13"
               );
               dvdDAO.save(dvd);
               System.out.println("DVD ajouté avec succès: " + dvd);

               System.out.println("Enregistrement terminé.");



               System.out.println("\n=== TEST: RECHERCHE DES DOCUMENTS DISPONIBLES ===");


               DocumentMapper<Livre, LivreDTO> livreMapper = new DocumentMapper<>(Livre.class, LivreDTO.class);
               DocumentMapper<CD, CDDTO> cdMapper = new DocumentMapper<>(CD.class, CDDTO.class);
               DocumentMapper<DVD, DVDDTO> dvdMapper = new DocumentMapper<>(DVD.class, DVDDTO.class);


               System.out.println("\n=== TEST: EMPRUNT IMPOSSIBLE SI AUCUN EXEMPLAIRE DISPONIBLE ===");

                // Créer un document avec 0 exemplaires
               Livre livreNonDisponible = new Livre(
                       0, // ID sera généré automatiquement
                       "Les Contemplations",
                       1, // aucun exemplaire disponible
                       "978-2070413119",
                       "Victor Hugo",
                       "Gallimard",
                       512
               );
               livreDAO.save(livreNonDisponible);
               System.out.println("Livre créé sans exemplaires: " + livreNonDisponible);

                // Tentative d'emprunt
               try {
                   System.out.println("Tentative d'emprunt du livre: " + livreNonDisponible.getTitre());

                   // Vérifier la disponibilité
                   if (!livreNonDisponible.verifieDisponibilite()) {
                       System.out.println("❌ Emprunt impossible: aucun exemplaire disponible pour \""
                               + livreNonDisponible.getTitre() + "\"");
                   } else {
                       System.out.println("✅ Emprunt réussi pour le livre: " + livreNonDisponible.getTitre());
                   }
               } catch (Exception e) {
                   System.out.println("Erreur lors de la tentative d'emprunt: " + e.getMessage());
               }



           }
       }