package ca.cal.tp2.dto;

public class CDDTO extends DocumentDTO {
    private String artiste;
    private int duree;
    private String genre;

    // Getters and setters
    public String getArtiste() { return artiste; }
    public void setArtiste(String artiste) { this.artiste = artiste; }
    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
