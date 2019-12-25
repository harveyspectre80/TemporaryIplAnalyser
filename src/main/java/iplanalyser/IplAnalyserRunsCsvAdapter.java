package iplanalyser;

import java.util.Map;

public class IplAnalyserRunsCsvAdapter extends IplAdapter {

    @Override
    public Map<String, IplDAO> loadIplData(IplAnalyser.PlayType playType, String... iplFilePath) throws IplAnalyserException {
        Map<String, IplDAO> iplMap = super.loadIplData(IplAnalyserRunsCsv.class, iplFilePath[0]);
        return iplMap;
    }
}
