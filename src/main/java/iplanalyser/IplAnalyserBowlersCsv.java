package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplAnalyserBowlersCsv {
    @CsvBindByName(column = "POS")
    public int position;
    @CsvBindByName(column = "PLAYER")
    public String player;
    @CsvBindByName(column = "Mat")
    public int matches;
    @CsvBindByName(column = "Inns")
    public int innings;
    @CsvBindByName(column = "Ov")
    public double overs;
    @CsvBindByName(column = "Runs")
    public int runs;
    @CsvBindByName(column = "Wkts")
    public int wickets;
    @CsvBindByName(column = "BBI")
    public int bestBowlingAverage;
    @CsvBindByName(column = "Avg")
    public double average;
    @CsvBindByName(column = "Econ")
    public double  economy;
    @CsvBindByName(column = "SR")
    public double strikeRate;
    @CsvBindByName(column = "4w")
    public int fourWickets;
    @CsvBindByName(column = "5w")
    public int fiveWickets;

    public IplAnalyserBowlersCsv() {
    }

    public IplAnalyserBowlersCsv(String player, double average, double strikeRate, int fourWickets, int fiveWickets, double economy)
    {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;
        this.economy = economy;
    }

    @Override
    public String toString() {
        return "IplAnalyserBowlersCsv{" +
                "position=" + position +
                ", player='" + player + '\'' +
                ", matches=" + matches +
                ", innings=" + innings +
                ", overs=" + overs +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", bestBowlingAverage=" + bestBowlingAverage +
                ", average=" + average +
                ", economy=" + economy +
                ", strikeRate=" + strikeRate +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                '}';
    }
}
