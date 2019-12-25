package iplanalyser;

import java.util.Comparator;

public class FieldComparatorFor5WicketsAnd4Wickets implements Comparator<IplDAO> {

    @Override
    public int compare(IplDAO iplDAO, IplDAO t1) {
        return (((iplDAO.fiveWickets * 5) + (iplDAO.fourWickets * 4)) - ((t1.fiveWickets * 5) + (t1.fourWickets * 4)));

    }
}
