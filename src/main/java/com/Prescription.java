package com.rmit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {

    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks = new ArrayList<>();

    public Prescription(int prescID, String firstName, String lastName, String address,
            float sphere, float axis, float cylinder, Date examinationDate,
            String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    // Method to add a prescription if it meets the given conditions
    public boolean addPrescription() {
        // Validate name length (4-15 characters)
        if (firstName.length() < 4 || firstName.length() > 15) {
            System.out.println("First name validation failed: " + firstName + " length: " + firstName.length());
            return false;
        }
        if (lastName.length() < 4 || lastName.length() > 15) {
            System.out.println("Last name validation failed: " + lastName + " length: " + lastName.length());
            return false;
        }

        // Validate address length
        if (address.length() < 20) {
            System.out.println("Address validation failed: " + address + " length: " + address.length());
            return false;
        }

        // Validate sphere range
        if (sphere < -20.00 || sphere > 20.00) {
            System.out.println("Sphere validation failed: " + sphere);
            return false;
        }

        // Validate cylinder range
        if (cylinder < -4.00 || cylinder > 4.00) {
            System.out.println("Cylinder validation failed: " + cylinder);
            return false;
        }

        // Validate axis range
        if (axis < 0 || axis > 180) {
            System.out.println("Axis validation failed: " + axis);
            return false;
        }

        // Validate optometrist name length (8-25 characters including title)
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            System.out.println("Optometrist validation failed: " + optometrist + " length: " + optometrist.length());
            return false;
        }

        // Save the prescription to a file if all validations pass
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            writer.write(this.toString() + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("File writing failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a remark if it meets the given conditions
    public boolean addRemark(String remark, String category) {
        // Validate remark word count (5-20 words)
        String[] words = remark.trim().split("\\s+");
        if (words.length < 5 || words.length > 20) {
            System.out.println("Remark word count validation failed: " + words.length + " words");
            return false;
        }
        // Validate first word capitalization
        if (!Character.isUpperCase(remark.charAt(0))) {
            System.out.println("Remark capitalization validation failed");
            return false;
        }
        // Validate category
        if (!category.equals("client") && !category.equals("optometrist")) {
            System.out.println("Category validation failed: " + category);
            return false;
        }
        // Check if remark limit has been reached
        if (postRemarks.size() >= 2) {
            System.out.println("Remark limit validation failed: " + postRemarks.size() + " remarks already exist");
            return false;
        }

        // Add the remark and save it to a file
        postRemarks.add(remark);
        try (FileWriter writer = new FileWriter("review.txt", true)) {
            writer.write(remark + " (" + category + ")\n");
            return true;
        } catch (IOException e) {
            System.out.println("File writing failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "Prescription ID: " + prescID + ", Name: " + firstName + " " + lastName
                + ", Address: " + address + ", Sphere: " + sphere + ", Axis: " + axis
                + ", Cylinder: " + cylinder + ", Examination Date: " + examinationDate
                + ", Optometrist: " + optometrist;
    }
}
