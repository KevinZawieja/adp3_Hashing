package hashing;

/**
 * Ein Wortschatz ist eine Menge von Wörtern. Dieses Interface definiert
 * geeignete Operationen auf einer solchen Menge:
 * Es können Wörter hinzugefügt und entfernt werden, es kann abgefragt werden,
 * ob ein bestimmtes Wort im Wortschatz enthalten ist, und es kann die Anzahl
 * der gespeicherten Wörter abgefragt werden.
 * 
 * @author Fredrik Winkler, Petra Becker-Pechau, Axel Schmolitzky
 * @version 2023
 */ 
interface Wortschatz
{
    /**
     * Füge ein Wort zum Wortschatz hinzu, sofern es noch nicht enthalten ist.
     * 
     * @param wort das hinzuzufügende Wort
     */
    public void fuegeWortHinzu(String wort);

    /**
     * Entferne ein Wort aus dem Wortschatz, sofern es darin enthalten ist.
     * 
     * @param wort das zu entfernende Wort
     */
    public void entferneWort(String wort);
    
    /**
     * Gib an, ob ein Wort im Wortschatz enthalten ist.
     * 
     * @param wort das zu überprüfende Wort
     * @return true, falls das Wort enthalten ist, false sonst
     */
    public boolean enthaeltWort(String wort);
    
    /**
     * Gib an, wieviele Wörter im Wortschatz enthalten sind.
     * 
     * @return die Anzahl der Wörter im Wortschatz
     */
    public int anzahlWoerter();

    /**
     * Gib den Füllgrad des Wortschatzes als Prozentzahl zurück.
     *
     * @return der Füllgrad in Prozent
     */
    int fuellgrad();

    /**
     * Gib die Länge der längsten Wortkette im Wortschatz zurück.
     *
     * @return die Länge der längsten Wortkette
     */
    int laengsteKette();
}
