package iplanalyser;

import com.opencsv.bean.CsvBindByName;

    public class IplAnalyserRunsCsv {
        public IplAnalyserRunsCsv() {}

        @CsvBindByName(column = "POS")
        public int position;
        @CsvBindByName(column = "PLAYER", required = true)
        public String player;
        @CsvBindByName(column = "Mat", required = true)
        public int matches;
        @CsvBindByName(column = "Inns", required = true)
        public int innings;
        @CsvBindByName(column = "NO", required = true)
        public int notOut;
        @CsvBindByName(column = "Runs", required = true)
        public int runs;
        @CsvBindByName(column = "HS", required = true)
        public String highestScore;
        @CsvBindByName(column = "Avg", required = true)
        public double  average;
        @CsvBindByName(column = "BF", required = true)
        public int ballFaced;
        @CsvBindByName(column = "SR", required = true)
        public double  strikeRate;
        @CsvBindByName(column = "100", required = true)
        public int hundreds;
        @CsvBindByName(column = "50", required = true)
        public int fiftys;
        @CsvBindByName(column = "4s", required = true)
        public int fours;
        @CsvBindByName(column = "6s", required = true)
        public int sixes;

        public IplAnalyserRunsCsv(String player, double average, double strikeRate, int fours, int sixes, int runs)
        {
            this.player = player;
            this.average = average;
            this.strikeRate = strikeRate;
            this.fours = fours;
            this.sixes = sixes;
            this.runs = runs;
        }

        @Override
        public String toString() {
            return "IplAnalyserRunsCsv{" +
                    "position=" + position +
                    ", player='" + player + '\'' +
                    ", matches=" + matches +
                    ", innings=" + innings +
                    ", notOut=" + notOut +
                    ", runs=" + runs +
                    ", highestScore='" + highestScore + '\'' +
                    ", average=" + average +
                    ", ballFaced=" + ballFaced +
                    ", strikeRate=" + strikeRate +
                    ", hundreds=" + hundreds +
                    ", fiftys=" + fiftys +
                    ", fours=" + fours +
                    ", sixes=" + sixes +
                    '}';
        }
    }
