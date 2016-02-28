package com.company.Tests;

import com.company.Parsers.CriticalParser;
import com.company.Parsers.PeriodParser;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import com.company.Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mauricio on 11/8/2015.
 */
public class ScheduleProblemTests {
    ScheduleProblem problem;

    @Before
    public void setUp() throws IOException, JSONException {
        List<Period> bank = PeriodParser.periodsParser();
        List<Period> start = new ArrayList<Period>();
        start.add(bank.get(0));
        start.add(bank.get(6));
        start.add(bank.get(10));
        start.add(bank.get(13));

        List<String> critical = CriticalParser.criticalParser();
        problem = new ScheduleProblem(bank, start, critical);

    }

    @Test
    public void isTimeOccupiedTests() {
        assertTrue(problem.isTimeOccupied(2));
        assertFalse(problem.isTimeOccupied(55));
    }

    @Test
    public void addAndRemoveTests() {
        Period k = new Period("Math 200", 103, 1000, 1000, 2);
        assertEquals(4, problem.getCurrentSchedule().size());
        problem.addPeriod(k);
        assertEquals(4, problem.getCurrentSchedule().size());
        Period k2 = new Period("Math 200", 2, 1000, 1000, 33);
        problem.addPeriod(k2);
        assertEquals(5, problem.getCurrentSchedule().size());
        problem.removePeriod(k2);
        assertEquals(4, problem.getCurrentSchedule().size());
    }

    @Test
    public void criticalInCurrentTest() {
        assertTrue(problem.criticalInCurrent("MATH 200"));
        assertFalse(problem.criticalInCurrent("asdf"));
    }

    @Test
    public void validityTests() {
        assertTrue(problem.hasAllSubjects());
        Period k = problem.getCurrentSchedule().get(1);
        problem.removePeriod(k);
        assertFalse(problem.hasAllSubjects());
    }

    @Test
    public void valueTests() {
        //assertEquals(8, problem.problemValue());
    }

    @Test
    public void filterBankTest() {
        List<Period> y = new ArrayList<Period>();
        List<Period> bank = problem.getBank();
        y.add(bank.get(0));
        y.add(bank.get(1));
        y.add(bank.get(2));
        y.add(bank.get(3));
        y.add(bank.get(4));
        y.add(bank.get(5));
        assertEquals(y, problem.filterBank("MATH 200"));
    }

    @Test
    public void randomToIterateTest() {
        assertEquals(problem.randomToIterate().getClass(), Period.class);
    }

    @Test
    public void namesInCurrentScheduleTest() throws IOException, JSONException {
        List<String> all = CriticalParser.criticalParser();
        assertEquals(all, problem.namesInCurrentSchedule());
    }

    @Test
    public void missingSubjectTest() {
        assertTrue(problem.hasAllSubjects());
        problem.removePeriod(problem.getCurrentSchedule().get(0));
        assertEquals("MATH 200", problem.missingSubject(""));
    }

    @Test
    public void iteratorTests() {
        int z = 25000;
        int i = 0;
        while (  i<z){
            problem.iterator();
            assertTrue(problem.hasAllSubjects());
            for (Period p: problem.getCurrentSchedule()){
                assertNotNull(p.getName());
                assertNotNull(p.getTime());
                assertNotNull(p.getX());
                assertNotNull(p.getY());
            }
            i++;
        }

    }
    @Test
    public void itTest(){
        int i = 0;
        int z = 275;
        int count = 0;
        List<Period> start = new ArrayList<Period>();
        for (Period p: problem.getCurrentSchedule()){
            start.add(p);
        }
        while (i<z){
            problem.iterator();
            if (start.equals(problem.getCurrentSchedule())){
                count++;
            }
            i++;
        }
         assertTrue(count<z/50);
    }
/*
    @Test
    public void randomToIterateTest() {
        problem.iterator();
        assertTrue(problem.hasAllSubjects());
        Random q = new Random();
        System.out.println("random is " + q.nextInt(4));
    }
    */

}


