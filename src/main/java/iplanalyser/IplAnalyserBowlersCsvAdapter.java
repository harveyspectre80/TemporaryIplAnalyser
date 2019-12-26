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
        Map<String, IplDAO> iplMap = super.loadIplData(IplAnalyserBowlersCsv.class, iplFilePath);
        return iplMap;
    }
}
