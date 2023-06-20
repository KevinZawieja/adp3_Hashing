package hashing;

import java.util.List;

/**
 * Diese Klasse dient als Startup für die Aufgabe zur
 * Implementierung von Hash-Verfahren.
 * 
 * @author Fredrik Winkler, Petra Becker-Pechau, Axel Schmolitzky
 * @version 2023 
 */
class Startup
{
    // Einige Primzahlen, die sich gut zur Dimensionierung von Hashtabellen eignen:
    private static final int MINI   =    11;
    private static final int KLEIN  =   101;
    private static final int MITTEL =  1009;
    private static final int GROSS  = 10007;

    public static void main(String[] args) {
        vergleichePerformance();
       // visualisiereHashtabelle();
    }
    /**
     * Zeigt die Hashtabelle eines Wortschatzes auf der Konsole an.
     */
    public static void visualisiereHashtabelle() {
        System.out.println("Aktuelles Arbeitsverzeichnis: " + System.getProperty("user.dir"));
        // Wenn es Probleme mit dem Arbeitsverzeichnis gibt, siehe die obige Ausgabe
        List<String> kurz = Worteinleser.dateiAlsText("./hashing/neuer_wortschatz");
        HashWertBerechner delegation = new Delegation();

        extrahiereWortschatz(kurz, delegation, MINI).schreibeWortschatzAufKonsole();
    }


    /**
     * Vergleicht die Performance verschiedener Hashfunktionen.
     */
    public static void vergleichePerformance()
    {
        List<String> lang = Worteinleser.dateiAlsText("./hashing/moby10b.txt");
        HashWertBerechner delegation = new Delegation();
        HashWertBerechner konstant = new KonstanterHashWertBerechner();
        HashWertBerechner ascii = new HashWertBerechner_ASCII();
        HashWertBerechner posAlphabet = new HashWertBerechner_PosAlphabet();

        vermesse(lang, delegation, MITTEL);
        vermesse(lang, konstant, MITTEL);
        vermesse(lang, ascii, MITTEL);
        vermesse(lang, posAlphabet, MITTEL);

    }

    /**
     * Extrahiert einen Wortschatz aus dem gegebenenen Text.
     *
     * @param text der zu verarbeitende Text
     * @param berechner der zu verwendende HashWertBerechner
     * @param groesse die Anzahl der Überlaufbehälter
     * @return der extrahierte Wortschatz
     */
    private static HashWortschatz extrahiereWortschatz(List<String> text,
                                                       HashWertBerechner berechner,
                                                       int groesse)
    {
        HashWortschatz wortschatz = new HashWortschatz(berechner, groesse);
        for (String wort : text)
        {
            wortschatz.fuegeWortHinzu(wort);
        }
        return wortschatz;
    }

    /**
     * Diese Methode misst, wie lange es dauert,
     * einen Wortschatz aus dem gegebenen Text zu extrahieren.
     *
     * @param text der zu verarbeitende Text
     * @param berechner der zu verwendende HashWertBerechner
     * @param groesse die Anzahl der Überlaufbehälter
     */
    private static void vermesse(List<String> text,
                                 HashWertBerechner berechner,
                                 int groesse)
    {
        Stoppuhr uhr = new Stoppuhr(System.out, berechner.gibBeschreibung());
        Wortschatz wortschatz = extrahiereWortschatz(text, berechner, groesse);
        uhr.stopp();
        System.out.println(wortschatz.anzahlWoerter() + " verschiedene Wörter gefunden");
        if (wortschatz instanceof HashWortschatz hash)
        {
            System.out.println("Füllgrad der Hash-Tabelle: " + hash.fuellgrad() + "%");
            System.out.println("Längste Kette: " + hash.laengsteKette() + "\n");
        }

    }
}
