package ca.cal.tp2.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpruntDTO {
    private long borrowID;
    private Date dateEmprunt;
    private String status;
    private List<EmpruntDetailDTO> items;
    private EmprunteurDTO emprunteur;

    public EmpruntDTO() {
        this.items = new ArrayList<>();
    }

    // Getters and setters
    public long getBorrowID() { return borrowID; }
    public void setBorrowID(long borrowID) { this.borrowID = borrowID; }
    public Date getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(Date dateEmprunt) { this.dateEmprunt = dateEmprunt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<EmpruntDetailDTO> getItems() { return items; }
    public void setItems(List<EmpruntDetailDTO> items) { this.items = items; }
    public EmprunteurDTO getEmprunteur() { return emprunteur; }
    public void setEmprunteur(EmprunteurDTO emprunteur) { this.emprunteur = emprunteur; }

    public void addItem(EmpruntDetailDTO item) {
        items.add(item);
    }
}
