package iplanalyser;

import java.nio.file.Path;

public class IplDAO {

    public String player;
    public double battingAverage;
    public double ballingAverage;
    public double battingStrikeRate;
    public double ballingStrikeRate;
    public int fours;
    public int sixes;
    public int runs;
    public int fiveWickets;
    public int fourWickets;
    public double economy;
    public double maximumWickets;

    public IplDAO(IplAnalyserRunsCsv iplAnalyserRunsCsv) {
        this.player = iplAnalyserRunsCsv.player;
        this.battingAverage = iplAnalyserRunsCsv.average;
        this.battingStrikeRate = iplAnalyserRunsCsv.strikeRate;
        this.fours = iplAnalyserRunsCsv.fours;
        this.sixes = iplAnalyserRunsCsv.sixes;
        this.runs = iplAnalyserRunsCsv.runs;
    }

    public IplDAO(IplAnalyserBowlersCsv iplAnalyserBowlersCsv) {
        this.player = iplAnalyserBowlersCsv.player;
        this.ballingAverage = iplAnalyserBowlersCsv.average;
        this.ballingStrikeRate = iplAnalyserBowlersCsv.strikeRate;
        this.fourWickets = iplAnalyserBowlersCsv.fourWickets;
        this.fiveWickets = iplAnalyserBowlersCsv.fiveWickets;
        this.economy = iplAnalyserBowlersCsv.economy;
        this.maximumWickets=iplAnalyserBowlersCsv.wickets;
    }

    public IplDAO(String player, int fourWickets, int fiveWickets) {
        this.player = player;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;

    }

    public IplDAO(String player , int runs, int fours, int sixes) {
        this.player = player;
        this.runs = runs;
        this.fours = fours;
        this.sixes = sixes;
    }

    public IplDAO(String hardik_pandya, int i, int i1, int i2, int i3) {
    }

    public Object getIPLDTO (IplAnalyser.PlayType playType)
    {
        if(playType.equals(IplAnalyser.PlayType.Batting))
               return new IplAnalyserRunsCsv(player,battingAverage,battingStrikeRate,fours,sixes,runs);
          return new IplAnalyserBowlersCsv(player,ballingAverage,ballingStrikeRate,fourWickets,fiveWickets,economy);
    }
}
