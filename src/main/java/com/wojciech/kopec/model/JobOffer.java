package com.wojciech.kopec.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "JOB_OFFERS")
public class JobOffer {
    @Id
    @GeneratedValue
    private int id;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String position;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String companyName;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String description;
    @Column(columnDefinition = "DOUBLE")
    private Double workingHours;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String contractType;
    @Column(columnDefinition = "INT(2)")
    private Integer requiredYearsOfExperience;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "JOB_OFFER_REQUIREMENTS", joinColumns = @JoinColumn(name = "id"))
    private List<String> requiredQualifications;
    @Column(columnDefinition = "INT(5)")
    private Integer minSalary;
    @Column(columnDefinition = "INT(5)")
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