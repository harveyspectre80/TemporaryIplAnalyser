package iplanalyser;

import com.Builder.CSVBuilderException;
import com.Builder.CSVBuilderFactory;
import com.Builder.ICVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IplAdapter {

    Map<String, IplCensusDAO> iplMap = new HashMap<>();
    public abstract Map<String, IplCensusDAO> loadIplData(String... iplFilePath) throws IplAnalyserException;
    protected  <E> Map<String, IplCensusDAO> loadIplData(Class<E> iplClass, String... csvFilePath)
            throws IplAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(csvFilePath[0]))))
        {
            ICVBuilder csvbuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> iplFileIterator = csvbuilder.getCSVFILEIterator(reader, iplClass);
            Iterable<E> iplFileIterable = () -> iplFileIterator;
            if (iplClass.getName().equals("iplanalyser.IplAnalyserRunsCsv"))
            {
                StreamSupport.stream(iplFileIterable.spliterator(), false)
                        .map(IplAnalyserRunsCsv.class::cast)
                        .forEach(iplCSV -> iplMap.put(iplCSV.player, new IplCensusDAO(iplCSV)));
            }
            else if (iplClass.getName().equals("iplanalyser.IplAnalyserBowlersCsv"))
            {
                StreamSupport.stream(iplFileIterable.spliterator(), false)
                        .map(IplAnalyserBowlersCsv.class::cast)
                        .forEach(iplCSV -> iplMap.put(iplCSV.player, new IplCensusDAO(iplCSV)));
            }
            return iplMap;
        } catch (CSVBuilderException  e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new IplAnalyserException("No Census Data", IplAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        catch (RuntimeException e)
        {
            throw new IplAnalyserException("There Is Issue In File", IplAnalyserException.ExceptionType.ISSUE_IN_FILE);
        }
        return null;
    }
}
