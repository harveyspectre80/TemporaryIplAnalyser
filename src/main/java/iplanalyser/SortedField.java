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
        BEST_BALLING_AVERAGE_WITH_STRIKERATE,BEST_BALLING_AVERAGE_WITH_MAXIMUM_WICKETS,BEST_BATTING_AND_BALLING_AVERAGES,BEST_PLAYER_WITH_MOST_RUNS_AND_WICKETS,
        MOST_RUNS,MOST_FOURS,MOST_SIXES,MOST_FOUR_WICKETS,MOST_FIVE_WICKETS;
    }
    SortedField() {}
    public static Comparator getField(SortedField.Field field)
    {
        Comparator<IplDAO> battingAverageComparator = Comparator.comparing(census -> census.battingAverage);
        Comparator<IplDAO> strikeRateComparator = Comparator.comparing(census -> census.battingStrikeRate);
        Comparator<IplDAO> runsComparator = Comparator.comparing(census -> census.runs);
        Comparator<IplDAO> ballingAverageComparator = Comparator.comparing(census -> census.ballingAverage);
        Comparator<IplDAO> ballingStrikeRatesComparator = Comparator.comparing(census -> census.ballingStrikeRate);
        Comparator<IplDAO> ballingEconomyComparator = Comparator.comparing(census -> census.economy);
        Comparator<IplDAO> wicketsComparator = Comparator.comparing(census -> census.maximumWickets);
        Comparator<IplDAO> foursComparator = Comparator.comparing(census -> census.fours);
        Comparator<IplDAO> sixComparator = Comparator.comparing(census -> census.sixes);
        Comparator<IplDAO> fourWicketsComparator = Comparator.comparing(census -> census.fourWickets);
        Comparator<IplDAO> fiveWicketsComparator = Comparator.comparing(census -> census.fiveWickets);

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
        sortFieldComparator.put(Field.BEST_PLAYER_WITH_MOST_RUNS_AND_WICKETS,runsComparator.thenComparing(wicketsComparator));
        sortFieldComparator.put(Field.MOST_RUNS,runsComparator);
        sortFieldComparator.put(Field.MOST_FOURS,foursComparator);
        sortFieldComparator.put(Field.MOST_SIXES,sixComparator);
        sortFieldComparator.put(Field.MOST_FOUR_WICKETS,fourWicketsComparator);
        sortFieldComparator.put(Field.MOST_FIVE_WICKETS,fiveWicketsComparator);


        Comparator<IplDAO> csvComparator = sortFieldComparator.get(field);
        return csvComparator;
    }
}