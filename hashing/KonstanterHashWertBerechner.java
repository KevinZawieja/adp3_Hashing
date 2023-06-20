package hashing;

class KonstanterHashWertBerechner implements HashWertBerechner {

        @Override
        public String gibBeschreibung() {
            return "Jedes Wort hat den konstanten Hashwert: 0";
        }

        @Override
        public int hashWert(String wort) {
            return 0;
        }

    }
