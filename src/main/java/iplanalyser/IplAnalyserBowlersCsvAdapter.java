package iplanalyser;

import com.Builder.CSVBuilderException;
import com.Builder.CSVBuilderFactory;
import com.Builder.ICVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplAnalyserBowlersCsvAdapter extends IplAdapter {

    @Override
    public Map<String, IplDAO> loadIplData(IplAnalyser.PlayType playType, String... iplFilePath) throws IplAnalyserException {
        Map<String, IplDAO> iplMap = super.loadIplData(IplAnalyserBowlersCsv.class, iplFilePath[0]);
        this.loadRunData(iplMap,iplFilePath);
        return iplMap;
    }

    private Map<String, IplDAO> loadRunData(Map<String, IplDAO> iplMap, String... iplFilePath) throws IplAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplFilePath[1]))) {
            ICVBuilder csvbuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplAnalyserRunsCsv> runCsvIterator = csvbuilder.getCSVFILEIterator(reader, IplAnalyserRunsCsv.class);
            Iterable<IplAnalyserRunsCsv> csvIterable = () -> runCsvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).
                    filter(csvRun -> iplMap.get(csvRun.player) != null).
                    forEach(csvRun -> iplMap.get(csvRun.player).ballingAverage = csvRun.average);
        } catch (IOException e) {
            throw new IplAnalyserException(e.getMessage(),
                    IplAnalyserException.ExceptionType.NO_CENSUS_DATA);
        } catch (RuntimeException e) {
            throw new IplAnalyserException(e.getMessage(),
                    IplAnalyserException.ExceptionType.ISSUE_IN_FILE);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return iplMap;
    }
}
