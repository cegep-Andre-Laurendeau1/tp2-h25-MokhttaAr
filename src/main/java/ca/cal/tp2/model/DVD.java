package ca.cal.tp2.model;
import jakarta.persistence.*;

@Entity
@Table(name = "DVDS")
public class DVD extends Document {
    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "DUREE")
    private int duree;

    @Column(name = "RATING")
    private String rating;

    public DVD() {}

    public DVD(long documentID, String titre, int nombreExemplaires,
               String director, int duree, String rating) {
        super(documentID, titre, nombreExemplaires);
        this.director = director;
        this.duree = duree;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }
    public int getDuree() {
        return duree;
    }
    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "DVD [documentID=" + documentID + ", titre=" + titre
                + ", director=" + director + "]";
    }
}
