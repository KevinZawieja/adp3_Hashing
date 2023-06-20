package hashing;

public class HashWertBerechner_ASCII implements HashWertBerechner {
    public int hashWert(String wort) {
        int hash = 0;
        for (char c : wort.toCharArray()) {
            hash += (int) c;
        }
        return hash;
    }

    @Override
    public String gibBeschreibung() {
        return "Jedes Wort hat den zugehörigen ASCII-Wert";
    }
}
