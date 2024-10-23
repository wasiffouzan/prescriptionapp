package com.rmit;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PrescriptionTest {

    @Test
    public void testAddPrescriptionValid() {
        Prescription prescription = new Prescription(
                1,
                "Root",
                "Smith",
                "125 JOE Street, WAVERL, 30145, ENGLAND",
                10.0f,
                90.0f,
                -1.0f,
                new Date(),
                "Dr. Alice Smith"
        );
        boolean result = prescription.addPrescription();
        assertTrue(result); // Expected to pass as all inputs are valid
    }

    @Test
    public void testAddPrescriptionInvalidFirstName() {
        Prescription prescription = new Prescription(
                1,
                "Al",
                "Smith",
                "125 JOE Street, WAVERL, 30145, ENGLAND",
                10.0f,
                90.0f,
                -1.0f,
                new Date(),
                "Dr. Alice Smith"
        );
        boolean result = prescription.addPrescription();
        assertFalse(result); // Expected to fail as first name is too short
    }

    @Test
    public void testAddPrescriptionInvalidAddress() {
        Prescription prescription = new Prescription(
                1,
                "Root",
                "Smith",
                "Short Addr",
                10.0f,
                90.0f,
                -1.0f,
                new Date(),
                "Dr. Alice Smith"
        );
        boolean result = prescription.addPrescription();
        assertFalse(result); // Expected to fail as address is too short
    }

    @Test
    public void testAddRemarkValid() {
        Prescription prescription = new Prescription(
                1,
                "Root",
                "Smith",
                "125 JOE Street, WAVERL, 30145, ENGLAND",
                10.0f,
                90.0f,
                -1.0f,
                new Date(),
                "Dr. Alice Smith"
        );
        boolean result = prescription.addRemark("Great service and friendly staff", "client");
        assertTrue(result); // Expected to pass as remark meets word and category requirements
    }

    @Test
    public void testAddRemarkInvalidRemarkTooShort() {
        Prescription prescription = new Prescription(
                1,
                "ROOT",
                "Smith",
                "125 JOE Street, WAVERL, 30145, ENGLAND",
                10.0f,
                90.0f,
                -1.0f,
                new Date(),
                "Dr. Alice Smith"
        );
        boolean result = prescription.addRemark("Good", "client");
        assertFalse(result); // Expected to fail as the remark is too short
    }

    @Test
    public void testAddRemarkExceedLimit() {
        Prescription prescription = new Prescription(
                1,
                "Root",
                "Smith",
                "125 JOE Street, WAVERL, 30145, ENGLAND",
                10.0f,
                90.0f,
                -1.0f,
                new Date(),
                "Dr. Alice Smith"
        );
        prescription.addRemark("First remark with enough words to be valid", "client");
        prescription.addRemark("Second remark with enough words also valid", "optometrist");
        boolean result = prescription.addRemark("Third remark with enough words to be valid", "client");
        assertFalse(result); // Expected to fail as the remark limit is exceeded
    }
}
