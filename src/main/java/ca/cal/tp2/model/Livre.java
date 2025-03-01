package ca.cal.tp2.model;
import jakarta.persistence.*;

@Entity
@Table(name = "LIVRES")
public class Livre extends Document {
    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "AUTEUR")
    private String auteur;

    @Column(name = "EDITEUR")
    private String editeur;

    @Column(name = "NOMBRE_PAGES")
    private int nombrePages;

    public Livre() {}

    public Livre(long documentID, String titre, int nombreExemplaires,
                 String ISBN, String auteur, String editeur, int nombrePages) {
        super(documentID, titre, nombreExemplaires);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nombrePages = nombrePages;
    }




    public String getISBN() {
        return ISBN;
    }
    public String getAuteur() {
        return auteur;
    }
    public String getEditeur() {
        return editeur;
    }
    public int getNombrePages() {
        return nombrePages;
    }

    @Override
    public String toString() {
        return "Livre [documentID=" + documentID + ", titre=" + titre
                + ", auteur=" + auteur + "]";
    }
}
