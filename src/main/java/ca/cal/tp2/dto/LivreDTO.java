package ca.cal.tp2.dto;

public class LivreDTO extends DocumentDTO {
    private String ISBN;
    private String auteur;
    private String editeur;
    private int nombrePages;

    // Getters and setters
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public String getEditeur() { return editeur; }
    public void setEditeur(String editeur) { this.editeur = editeur; }
    public int getNombrePages() { return nombrePages; }
    public void setNombrePages(int nombrePages) { this.nombrePages = nombrePages; }
}
