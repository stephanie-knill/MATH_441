package com.company.Tests;

import com.company.Model.FindFeasible;
import com.company.Model.Period;
import com.company.Parsers.PeriodParser;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mauricio on 11/9/2015.
 */
public class FindFeasibleTests {
    List<Period> bank;

    @Before
    public void setUp() throws IOException, JSONException {
        bank = PeriodParser.periodsParser();
        //FindFeasible x = new FindFeasible(bank);
        //feasible = x.feasibleResult();
    }
    @Test
    public void feasibilityTests() throws IOException, JSONException {
        int i=0;
        int z=25;
        while (i<z){
            FindFeasible x = new FindFeasible(bank);
            List<Period> feasible = x.feasibleResult();
            for (Period p: feasible){
                assertNotNull(p.getName());
                assertNotNull(p.getY());
                assertNotNull(p.getX());
                assertNotNull(p.getTime());
            }
            i++;
        }
    }

}
