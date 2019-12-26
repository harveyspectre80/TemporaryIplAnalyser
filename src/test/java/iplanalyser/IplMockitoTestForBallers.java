package iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IplMockitoTestForBallers {

    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH = "/home/admin1/Desktop/IplAnalyser/TemporaryIplAnalyser/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Mock
    IplAdapter iplAdapter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Map<String, IplDAO> iplMap;

    @Before
    public void setUp() {
        IplAnalyser iplAnalyser = new IplAnalyser();
        this.iplMap = new HashMap<>();
        iplMap.put("Hardik Pandya", new IplDAO("Hardik Pandya",4,5));
        iplMap.put("Ishant Sharma", new IplDAO("Ishant Sharma",6,7));
        iplMap.put("Dale Steyn", new IplDAO("Dale Steyn",8,4));
    }


    @Test
    public void givenIPLSampleData_ShouldReturnRightAmountOfBallersRecords()  {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserBowlersCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH)).thenReturn(iplMap);
            IplAnalyser iplAnalyser = new IplAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            int size = iplAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling, IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH);
            Assert.assertEquals(3, size);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostFourWickets_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            IplAdapter iplAdapter = mock(IplAnalyserRunsCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH)).thenReturn(iplMap);
            censusAnalyser.setIplAdapter(iplAdapter);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.MOST_FOUR_WICKETS);
            IplAnalyserBowlersCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Dale Steyn", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostFiveWickets_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            IplAdapter iplAdapter = mock(IplAnalyserRunsCsvAdapter.class);
            when(iplAdapter.loadIplData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH)).thenReturn(iplMap);
            censusAnalyser.setIplAdapter(iplAdapter);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.MOST_FIVE_WICKETS);
            IplAnalyserBowlersCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Ishant Sharma", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }
}
