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
    private int pregnancyAge;
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
    private boolean active;

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

    public int getPregnancyAge() {
        return pregnancyAge;
    }

    public void setPregnancyAge(int pragnancyAge) {
        this.pregnancyAge = pragnancyAge;
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


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean activ) {
        this.active = activ;
    }

    public static class PatientBuilder {


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
        private boolean active;

        public static PatientBuilder getBuilder() {
            return new PatientBuilder();
        }

        public PatientBuilder hospitalizationDate() {
            // SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            this.hospitalizationDate = date;
            return this;
        }

        public PatientBuilder pragnancyAge(int pragnancyAge) {
            this.pragnancyAge = pragnancyAge;
            return this;
        }

        public PatientBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PatientBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PatientBuilder foreigner(boolean foreigner) {
            this.foreigner = foreigner;
            return this;
        }

        public PatientBuilder pesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public PatientBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PatientBuilder scheludedRegistration(boolean scheludedRegistration) {
            this.scheludedRegistration = scheludedRegistration;
            return this;
        }

        public PatientBuilder diagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public PatientBuilder lastPeriodDate(Date date) {
            this.lastPeriodDate = date;
            return this;
        }

        public PatientBuilder refferingDoctor(String refferingDoctor) {
            this.refferingDoctor = refferingDoctor;
            return this;
        }

        public PatientBuilder prescribingDoctor(String prescribingDoctor) {
            this.prescribingDoctor = prescribingDoctor;
            return this;
        }

        public PatientBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }
        public PatientBuilder isActive() {
            this.active = true;
            return this;
        }


        public Patient build() {
            Patient patient = new Patient();
            patient.setHospitalizationDate(this.hospitalizationDate);
            patient.setPregnancyAge(this.pragnancyAge);
            patient.setFirstName(this.firstName);
            patient.setLastName(this.lastName);
            patient.setForeigner(this.foreigner);
            patient.setPesel(patient.isForeigner() ? "-" : this.pesel);
            patient.setPhoneNumber(this.phoneNumber);
            patient.setScheludedRegistration(this.scheludedRegistration);
            patient.setDiagnosis(this.diagnosis);
            patient.setLastPeriodDate(this.lastPeriodDate);
            patient.setRefferingDoctor(this.refferingDoctor);
            patient.setPrescribingDoctor(this.prescribingDoctor);
            patient.setComment(this.comment);
            patient.setActive(this.active);
            return patient;
        };

    }


}
