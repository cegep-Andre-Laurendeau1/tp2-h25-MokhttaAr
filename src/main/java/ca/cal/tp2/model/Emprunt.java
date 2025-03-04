package ca.cal.tp2.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EMPRUNTS")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BORROW_ID")
    private long borrowID;

    @Column(name = "DATE_EMPRUNT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmprunt;

    @Column(name = "STATUS")
    private String status;

    //verifier si le fetch est bien LAZY
    @OneToMany(mappedBy = "emprunt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmpruntDetail> items;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Emprunteur emprunteur;

    public Emprunt() {
        this.dateEmprunt = new Date();
        this.status = "ACTIF";
        this.items = new ArrayList<>();
    }

    public long getBorrowID() { return borrowID; }
    public Date getDateEmprunt() { return dateEmprunt; }
    public String getStatus() { return status; }
    public List<EmpruntDetail> getItems() { return items; }
    public Emprunteur getEmprunteur() { return emprunteur; }

    public void setEmprunteur(Emprunteur emprunteur) { this.emprunteur = emprunteur; }
    public void setStatus(String status) { this.status = status; }

    public void addItem(EmpruntDetail item) {
        items.add(item);
        item.setEmprunt(this);
    }

    @Override
    public String toString() {
        return "Emprunt [borrowID=" + borrowID + ", dateEmprunt=" + dateEmprunt
                + ", status=" + status + ", items=" + items + "]";
    }
}
