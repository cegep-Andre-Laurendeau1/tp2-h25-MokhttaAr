package ca.cal.tp2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CDS")
public class CD extends Document {
    @Column(name = "ARTISTE")
    private String artiste;

    @Column(name = "DUREE")
    private int duree;

    @Column(name = "GENRE")
    private String genre;

    public CD() {}

    public CD(long documentID, String titre, int nombreExemplaires,
              String artiste, int duree, String genre) {
        super(documentID, titre, nombreExemplaires);
        this.artiste = artiste;
        this.duree = duree;
        this.genre = genre;
    }

    public String getArtiste() {
        return artiste;
    }
    public int getDuree() {
        return duree;
    }
    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "CD [documentID=" + documentID + ", titre=" + titre
                + ", artiste=" + artiste + "]";
    }
}
