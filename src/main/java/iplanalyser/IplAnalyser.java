package iplanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IplAnalyser {

    private IplAdapter iplAdapter;
    Map<String , IplDAO> iplMap;

    public IplAnalyser() {
        iplMap =  new HashMap<>();

    }

    public void setIplAdapter(IplAdapter iplAdapter) {
        this.iplAdapter = iplAdapter;
    }

    public enum PlayType { Batting , Bowling };

    private PlayType playType;

    public IplAnalyser(PlayType playType)
    {
        this.playType = playType;
    }

    public int loadIplCensusData(PlayType playType, String... iplFilePath) throws IplAnalyserException {
        this.iplMap = iplAdapter.loadIplData(playType, iplFilePath);
        return iplMap.size();
    }

    public String getSortedDataOfIpl(SortedField.Field field) throws IplAnalyserException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IplAnalyserException("No Census Data", IplAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IplDAO> censusComparator = SortedField.getField(field);
        ArrayList iplCensusDAOS = iplMap.values().stream()
                                 .sorted(censusComparator)
                                 .map(iplCensusDAO -> iplCensusDAO.getIPLDTO(playType))
                                 .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(iplCensusDAOS);
    }

}


