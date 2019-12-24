package iplanalyser;

import java.util.Map;

public class IplAnalyserBowlersCsvAdapter extends IplAdapter {

    @Override
    public Map<String, IplCensusDAO> loadIplData(String... csvFilePath) throws IplAnalyserException {
        Map<String, IplCensusDAO> iplMap = super.loadIplData(IplAnalyserBowlersCsv.class, csvFilePath[0]);
        return iplMap;

    }
}
