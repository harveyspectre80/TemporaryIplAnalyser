package iplanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {
    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();
    enum Field
    {
        AVERAGE,STRIKE_RATE,SIXES_AND_FOURS,SIXES_AND_FOURS_WITH_STRIKERATE,BEST_AVERAGE_WITH_STRIKERATE,
        MAXIMUM_RUNS_WITH_BEST_AVERAGE,BALLING_AVERAGE,BEST_BALLING_STRIKE_RATE,BOWLING_ECONOMY,BEST_STRIKINGRATE_WITH_5WICKETS_AND_4WICKETS,
        BEST_BALLING_AVERAGE_WITH_STRIKERATE,BEST_BALLING_AVERAGE_WITH_MAXIMUM_WICKETS,BEST_BATTING_AND_BALLING_AVERAGES,BEST_PLAYER_WITH_MOST_RUNS_AND_WICKETS;
    }
    SortedField() {}
    public static Comparator getField(SortedField.Field field)
    {
        Comparator<IplCensusDAO> battingAverageComparator = Comparator.comparing(census -> census.battingAverage);
        Comparator<IplCensusDAO> strikeRateComparator = Comparator.comparing(census -> census.battingStrikeRate);
        Comparator<IplCensusDAO> runsComparator = Comparator.comparing(census -> census.runs);
        Comparator<IplCensusDAO> ballingAverageComparator = Comparator.comparing(census -> census.ballingAverage);
        Comparator<IplCensusDAO> ballingStrikeRatesComparator = Comparator.comparing(census -> census.ballingStrikeRate);
        Comparator<IplCensusDAO> ballingEconomyComparator = Comparator.comparing(census -> census.economy);
        Comparator<IplCensusDAO> wicketsComparator = Comparator.comparing(census -> census.maximumWickets);
        Comparator<IplCensusDAO> batsmenRunsComparator = Comparator.comparing(census -> census.runs);

        sortFieldComparator.put(Field.AVERAGE, battingAverageComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, strikeRateComparator);
        sortFieldComparator.put(Field.SIXES_AND_FOURS, new FieldComparatorForSixAndFours());
        sortFieldComparator.put(Field.SIXES_AND_FOURS_WITH_STRIKERATE, new FieldComparatorForSixAndFours().thenComparing(strikeRateComparator));
        sortFieldComparator.put(Field.BEST_AVERAGE_WITH_STRIKERATE , battingAverageComparator.thenComparing(strikeRateComparator));
        sortFieldComparator.put(Field.MAXIMUM_RUNS_WITH_BEST_AVERAGE, runsComparator.thenComparing(battingAverageComparator));
        sortFieldComparator.put(Field.BALLING_AVERAGE, ballingAverageComparator);
        sortFieldComparator.put(Field.BEST_BALLING_STRIKE_RATE,ballingStrikeRatesComparator);
        sortFieldComparator.put(Field.BOWLING_ECONOMY,ballingEconomyComparator);
        sortFieldComparator.put(Field.BEST_STRIKINGRATE_WITH_5WICKETS_AND_4WICKETS, new FieldComparatorFor5WicketsAnd4Wickets().thenComparing(ballingStrikeRatesComparator));
        sortFieldComparator.put(Field.BEST_BALLING_AVERAGE_WITH_STRIKERATE,ballingAverageComparator.thenComparing(ballingStrikeRatesComparator));
        sortFieldComparator.put(Field.BEST_BALLING_AVERAGE_WITH_MAXIMUM_WICKETS,wicketsComparator.thenComparing(ballingAverageComparator));
        sortFieldComparator.put(Field.BEST_BATTING_AND_BALLING_AVERAGES,battingAverageComparator.thenComparing(ballingAverageComparator));
        sortFieldComparator.put(Field.BEST_PLAYER_WITH_MOST_RUNS_AND_WICKETS,batsmenRunsComparator.thenComparing(wicketsComparator));

        Comparator<IplCensusDAO> csvComparator = sortFieldComparator.get(field);
        return csvComparator;
    }
}