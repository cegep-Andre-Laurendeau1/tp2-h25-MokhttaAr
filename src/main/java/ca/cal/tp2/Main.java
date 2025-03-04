package ca.cal.tp2;

       import ca.cal.tp2.model.CD;
       import ca.cal.tp2.model.DVD;
       import ca.cal.tp2.model.Livre;
       import ca.cal.tp2.service.EmpruntService;
       import ca.cal.tp2.dto.LivreDTO;
       import ca.cal.tp2.dto.CDDTO;
       import ca.cal.tp2.dto.DVDDTO;
       import ca.cal.tp2.mapper.DocumentMapper;


       import java.sql.SQLException;
       import java.util.List;

public class Main {
           public static void main(String[] args) throws SQLException {
               TcpServer.createTcpServer();

               DocumentMapper<Livre, LivreDTO> livreMapper = new DocumentMapper<>(Livre.class, LivreDTO.class);
               DocumentMapper<CD, CDDTO> cdMapper = new DocumentMapper<>(CD.class, CDDTO.class);
               DocumentMapper<DVD, DVDDTO> dvdMapper = new DocumentMapper<>(DVD.class, DVDDTO.class);
               EmpruntService service = new EmpruntService();

               String nom = "Jean Dupont";
               String email = "jean.dupont@example.com";
               String telephone = "0123456789";
               service.registerEmprunteur(nom, email, telephone);

               System.out.println("Client enregistré avec succès: " + nom + ", " + email + ", " + telephone);



               Livre livre = new Livre(
                       0, // ID sera généré automatiquement
                       "Les Misérables",
                       3,
                       "978-2253096344",
                       "Victor Hugo",
                       "Le Livre de Poche",
                       1472 ,
                          1862
               );
               livreMapper.addDocument(livre);
               System.out.println("Livre ajouté avec succès: " + livre);



               CD cd = new CD(
                       0,
                       "Thriller",
                       2,
                       "Michael Jackson",
                       42,
                       "Pop"
               );
               cdMapper.addDocument(cd);
               System.out.println("CD ajouté avec succès: " + cd);


               DVD dvd = new DVD(
                       0,
                       "Le Seigneur des Anneaux",
                       4,
                       "Peter Jackson",
                       178,
                       "PG-13"
               );
               dvdMapper.addDocument(dvd);
               System.out.println("DVD ajouté avec succès: " + dvd);

               System.out.println("Enregistrement terminé.");



               System.out.println("\n=== TEST: EMPRUNT IMPOSSIBLE SI AUCUN EXEMPLAIRE DISPONIBLE ===");

                // Créer un document avec 0 exemplaires
               Livre livreNonDisponible = new Livre(
                       0, // ID sera généré automatiquement
                       "Les Contemplations",
                       0, // aucun exemplaire disponible
                       "978-2070413119",
                       "Victor Hugo",
                       "Gallimard",
                       512 ,
                          1856
               );
               livreMapper.addDocument(livreNonDisponible);
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



               System.out.println("\n=== TEST: RECHERCHE DE DOCUMENTS PAR CRITÈRES ===");

               // 1. Recherche par titre partiel
               System.out.println("\n-> Recherche de livres contenant 'Mis' dans le titre:");
               List<LivreDTO> livresParTitre = livreMapper.searchDocuments("Mis", null, null);
               livreMapper.afficherResultatsRecherche(livresParTitre, "livres");

                // 2. Recherche par auteur
               System.out.println("\n-> Recherche de documents par auteur 'Victor Hugo':");
               List<LivreDTO> livresParAuteur = livreMapper.searchDocuments(null, "Hugo", null);
               livreMapper.afficherResultatsRecherche(livresParAuteur, "livres");

                // 3. Recherche de CD par artiste
               System.out.println("\n-> Recherche de CDs par artiste 'Michael':");
               List<CDDTO> cdsParArtiste = cdMapper.searchDocuments(null, "Michael", null);
               cdMapper.afficherResultatsRecherche(cdsParArtiste, "CDs");

                // 4. Recherche de DVD par réalisateur
               System.out.println("\n-> Recherche de DVDs par réalisateur 'Jackson':");
               List<DVDDTO> dvdsParDirecteur = dvdMapper.searchDocuments(null, "Jackson", null);
               dvdMapper.afficherResultatsRecherche(dvdsParDirecteur, "DVDs");




               System.out.println("\n=== TEST: EMPRUNT DE DOCUMENT ===");

               EmpruntService empruntService = new EmpruntService();

               empruntService.registerEmprunteur("Lucas Martin", "lucas.martin@example.com", "0611223344");

               try {
                   System.out.println("Tentative d'emprunt du livre: " + livre.getTitre());
                   empruntService.emprunterDocument(livre.getDocumentID(), 1);
               } catch (SQLException e) {
                   System.out.println("Erreur: " + e.getMessage());
               }

               try {
                   System.out.println("\nTentative d'emprunt d'un document sans exemplaires disponibles:");
                   empruntService.emprunterDocument(livreNonDisponible.getDocumentID(), 2);
               } catch (SQLException e) {
                   System.out.println("Erreur: " + e.getMessage());
               }




               System.out.println("\n=== TEST: AFFICHER LES EMPRUNTS D'UN EMPRUNTEUR");

                   empruntService.afficherEmpruntsEmprunteurDTO(1);






           }

       }