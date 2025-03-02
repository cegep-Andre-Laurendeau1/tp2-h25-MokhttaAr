package ca.cal.tp2.model;

import jakarta.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "EMPRUNT_DETAILS")
public class EmpruntDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ITEM_ID")
    private long lineItemID;

    @Column(name = "DATE_RETOUR_PREVUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetourPrevue;

    @Column(name = "DATE_RETOUR_ACTUELLE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetourActuelle;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "BORROW_ID")
    private Emprunt emprunt;

    public EmpruntDetail() {
        // Set default loan period (14 days)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        this.dateRetourPrevue = calendar.getTime();
        this.status = "EMPRUNTE";
    }

    // Getters and setters
    public long getLineItemID() { return lineItemID; }
    public Date getDateRetourPrevue() { return dateRetourPrevue; }
    public Date getDateRetourActuelle() { return dateRetourActuelle; }
    public String getStatus() { return status; }
    public Document getDocument() { return document; }
    public Emprunt getEmprunt() { return emprunt; }

    public void setDocument(Document document) { this.document = document; }
    public void setEmprunt(Emprunt emprunt) { this.emprunt = emprunt; }

    public void setDateRetourActuelle(Date date) {
        this.dateRetourActuelle = date;
        this.status = "RETOURNE";
    }

    @Override
    public String toString() {
        return "EmpruntDetail [lineItemID=" + lineItemID + ", dateRetourPrevue="
                + dateRetourPrevue + ", dateRetourActuelle=" + dateRetourActuelle
                + ", status=" + status + ", document=" + document + "]";
    }
}
