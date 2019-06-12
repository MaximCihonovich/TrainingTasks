package javaClasses.optional;

public class Patient {
    private int id, medicalCardID;
    private String name, surname, patronymic, address;
    private String phoneNumber = "нет телефона";
    private String diagnosis = "не установлен";

    Patient(int id, int medicalCardID) {
        this.id = id;
        this.medicalCardID = medicalCardID;
        this.name = "Джон";
        this.surname = "Доу";
        this.patronymic = "неизвестно";
        this.address = "бездомный";
    }

    Patient(int id, int medicalCardID, String name, String surname, String patronymic) {
        this.id = id;
        this.medicalCardID = medicalCardID;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = "бездомный";
    }

    Patient(int id, int medicalCardID, String name, String surname, String patronymic, String phoneNumber, String address) {
        this.id = id;
        this.medicalCardID = medicalCardID;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    int getMedicalCardID (){
        return medicalCardID;
    }

    int getID (){
        return id;
    }

    String getName (){
        return name;
    }

    void setName (String name) {
        this.name = name;
    }

    String getSurname(){
        return surname;
    }

    void setSurname (String surname) {
        this.surname = surname;
    }

    String getPatronymic (){
        return patronymic;
    }

    void setPatronymic (String patronymic) {
        this.patronymic = patronymic;
    }

    String getAddress (){
        return address;
    }

    void setAddress (String address) {
        this.address = address;
    }

    String getPhoneNumber () {
        return phoneNumber;
    }

    void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getDiagnosis (){
        return diagnosis;
    }

    void setDiagnosis (String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString (){
        return String.format("%-5d %12s %12s %14s : %-40s, %12s - %-18s", medicalCardID, surname, name, patronymic, address, phoneNumber, diagnosis);
    }
}
