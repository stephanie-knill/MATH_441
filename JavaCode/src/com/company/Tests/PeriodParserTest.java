package com.company.Tests;
import com.company.Model.Period;
import com.company.Parsers.PeriodParser;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Created by Mauricio on 11/9/2015.
 */
public class PeriodParserTest {
    private List<Period> output;

    @Before
    public void setUP() throws IOException, JSONException {
        output = PeriodParser.periodsParser();
    }
    @Test
    public void test(){
        assertEquals(16, output.size());
        assertEquals("MATH 215", output.get(7).getName());
    }
}
