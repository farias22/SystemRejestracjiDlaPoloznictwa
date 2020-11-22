package models;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp()
    private Date registrationDate;
    private Date hospitalizationDate;
    private int pragnancyAge;
    private String firstName;
    private String lastName;
    private boolean foreigner;
    private String pesel;
    private String phoneNumber;
    private boolean scheludedRegistration;
    private String diagnosis;
    private Date lastPeriodDate;
    private String refferingDoctor;
    private String prescribingDoctor;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getHospitalizationDate() {
        return hospitalizationDate;
    }

    public void setHospitalizationDate(Date hospitalizationDate) {
        this.hospitalizationDate = hospitalizationDate;
    }

    public int getPragnancyAge() {
        return pragnancyAge;
    }

    public void setPragnancyAge(int pragnancyAge) {
        this.pragnancyAge = pragnancyAge;
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

    public boolean isForeigner() {
        return foreigner;
    }

    public void setForeigner(boolean foreigner) {
        this.foreigner = foreigner;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isScheludedRegistration() {
        return scheludedRegistration;
    }

    public void setScheludedRegistration(boolean scheludedRegistration) {
        this.scheludedRegistration = scheludedRegistration;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getLastPeriodDate() {
        return lastPeriodDate;
    }

    public void setLastPeriodDate(Date lastPeriodDate) {
        this.lastPeriodDate = lastPeriodDate;
    }

    public String getRefferingDoctor() {
        return refferingDoctor;
    }

    public void setRefferingDoctor(String refferingDoctor) {
        this.refferingDoctor = refferingDoctor;
    }

    public String getPrescribingDoctor() {
        return prescribingDoctor;
    }

    public void setPrescribingDoctor(String prescribingDoctor) {
        this.prescribingDoctor = prescribingDoctor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
