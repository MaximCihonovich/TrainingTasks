package javaClasses.optional;

import java.util.ArrayList;

public class OptionalTaskRunner {
    public static void main(String[] args) {

        Patient [] patients, patientsList;
        Patient patient1 = new Patient(1, 100);
        Patient patient2 = new Patient(2, 101, "Василий","Воробей", "Петрович");
        Patient patient3 = new Patient(3, 102, "Иван", "Кот", "Николавевич", "33322211", "ул. Игнатенко 42, кв. 22");
        Patient patient4 = new Patient(4, 103, "Олег", "Петровский", "Васильевич", "11144422", "ул. Короля 18, кв. 5");
        Patient patient5 = new Patient(5, 104, "Семён", "Карпов", "Евгеньевич", "55533366", "ул. Шугаева 9, кв. 6");
        Patient patient6 = new Patient(6, 105);
        Patient patient7 = new Patient(7, 106, "Николай", "Горелко", "Вадимович", "88822255", "ул. Кабушкина 88, кв. 42");
        Patient patient8 = new Patient(8, 107, "Григорий", "Лещенко", "Ильич");
        patient1.setDiagnosis("Простуда");
        patient3.setDiagnosis("Простуда");
        patient4.setDiagnosis("Грипп");
        patient5.setDiagnosis("Ангина");
        patient6.setDiagnosis("Простуда");
        patient8.setDiagnosis("Симмулянт");
        patients = new Patient[] {patient1, patient2, patient3, patient4, patient5, patient6, patient7, patient8};
        for (Patient patient : patients) {
            System.out.println(patient.toString());
        }

        System.out.println("\n");
        System.out.println("Пациенты с простудой :");
        patientsList = sortByDiagnosis("Простуда", patients);
        for (Patient patient : patientsList) {
            System.out.printf("%n%14s %12s %16s", patient.getSurname(), patient.getName(), patient.getPatronymic());
        }
        System.out.println("\n");
        System.out.println("Пациенты без диагноза :");
        patientsList = sortByDiagnosis("не установлен", patients);
        for (Patient patient : patientsList) {
            System.out.printf("%n%14s %12s %16s", patient.getSurname(), patient.getName(), patient.getPatronymic());
        }
        System.out.println("\n");
        System.out.println("Пациенты c номерами карт от 100 до 104");
        patientsList = sortByMedicalID(100, 104, patients);
        for (Patient patient : patientsList) {
            System.out.printf("%n%14s %12s %16s", patient.getSurname(), patient.getName(), patient.getPatronymic());
        }
    }

    private static Patient[] sortByDiagnosis (String diagnosis, Patient [] patients) {
        ArrayList<Patient> sortedByDiagnosisPatientsList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getDiagnosis().equals(diagnosis)) {
                sortedByDiagnosisPatientsList.add(patient);
            }
        }
        Patient [] sortedPatientsList = new Patient[sortedByDiagnosisPatientsList.size()];
        return sortedByDiagnosisPatientsList.toArray(sortedPatientsList);
    }

    private static Patient [] sortByMedicalID (int startID, int endID, Patient [] patients) {
        ArrayList <Patient> sortedByMedicalIDPatientsList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getMedicalCardID() >= startID && patient.getMedicalCardID() <= endID) {
                sortedByMedicalIDPatientsList.add(patient);
            }
        }
        Patient [] sortedPatientsList = new Patient[sortedByMedicalIDPatientsList.size()];
        return sortedByMedicalIDPatientsList.toArray(sortedPatientsList);
    }

}
