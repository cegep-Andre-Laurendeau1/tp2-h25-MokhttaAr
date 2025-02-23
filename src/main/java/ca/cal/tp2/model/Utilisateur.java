package ca.cal.tp2.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    protected long userID;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    protected String email;

    @Column(name = "PHONE_NUMBER")
    protected String phoneNumber;

    public Utilisateur() {}

    public Utilisateur(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getUserID() {
        return userID;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }


    @Override
    public String toString() {
        return "Utilisateur [userID=" + userID + ", name=" + name
                + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }
}
