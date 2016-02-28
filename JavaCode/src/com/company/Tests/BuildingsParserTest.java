package com.company.Tests;
import com.company.Model.Building;
import com.company.Parsers.BuildingsParser;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mauricio on 11/9/2015.
 */
public class BuildingsParserTest {
    private List<Building> output;
    @Before
    public void setUp() throws IOException, JSONException {
        output = BuildingsParser.buildingsParser();
    }
    @Test
    public void tests(){
        assertEquals(6, output.size());
        assertEquals(output.get(0).toString(),"IKB" );
    }
}
