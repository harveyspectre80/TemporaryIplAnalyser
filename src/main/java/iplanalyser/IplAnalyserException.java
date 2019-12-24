package iplanalyser;

public class IplAnalyserException extends Exception {

    enum ExceptionType {
        NO_CENSUS_DATA,ISSUE_IN_FILE,NO_SUCH_FILE;
    }

    ExceptionType type;
    public IplAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;

    }
}
