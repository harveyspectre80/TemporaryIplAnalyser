package iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Type;

public class IplAnalyserTest {
    public static String IPL_CENSUS_CSV_MOSTRUNS_FILEPATH = "C:\\Users\\Hemil\\Desktop\\IplAnalysermockito\\TemporaryIplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    public static String WRONG_CSV_FILE_PATH = "/home/admin1/iplanalyserproblem/src/test/resources/IPL2019FactsheetMostRuns.cv";
    public static String IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH = "C:\\Users\\Hemil\\Desktop\\IplAnalysermockito\\TemporaryIplAnalyser\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";



    @Test
    public void givenIplCenusMostRunsCsvFile_ReturnCorrectNoOfRecords() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            int numOfRecords = censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            Assert.assertEquals(100,numOfRecords);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIplCensusMostRunsData_WithWrongFile_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,WRONG_CSV_FILE_PATH);
        } catch (IplAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(IplAnalyserException.ExceptionType.NO_CENSUS_DATA,e.type);
        }
    }

    @Test
    public void givenIplCensusMostRunsData_WithWrongDelimeter_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.ISSUE_IN_FILE,e.type);
        }
    }

    @Test
    public void givenIplCensusMostRunsData_WithWrongHeader_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.ISSUE_IN_FILE,e.type);
        }
    }

    @Test
    public void givenIplCensusMostRunsData_WithWrongFileType_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,WRONG_CSV_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.NO_CENSUS_DATA,e.type);
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopBattingAverages_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.AVERAGE);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("MS Dhoni", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopBattingStrikeRates_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl( SortedField.Field.STRIKE_RATE);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("Ishant Sharma", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostSixAndFours_WhenSortedOnIt()  {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.SIXES_AND_FOURS);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("Andre Russell", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostSixAndFoursWithStrikingRate_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.SIXES_AND_FOURS_WITH_STRIKERATE);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("Andre Russell", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }

    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostAverageWithStrikingRate_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            String playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.BEST_AVERAGE_WITH_STRIKERATE);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("MS Dhoni", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostRunsData_ShouldReturnTopPlayersWithMostAverageWithBestAverage_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Batting);
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Batting));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            String playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.MAXIMUM_RUNS_WITH_BEST_AVERAGE);
            IplAnalyserRunsCsv[] BattingType = new Gson().fromJson(playersData, IplAnalyserRunsCsv[].class);
            Assert.assertEquals("David Warner", BattingType[BattingType.length-1].player);
        } catch (IplAnalyserException e) {
            System.out.println(e.type);
        }
    }

    @Test
    public void givenIplCenusMostWicketsFile_ReturnCorrectNoOfRecords() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            int numOfRecords = censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH);
            Assert.assertEquals(99,numOfRecords);
        } catch (IplAnalyserException e) {
            System.out.println(e.type);
        }
    }

    @Test
    public void givenIplCensusMostWicketsData_WithWrongFile_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,WRONG_CSV_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.NO_CENSUS_DATA,e.type);
        }
    }

    @Test
    public void givenIplCensusMostWickets_WithWrongDelimeter_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Batting,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.ISSUE_IN_FILE,e.type);
        }
    }

    @Test
    public void givenIplCensusMostWicketsData_WithWrongHeader_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
        } catch (IplAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(IplAnalyserException.ExceptionType.ISSUE_IN_FILE,e.type);
        }
    }

    @Test
    public void givenIplCensusMostWicketsData_WithWrongFileType_ShouldThrowException() {
        try {
            IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IplAnalyserException.class);
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,WRONG_CSV_FILE_PATH);
        } catch (IplAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(IplAnalyserException.ExceptionType.NO_CENSUS_DATA,e.type);
        }
    }

    @Test
    public void givenIPLCensusMostWicketsData_ShouldReturnTopBowlingAverages_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.BALLING_AVERAGE);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Prasidh Krishna", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostWicketsData_ShouldReturnTopBowlingStrikeRates_WhenSortedOnIt(){
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.BEST_BALLING_STRIKE_RATE);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostWicketsData_ShouldReturnTopBowlingEconomy_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            String playersData =  censusAnalyser.getSortedDataOfIpl(SortedField.Field.BOWLING_ECONOMY);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Ben Cutting", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostWicketsData_ShouldReturnTopBallingStrikeratesWith5WicketsAnd4WicketsAverage_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.BEST_STRIKINGRATE_WITH_5WICKETS_AND_4WICKETS);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Lasith Malinga", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostWicketsData_ShouldReturnTopBallingStrikeratesWithBestBallingAverage_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.BEST_BALLING_AVERAGE_WITH_STRIKERATE);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Prasidh Krishna", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostWicketsData_ShouldReturnTopWicketTakerWithBestBallingAverage_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.BEST_BALLING_AVERAGE_WITH_MAXIMUM_WICKETS);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Imran Tahir", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostWicketsDataWithTopRunsData_ShouldReturnBestAveragePlayer_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        String playersData = null;
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            playersData = censusAnalyser.getSortedDataOfIpl(SortedField.Field.BEST_BATTING_AND_BALLING_AVERAGES);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Prasidh Krishna", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
        }
    }

    @Test
    public void givenIPLCensusMostWicketsDataWithTopRunsData_ShouldReturnBestAllRoundPlayer_WhenSortedOnIt() {
        IplAnalyser censusAnalyser = new IplAnalyser(IplAnalyser.PlayType.Bowling);
        try {
            censusAnalyser.setIplAdapter(IplAdaptorFactory.getIplData(IplAnalyser.PlayType.Bowling));
            censusAnalyser.loadIplCensusData(IplAnalyser.PlayType.Bowling,IPL_CENSUS_CSV_MOSTWICKETS_CSV_FILEPATH,IPL_CENSUS_CSV_MOSTRUNS_FILEPATH);
            String playersData =  censusAnalyser.getSortedDataOfIpl(SortedField.Field.BEST_PLAYER_WITH_MOST_RUNS_AND_WICKETS);
            IplAnalyserBowlersCsv[] BallingPlayType = new Gson().fromJson(playersData, IplAnalyserBowlersCsv[].class);
            Assert.assertEquals("Imran Tahir", BallingPlayType[BallingPlayType.length-1].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

}

