package hashing;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WortschatzTest {
    private Wortschatz _schatz;

    @BeforeMethod
    public void setUp() {
        _schatz = new HashWortschatz(new Delegation(), 10);
        //_schatz = new HashWortschatz(new KonstanterHashWertBerechner(), 1009);
    }

    @Test
    public void testNeuerWortschatzIstLeer() {
        Assert.assertEquals(_schatz.anzahlWoerter(), 0);
    }

    @Test
    public void testHinzugefuegtesWortIstEnthalten() {
        _schatz.fuegeWortHinzu("Test");
        Assert.assertTrue(_schatz.enthaeltWort("Test"));
        Assert.assertEquals(_schatz.anzahlWoerter(), 1);
    }

    @Test
    public void testEntferntesWortIstNichtEnthalten() {
        _schatz.fuegeWortHinzu("Test");
        _schatz.entferneWort("Test");
        Assert.assertFalse(_schatz.enthaeltWort("Test"));
        Assert.assertEquals(_schatz.anzahlWoerter(), 0);
    }

    @Test
    public void testNichtHinzugefuegtesWortIstNichtEnthalten() {
        Assert.assertFalse(_schatz.enthaeltWort("Test"));
    }

    @Test
    public void testDuplikateWerdenNichtHinzugefuegt() {
        _schatz.fuegeWortHinzu("Test");
        _schatz.fuegeWortHinzu("Test");
        Assert.assertEquals(_schatz.anzahlWoerter(), 1);
    }

    @Test
    public void testEntfernenNichtEnthaltenerWoerterBewirktNichts() {
        _schatz.entferneWort("Test");
        Assert.assertEquals(_schatz.anzahlWoerter(), 0);
    }

    @Test
    public void testLeererWortschatzHatKeineWoerter() {
        Assert.assertEquals(_schatz.anzahlWoerter(), 0);
        Assert.assertEquals(_schatz.fuellgrad(), 0);
        Assert.assertEquals(_schatz.laengsteKette(), 0);
    }

    @Test
    public void testFuellgradNachHinzufuegenVonWoertern() {
        _schatz.fuegeWortHinzu("Test1");
        _schatz.fuegeWortHinzu("Test2");
        _schatz.fuegeWortHinzu("Test3");
        Assert.assertEquals(_schatz.anzahlWoerter(), 3);
        Assert.assertEquals(_schatz.fuellgrad(), 30); // Bei einer Tabelle mit Anfangsgröße 10 entspricht dies 3 / 10 * 100
    }

    @Test
    public void testLaengsteKetteNachHinzufuegenVonWoertern() {
        _schatz.fuegeWortHinzu("Test1");
        _schatz.fuegeWortHinzu("Test2");
        _schatz.fuegeWortHinzu("Test3");
        Assert.assertEquals(_schatz.laengsteKette(), 1); // Da alle Wörter in unterschiedlichen Buckets landen, sollte die längste Kette 1 sein
    }

    @Test
    public void testEntfernenVonWoertern() {
        _schatz.fuegeWortHinzu("Test1");
        _schatz.fuegeWortHinzu("Test2");
        _schatz.entferneWort("Test1");
        Assert.assertEquals(_schatz.anzahlWoerter(), 1);
        Assert.assertFalse(_schatz.enthaeltWort("Test1"));
        Assert.assertTrue(_schatz.enthaeltWort("Test2"));
    }

    @Test
    public void testGrößenanpassung() {
        _schatz.fuegeWortHinzu("Test1");
        _schatz.fuegeWortHinzu("Test2");
        _schatz.fuegeWortHinzu("Test3");
        _schatz.fuegeWortHinzu("Test4");
        _schatz.fuegeWortHinzu("Test5");
        Assert.assertEquals(_schatz.anzahlWoerter(), 5);
        Assert.assertEquals(_schatz.fuellgrad(), 50); // Bei einer Tabelle mit Anfangsgröße 10 entspricht dies 5 / 10 * 100

        _schatz.fuegeWortHinzu("Test6");
        _schatz.fuegeWortHinzu("Test7");
        _schatz.fuegeWortHinzu("Test8");
        _schatz.fuegeWortHinzu("Test9");
        _schatz.fuegeWortHinzu("Test10");
        _schatz.fuegeWortHinzu("Test11");
        Assert.assertEquals(_schatz.anzahlWoerter(), 11);
        Assert.assertEquals(_schatz.fuellgrad(), 110); // Bei einer Tabelle mit Anfangsgröße 10 und einer Größenanpassung bei 100% Füllgrad entspricht dies 11 / 20 * 100
        Assert.assertEquals(_schatz.laengsteKette(), 2); // Nach der Größenanpassung sollten einige Ketten länger sein
    }

    @Test
    public void testKonstanterHashWert() {
        // Arrange
        HashWertBerechner berechner = new KonstanterHashWertBerechner();

        // Act
        int hashWert = berechner.hashWert("Beispielwort");

        // Assert
        Assert.assertEquals(hashWert, 0, "Der Hashwert des Wortes sollte 0 sein");
    }

}
