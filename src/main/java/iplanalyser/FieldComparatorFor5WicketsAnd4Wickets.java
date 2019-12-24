package iplanalyser;

import java.util.Comparator;

public class FieldComparatorFor5WicketsAnd4Wickets implements Comparator<IplCensusDAO> {

    @Override
    public int compare(IplCensusDAO iplCensusDAO, IplCensusDAO t1) {
        return (((iplCensusDAO.fiveWickets * 5) + (iplCensusDAO.fourWickets * 4)) - ((t1.fiveWickets * 5) + (t1.fourWickets * 4)));

    }
}
