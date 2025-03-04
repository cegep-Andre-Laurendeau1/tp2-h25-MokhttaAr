package ca.cal.tp2.dto;

import java.util.Date;

public class EmpruntDetailDTO {
    private long lineItemID;
    private Date dateRetourPrevue;
    private Date dateRetourActuelle;
    private String status;
    private String documentTitre;

    // Getters and setters
    public long getLineItemID() { return lineItemID; }
    public void setLineItemID(long lineItemID) { this.lineItemID = lineItemID; }
    public Date getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(Date dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
    public Date getDateRetourActuelle() { return dateRetourActuelle; }
    public void setDateRetourActuelle(Date dateRetourActuelle) { this.dateRetourActuelle = dateRetourActuelle; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDocumentTitre() { return documentTitre; }
    public void setDocumentTitre(String documentTitre) { this.documentTitre = documentTitre; }
}
