package com.wojciech.kopec.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PRZEDSIĘBIORSTWA")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT(11) UNSIGNED")
    private int id;
    @NotNull
    @Size(min = 3, max = 60)
    @Column(name = "NAZWA", unique = true)
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d*")
    @Column(name = "NIP", unique = true)
    private String NIP;

    @NotNull
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\d*")
    @Column(name = "REGON", unique = true)
    private String REGON;

    @NotNull
    @Size(min = 3, max = 30)
    @Pattern(regexp = "[\\p{L}]*")
    @Column(name = "MIASTO")
    private String city;

    @NotNull
    @Size(min = 3, max = 60)
    @Pattern(regexp = "[[-\"/.' \\d\\p{L}]*")
    @Column(name = "ADRES_ZAMIESZKANIA")
    private String address;

    @NotNull
    @Size(min = 7, max = 13)
    @Pattern(regexp = "[+]?\\d*")
    @Column(name = "NUMER_KONTAKTOWY", unique = true)
    private String contactNumber;

    @NotNull
    @Size(min = 6, max = 30)
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    @Column(name = "ADRES_EMAIL", unique = true)
    private String emailAddress;


    public Company() {
    }

    public Company(String name, String NIP, String REGON, String city,
                   String address, String contactNumber, String emailAddress) {
        this.name = name;
        this.NIP = NIP;
        this.REGON = REGON;
        this.city = city;
        this.address = address;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getREGON() {
        return REGON;
    }

    public void setREGON(String REGON) {
        this.REGON = REGON;
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
}