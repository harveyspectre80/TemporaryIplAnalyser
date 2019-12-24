package iplanalyser;

import java.util.Map;

public class IplAnalyserRunsCsvAdapter extends IplAdapter {

    @Override
    public Map<String, IplCensusDAO> loadIplData(String... iplFilePath) throws IplAnalyserException {
        Map<String, IplCensusDAO > iplMap = super.loadIplData(IplAnalyserRunsCsv.class, iplFilePath[0]);
        return iplMap;

    }
}
