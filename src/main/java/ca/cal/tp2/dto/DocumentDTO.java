package ca.cal.tp2.dto;

public class DocumentDTO {
    private long documentID;
    private String titre;
    private int nombreExemplaires;


    public long getDocumentID() { return documentID; }
    public void setDocumentID(long documentID) { this.documentID = documentID; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public int getNombreExemplaires() { return nombreExemplaires; }
    public void setNombreExemplaires(int nombreExemplaires) { this.nombreExemplaires = nombreExemplaires; }
}
