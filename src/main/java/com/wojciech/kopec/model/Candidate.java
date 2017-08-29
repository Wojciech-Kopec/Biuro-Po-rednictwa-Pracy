package com.wojciech.kopec.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/* TODO @NotNull annos. to @NotBlank annos */
@Entity
@Table(name = "KANDYDACI")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        /* For thought: is columnDefinition really needed? */
    @Column(name = "ID", columnDefinition = "INT(11) UNSIGNED")
    private int id;

    @NotNull
    @Size(min = 3, max = 30)
    @Pattern(regexp = "\\p{L}*")
    @Column(name = "IMIĘ")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    @Pattern(regexp = "[- \\p{L}]*")
    @Column(name = "NAZWISKO")
    private String lastName;

    @NotNull
    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d*")
    @Column(name = "PESEL", unique = true)
    private String PESEL;

    @NotNull
    @Pattern(regexp = "^([MK])$")
    @Column(name = "PŁEĆ")
    private Character sex;

    @NotNull
    @Size(min = 3, max = 30)
    @Pattern(regexp = "[-' \\p{L}]*")
    @Column(name = "MIASTO")
    private String city;

    @NotNull
    @Size(min = 3, max = 60)
    @Pattern(regexp = "[-\"/.' \\d\\p{L}]*")
    @Column(name = "ADRES_ZAMIESZKANIA")
    private String address;

    @NotNull
    @Size(min = 7, max = 13)
    @Pattern(regexp = "[+]?\\d*")
    @Column(name = "NUMER_KONTAKTOWY", unique = true)
    private String contactNumber;

    @NotNull
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    @Column(name = "ADRES_EMAIL", unique = true)
    private String emailAddress;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "KANDYDACI_UMIEJĘTNOŚCI", joinColumns = @JoinColumn(name = "ID"))
    private List<String> experiences;

    public Candidate() {
    }

    public Candidate(String firstName, String lastName, String PESEL, Character sex, String city,
                     String address, String contactNumber, String emailAddress, ArrayList<String> experiences) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.PESEL = PESEL;
        this.sex = sex;
        this.city = city;
        this.address = address;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.experiences = experiences;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<String> getExperiences() {
        return experiences;
    }

    public void setExperiences(ArrayList<String> experiences) {
        this.experiences = experiences;
    }
}