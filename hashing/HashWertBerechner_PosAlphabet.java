package hashing;

public class HashWertBerechner_PosAlphabet implements HashWertBerechner {
    public int hashWert(String wort) {
        int hash = 0;
        for (char c : wort.toCharArray()) {
            hash += (int) Character.toLowerCase(c) - 'a' + 1;
        }
        return hash;
    }

    @Override
    public String gibBeschreibung() {
        return "Jedes Wort hat den zugehörigen Wert der Position der Buchstaben im Alphabet addiert";
    }
}
