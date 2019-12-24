package iplanalyser;

import java.util.Comparator;

public class FieldComparatorForSixAndFours implements Comparator<IplCensusDAO> {

    @Override
    public int compare(IplCensusDAO iplCensusDAO, IplCensusDAO t1) {
        return (((iplCensusDAO.sixes * 6) + (iplCensusDAO.fours * 4)) - ((t1.sixes * 6) + (t1.fours * 4)));

    }
}
