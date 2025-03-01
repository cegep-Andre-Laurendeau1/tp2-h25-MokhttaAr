package ca.cal.tp2.dto;

public class DVDDTO extends DocumentDTO {
    private String director;
    private int duree;
    private String rating;

    // Getters and setters
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
}
