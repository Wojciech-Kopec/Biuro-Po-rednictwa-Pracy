package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
    @Table(name = "CANDIDATES")
    public class Candidate {
        @Id
        @GeneratedValue
        private int id;
        @Column(columnDefinition = "VARCHAR(30) NOT NULL")
        private String firstName;
        @Column(columnDefinition = "VARCHAR(30) NOT NULL")
        private String lastName;
        @Column(columnDefinition = "BIGINT(11) NOT NULL UNIQUE")
        private Long PESEL;
        @Column(columnDefinition = "CHAR(1)")
        private Character sex;
        @Column(columnDefinition = "VARCHAR(30) NOT NULL")
        private String city;
        @Column(columnDefinition = "VARCHAR(30) NOT NULL")
        private String address;
        @Column(columnDefinition = "BIGINT(9) NOT NULL")
        private Long contactNumber;
        @Column(columnDefinition = "VARCHAR(30) NOT NULL")
        private String emailAddress;
        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "CANDIDATE_SKILLS", joinColumns = @JoinColumn(name = "id"))
        private List<String> experiences;

    public Candidate() {
    }

    public Candidate(String firstName, String lastName, Long PESEL, Character sex, String city,
                     String address, Long contactNumber, String emailAddress, ArrayList<String> experiences) {
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

    public Long getPESEL() {
        return PESEL;
    }

    public void setPESEL(Long PESEL) {
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

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
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
