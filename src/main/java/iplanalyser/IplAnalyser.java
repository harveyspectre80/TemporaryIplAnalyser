package iplanalyser;

import com.Builder.CSVBuilderException;
import com.Builder.CSVBuilderFactory;
import com.Builder.ICVBuilder;
import com.google.gson.Gson;

import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {

    public enum PlayType { Batting , Bowling };
    Map<String , IplCensusDAO> iplMap = new HashMap<>();
    private PlayType playType;

    public IplAnalyser(PlayType playType)
    {
        this.playType = playType;
    }

    public int loadIplCensusData(PlayType playType, String... iplFilePath) throws IplAnalyserException {
        iplMap = IplAdaptorFactory.getIplData(playType).loadIplData(iplFilePath);
        return iplMap.size();
    }

    public String getSortedDataOfIpl(SortedField.Field field) throws IplAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IplAnalyserException("No Census Data", IplAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IplCensusDAO> censusComparator = SortedField.getField(field);
        ArrayList iplCensusDAOS = iplMap.values().stream()
                                 .sorted(censusComparator)
                                 .map(iplCensusDAO -> iplCensusDAO.getIPLDTO(playType))
                                 .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(iplCensusDAOS);
    }

}


