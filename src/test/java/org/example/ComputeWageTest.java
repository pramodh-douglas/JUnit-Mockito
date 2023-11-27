package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class ComputeWageTest {

    ComputeWage obj = new ComputeWage();
    @Mock
    BonusInterface bonus_i;
    @InjectMocks
    ComputeWage cc;

    @BeforeEach
    public void initEach() {
        ByteArrayInputStream in = new ByteArrayInputStream("John\nregular\n35".getBytes()); //hijack input stream to check whether values are accepted
        System.setIn(in);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void acceptData() {
        obj.acceptData();
        assertEquals("John", obj.getName());
        assertEquals(35, obj.getHours());
    }

    @Test
    void testException() {
        ByteArrayInputStream in = new ByteArrayInputStream("John\nregular\nxx\n35".getBytes()); //hijack output stream to check whether exception is fired when invald value is entered
        System.setIn(in);

        PrintStream myownoutput = System.out;
        ByteArrayOutputStream captor = new ByteArrayOutputStream();

        obj.acceptData();

        assertTrue(new String(captor.toByteArray()).contains("You didnt enter a valid number. "));
        System.setOut(myownoutput);
    }

    @Test
    void computeWage() {
        obj.acceptData();
        obj.computeWage();
        assertEquals(525, obj.getTotal());
    }

    @Test
    void display() {
        obj.acceptData();
        obj.computeWage();

        PrintStream myownoutput = System.out;
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));

        obj.display();

        assertEquals("The total wage of John is 525.0", captor.toString().trim());
    }

    @Test
    void addBonus() {
        obj.acceptData();
        obj.computeWage();

        Mockito.when(bonus_i.bonus(anyString())).thenReturn(2000.0);

        PrintStream myownoutput = System.out;
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));

        obj.addBonus(bonus_i);

        assertEquals("The total wage of John is 2525.0", captor.toString().trim());
    }
}