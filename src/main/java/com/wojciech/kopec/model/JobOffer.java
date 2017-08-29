package com.wojciech.kopec.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OFERTY_PRACY")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT(11) UNSIGNED")
    private int id;

    @NotNull
    @Size(min = 15, max = 60)
    @Column(name = "STANOWISKO")
    private String position;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAZWA_PRZEDSIĘBIORSTWA")
    private String companyName;

    @NotNull
    @Size(min = 30)
    @Column(name = "OPIS_OFERTY")
    private String description;

    @NotNull
    @Min(0)
    @Max(1)
    @Column(name = "WYMIAR_ETATU")
    private Double workingHours;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "TYP_UMOWY")
    private String contractType;

    @NotNull
    @Min(0)
    @Column(name = "WYMAGANY_STAŻ_LATA", precision = 2)
    private Integer requiredYearsOfExperience;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "OFERTY_PRACY_WYMAGANIA", joinColumns = @JoinColumn(name = "ID"))
    private List<String> requiredQualifications;

    @NotNull
    @Min(0)
    @Max(50000)
    @Column(name = "PENSJA_MINIMALNA")
    private Integer minSalary;

    @NotNull
    @Min(0)
    @Max(50000)
    @Column(name = "PENSJA_MAKSYMALNA")
    private Integer maxSalary;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public JobOffer() {
        creationDate = new Date();
    }

    public JobOffer(String position, String companyName, String description, Double workingHours, String contractType,
                    Integer requiredYearsOfExperience, ArrayList<String> requiredQualifications, Integer minSalary, Integer maxSalary) {
        this();
        this.position = position;
        this.companyName = companyName;
        this.description = description;
        this.workingHours = workingHours;
        this.contractType = contractType;
        this.requiredYearsOfExperience = requiredYearsOfExperience;
        this.requiredQualifications = requiredQualifications;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Double workingHours) {
        this.workingHours = workingHours;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Integer getRequiredYearsOfExperience() {
        return requiredYearsOfExperience;
    }

    public void setRequiredYearsOfExperience(Integer requiredYearsOfExperience) {
        this.requiredYearsOfExperience = requiredYearsOfExperience;
    }

    public List<String> getRequiredQualifications() {
        return requiredQualifications;
    }

    public void setRequiredQualifications(ArrayList<String> requiredQualifications) {
        this.requiredQualifications = requiredQualifications;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date created) {
        this.creationDate = created;
    }
}