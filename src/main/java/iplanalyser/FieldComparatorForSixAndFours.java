package iplanalyser;

import java.util.Comparator;

public class FieldComparatorForSixAndFours implements Comparator<IplDAO> {

    @Override
    public int compare(IplDAO iplDAO, IplDAO t1) {
        return (((iplDAO.sixes * 6) + (iplDAO.fours * 4)) - ((t1.sixes * 6) + (t1.fours * 4)));

    }
}
