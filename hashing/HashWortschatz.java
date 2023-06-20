package hashing;


/**
 * Eine hashbasierte Implementation eines Wortschatzes.
 */
class HashWortschatz implements Wortschatz {

    private static final int DEFAULT_FUELLGRAD = 2003;
    private final HashWertBerechner _berechner;
    private WortListe[] _tabelle;
    private int _anzahlWoerter;
    private double _fuellgrad;
    private double _fuellgradGrenze;



    public HashWortschatz(HashWertBerechner berechner, int groesse) {
        this(berechner, groesse, DEFAULT_FUELLGRAD);
    }

    public HashWortschatz(HashWertBerechner berechner, int groesse, double fuellgradGrenze) {
        _berechner = berechner;
        _tabelle = new WortListe[groesse];
        _anzahlWoerter = 0;
        _fuellgrad = 0.0;
        _fuellgradGrenze = fuellgradGrenze;
    }

    public void fuegeWortHinzu(String wort) {
        int hashWert = _berechner.hashWert(wort);
        int index = Math.abs(hashWert) % _tabelle.length;
        if (_tabelle[index] == null) {
            _tabelle[index] = new WortListe();
        }
        if (!_tabelle[index].enthaeltWort(wort)) {
            _tabelle[index].fuegeWortHinzu(wort);
            _anzahlWoerter++;
            _fuellgrad = (double) _anzahlWoerter / _tabelle.length;

            if (_fuellgrad >= _fuellgradGrenze) {
                vergroessereTabelle();
            }
        }
    }

    public void entferneWort(String wort) {
        int hashWert = _berechner.hashWert(wort);
        int index = Math.abs(hashWert) % _tabelle.length;
        if (_tabelle[index] != null) {
            if (_tabelle[index].entferneWort(wort)) {
                _anzahlWoerter--;
            }
        }
    }

    public boolean enthaeltWort(String wort) {
        int hashWert = _berechner.hashWert(wort);
        int index = Math.abs(hashWert) % _tabelle.length;
        if (_tabelle[index] != null) {
            return _tabelle[index].enthaeltWort(wort);
        }
        return false;
    }

    public int anzahlWoerter() {
        return _anzahlWoerter;
    }

    public int fuellgrad() {
        double fuellgrad = _fuellgrad * 100;
        return (int) fuellgrad;
    }

    public int laengsteKette() {
        int laengsteKette = 0;
        for (WortListe liste : _tabelle) {
            if (liste != null && liste.anzahlWoerter() > laengsteKette) {
                laengsteKette = liste.anzahlWoerter();
            }
        }
        return laengsteKette;
    }

    public void schreibeWortschatzAufKonsole() {
        for (int i = 0; i < _tabelle.length; i++) {
            System.out.print("[" + i + "]: ");
            if (_tabelle[i] != null) {
                System.out.print(_tabelle[i]);
            }
            System.out.println();
        }
    }

    private void vergroessereTabelle() {
        int neueGroesse = _tabelle.length * 2;
        WortListe[] neueTabelle = new WortListe[neueGroesse];

        // Initialisiere die neue Hash-Tabelle
        for (int i = 0; i < neueGroesse; i++) {
            neueTabelle[i] = new WortListe();
        }

        // Kopiere die Einträge aus der alten Tabelle in die neue Tabelle
        for (WortListe liste : _tabelle) {
            if (liste != null) {
                for (String wort : liste) {
                    int hashWert = _berechner.hashWert(wort);
                    int index = Math.abs(hashWert) % neueGroesse;
                    neueTabelle[index].fuegeWortHinzu(wort);
                }
            }
        }

        // Aktualisiere die Referenz auf die neue Hash-Tabelle
        _tabelle = neueTabelle;
    }
}
