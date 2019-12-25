package iplanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IplMockitoTest {

    public static String IPL_CENSUS_CSV_MOSTRUNS_FILEPATH = "C:\\Users\\Hemil\\Desktop\\IplAnalysermockito\\TemporaryIplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH = "C:\\Users\\Hemil\\Desktop\\IplAnalysermockito\\TemporaryIplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";

    @Mock
    IplAdapter iplAdapter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Map<String, IplDAO> iplMap;

    @Before
    public void setUp() {
        this.iplMap = new HashMap<>();
        IplDAO ipldao = new IplDAO();
        iplMap.put("Andre Russell", new IplDAO("Andre Russell",55,4,2));
        iplMap.put("David Warner", new IplDAO("David Warner",105,6,8));
        iplMap.put("MS Dhoni", new IplDAO("MS Dhoni",95,6,7));
    }


    @Test
    public void givenIPLSampleData_ShouldReturnRightAmountOfBatsmenRecords() throws IplAnalyserException {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserRunsCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Batting, IPL_CENSUS_CSV_MOSTRUNS_FILEPATH)).thenReturn(iplMap);
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            int size = iplAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting, IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            Assert.assertEquals(3, size);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSampleData_ShouldReturnRightAmountOfBallersRecords() throws IplAnalyserException {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserBowlersCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH)).thenReturn(iplMap);
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            int size = iplAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling, IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            Assert.assertEquals(0, size);
        } catch (IplAnalyserException e) {
        }
    }
}
