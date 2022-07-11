package org.example.volunteers;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VolunteerTest {

    @BeforeAll
    public static void globalSetUp() {
    }

    @BeforeEach
    public void setUp() {
        System.out.println("");
    }

    @Test
    public void shouldAlwaysPass() {
        assertTrue(true);
    }

    @Test
    public void shouldHaveValidPhoneNumber() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                                                "matli", "matisse.livain@gmail.com"
                                                , "+33052658575");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(true,hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneNumberWithHyphen() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+555-2658575");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false,hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneNoPlus() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "33052658575");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false, hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneTooLong() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305265857512345");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false,hasValidPhone);
    }

    @Test
    public void shouldHaveInvalidPhoneTooShort() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+");
        boolean hasValidPhone = testVolunteer.hasValidPhoneNumber();
        assertEquals(false,hasValidPhone);
    }

    @Test
    public void shouldHaveValidMail() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(true, hasValidMail);
    }

    @Test
    public void shouldHaveInvalidMailArobase() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livaingmail.com"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(false, hasValidMail);
    }

    @Test
    public void shouldHaveInvalidMailANoDotAtEnd() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisselivain@gmailcom"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(false, hasValidMail);
    }

    @Test
    public void shouldHaveInvalidMailANoDotAtEndWithDotBefore() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmailcom"
                , "+33052658575");
        boolean hasValidMail = testVolunteer.hasValidMail();
        assertEquals(false, hasValidMail);
    }

    @Test
    public void shouldHaveValidFullName() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(true, hasFullName);
    }

    @Test
    public void shouldHaveInvalidNoFullNameNoFirstName() {
        Volunteer testVolunteer = new Volunteer("", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(false, hasFullName);
    }

    @Test
    public void shouldHaveInvalidNoFullNameNoLastName() {
        Volunteer testVolunteer = new Volunteer("Matisse", "",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(false, hasFullName);
    }

    @Test
    public void shouldHaveInvalidNoFullNameNoLastNameNoFirstName() {
        Volunteer testVolunteer = new Volunteer("", "",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        boolean hasFullName = testVolunteer.hasFullName();
        assertEquals(false, hasFullName);
    }

    @Test
    public void shouldFormatPhoneNumberHyphens() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305-2658-575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }

    @Test
    public void shouldNotFormatPhoneNumberAlreadyFormated() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }
    @Test
    public void shouldFormatPhoneNumberOpenParenthesis() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305(2658(575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }

    @Test
    public void shouldFormatPhoneNumberCloseParenthesis() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305)2658)575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }
    @Test
    public void shouldFormatPhoneNumberSlash() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305/2658/575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }

    @Test
    public void shouldFormatPhoneNumberAntiSlash() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305\\2658\\575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }

    @Test
    public void shouldFormatPhoneDot() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305.2658.575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }

    @Test
    public void shouldFormatPhoneSpace() {
        Volunteer testVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+3305 2658 575");
        testVolunteer.formatPhoneNumber();
        Volunteer verifyVolunteer = new Volunteer("Matisse", "LIVAIN",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        assertEquals(verifyVolunteer, testVolunteer);
    }


    @AfterEach
    public void tearDown() {
        System.out.println("Ce code est exécuté après chaque test");
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("Ce code est exécuté une seule fois après l'ensemble des tests");
    }

}