package com.company.Tests;

import com.company.Parsers.CriticalParser;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;


/**
 * Created by Mauricio on 11/9/2015.
 */
public class CriticalParserTest {
    private List<String> output;

    @Before
    public void setUp() throws IOException, JSONException {
        output = CriticalParser.criticalParser();
    }
    @Test
    public void test(){
        assertEquals(4, output.size());
        assertEquals("MATH 215", output.get(1));
    }
}
