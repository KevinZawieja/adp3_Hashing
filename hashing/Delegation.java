package hashing;

/**
 * Delegiert die Berechnung an das String-Objekt.
 * 
 * @author Fredrik Winkler, Petra Becker-Pechau, Axel Schmolitzky
 // * @version 2023 
 */
class Delegation implements HashWertBerechner
{
    /**
     * Bildet das gegebene Wort auf einen ganzzahligen Wert ab.
     */
    public int hashWert(String wort)
    {
        return wort.hashCode();
    }
    
    /**
     * Liefert eine Beschreibung für die Art der Berechnung.
     */
    public String gibBeschreibung()
    {
        return "Delegiert die Berechnung an das String-Objekt";
    }
}
