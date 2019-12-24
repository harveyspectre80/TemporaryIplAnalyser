package iplanalyser;

public class IplAdaptorFactory {

    public static IplAdapter getIplData(IplAnalyser.PlayType playType) throws IplAnalyserException {
        if (playType.equals(IplAnalyser.PlayType.Batting))
            return new IplAnalyserRunsCsvAdapter();
        else if(playType.equals(IplAnalyser.PlayType.Bowling))
            return new IplAnalyserBowlersCsvAdapter();
        else throw new IplAnalyserException("No Such File Exist",IplAnalyserException.ExceptionType.NO_SUCH_FILE);

    }
}
