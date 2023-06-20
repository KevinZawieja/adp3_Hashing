package hashing;

import java.io.PrintStream;

/**
 * Eine Stoppuhr vereinfacht das Messen von Zeitabschnitten.
 * 
 * @author Fredrik Winkler
 * @version 2023
 */
class Stoppuhr
{
    private final PrintStream _out;
    private long _start;
    
    /**
     * Startet den ersten Messvorgang.
     * @param out ein PrintStream, über den die Messdaten ausgegeben werden sollen
     * @param beschreibung eine Beschreibung des gemessenen Vorgangs
     */
    public Stoppuhr(PrintStream out, String beschreibung)
    {
        _out = out;
        _out.println("Starte Messung: " + beschreibung);
        _start = System.nanoTime();
    }

    /**
     * Stoppt den Messvorgang und startet einen neuen.
     */
    public void stopp()
    {
        long now = System.nanoTime();
        long difference = now - _start;
        long ms = difference / 1000000;
        _out.println("Gemessene Zeit: " + ms + " ms");
        _start = System.nanoTime();
    }

}
