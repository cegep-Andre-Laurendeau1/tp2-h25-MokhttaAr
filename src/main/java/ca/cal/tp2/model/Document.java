package ca.cal.tp2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DOCUMENTS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCUMENT_ID")
    protected long documentID;

    @Column(name = "TITRE", nullable = false)
    protected String titre;

    @Column(name = "NOMBRE_EXEMPLAIRES", nullable = false)
    protected int nombreExemplaires;

    public Document() {}

    public Document(long documentID, String titre, int nombreExemplaires) {
        this.documentID = documentID;
        this.titre = titre;
        this.nombreExemplaires = nombreExemplaires;
    }

    public long getDocumentID() {
        return documentID;
    }
    public String getTitre() {
        return titre;
    }
    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public boolean verifieDisponibilite() {
        return nombreExemplaires > 0;
    }

    public void decrementExemplaires() {
        if (nombreExemplaires > 0) {
            nombreExemplaires--;
        }
    }

    public void incrementExemplaires() {
        nombreExemplaires++;
    }

    @Override
    public String toString() {
        return "Document [documentID=" + documentID + ", titre=" + titre
                + ", nombreExemplaires=" + nombreExemplaires + "]";
    }
}
