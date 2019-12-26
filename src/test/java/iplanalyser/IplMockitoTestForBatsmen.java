package iplanalyser;

import com.google.gson.Gson;
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

public class IplMockitoTestForBatsmen {

    public static String IPL_CENSUS_CSV_MOSTRUNS_FILEPATH = "/home/admin1/Desktop/IplAnalyser/TemporaryIplAnalyser/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Mock
    IplAdapter iplAdapter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Map<String, IplDAO> iplMap;

    @Before
    public void setUp() {
        this.iplMap = new HashMap<>();
        iplMap.put("Neil", new IplDAO("Neil",5,7,2));
        iplMap.put("Hemil", new IplDAO("Hemil",105,6,8));
        iplMap.put("Poojan", new IplDAO("Poojan",155,6,7));
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
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostRuns_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        String playersData = null;
        try {
            IplAdapter iplAdapter = mock(IplAnalyserRunsCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Batting, IPL_CENSUS_CSV_MOSTRUNS_FILEPATH)).thenReturn(iplMap);
            censusAnalyser.setIplAdapter(iplAdapter);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.MOST_RUNS);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("Poojan", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostFours_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        String playersData = null;
        try {
            IplAdapter iplAdapter = mock(IplAnalyserRunsCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Batting, IPL_CENSUS_CSV_MOSTRUNS_FILEPATH)).thenReturn(iplMap);
            censusAnalyser.setIplAdapter(iplAdapter);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.MOST_FOURS);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("Neil", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostSixes_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        String playersData = null;
        try {
            IplAdapter iplAdapter = mock(IplAnalyserRunsCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Batting, IPL_CENSUS_CSV_MOSTRUNS_FILEPATH)).thenReturn(iplMap);
            censusAnalyser.setIplAdapter(iplAdapter);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.MOST_SIXES);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("Hemil", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }
}
