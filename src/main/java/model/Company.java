package model;

import javax.persistence.*;

@Entity
@Table(name = "COMPANIES")
public class Company {
    @Id
    @GeneratedValue
    private int id;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL UNIQUE")
    private String name;
    @Column(columnDefinition = "BIGINT(10) NOT NULL UNIQUE")
    private long NIP;
    @Column(columnDefinition = "BIGINT(9) NOT NULL UNIQUE")
    private long REGON;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String city;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String address;
    @Column(columnDefinition = "BIGINT(9) NOT NULL")
    private long contactNumber;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String emailAddress;

    public Company() {
    }

    public Company(String name, Long NIP, long REGON, String city,
                   String address, long contactNumber, String emailAddress) {
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

    public long getNIP() {
        return NIP;
    }

    public void setNIP(long NIP) {
        this.NIP = NIP;
    }

    public long getREGON() {
        return REGON;
    }

    public void setREGON(long REGON) {
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

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
