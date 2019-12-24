package iplanalyser;

import java.nio.file.Path;

public class IplCensusDAO {

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

    public IplCensusDAO(IplAnalyserRunsCsv iplAnalyserRunsCsv) {
        this.player = iplAnalyserRunsCsv.player;
        this.battingAverage = iplAnalyserRunsCsv.average;
        this.battingStrikeRate = iplAnalyserRunsCsv.strikeRate;
        this.fours = iplAnalyserRunsCsv.fours;
        this.sixes = iplAnalyserRunsCsv.sixes;
        this.runs = iplAnalyserRunsCsv.runs;
    }

    public IplCensusDAO(IplAnalyserBowlersCsv iplAnalyserBowlersCsv) {
        this.player = iplAnalyserBowlersCsv.player;
        this.ballingAverage = iplAnalyserBowlersCsv.average;
        this.ballingStrikeRate = iplAnalyserBowlersCsv.strikeRate;
        this.fourWickets = iplAnalyserBowlersCsv.fourWickets;
        this.fiveWickets = iplAnalyserBowlersCsv.fiveWickets;
        this.economy = iplAnalyserBowlersCsv.economy;
        this.maximumWickets=iplAnalyserBowlersCsv.wickets;
    }

    public IplCensusDAO() {
    }

    public Object getIPLDTO (IplAnalyser.PlayType playType)
    {
        if(playType.equals(IplAnalyser.PlayType.Batting))
               return new IplAnalyserRunsCsv(player,battingAverage,battingStrikeRate,fours,sixes,runs);
          return new IplAnalyserBowlersCsv(player,ballingAverage,ballingStrikeRate,fourWickets,fiveWickets,economy);
    }
}
