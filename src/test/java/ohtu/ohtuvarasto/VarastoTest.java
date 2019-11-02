package ohtu.ohtuvarasto;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaAlkusaldo() {
        varasto = new Varasto(10, 5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaEiNegatiivistaSaldoa() {
        varasto = new Varasto(10, -8);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaEiNegatiivistaTilavuutta() {
        varasto = new Varasto(-10);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoKayttokelvottomanVaraston() {
        varasto = new Varasto(-1, 5);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenAntaaKorkeintaanSaldon() {
        varasto.lisaaVarastoon(7);
        double saatuMaara = varasto.otaVarastosta(13);
        assertEquals(7, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivisiaArvojaEiVoiOttaa() {
        double saatuMaara = varasto.otaVarastosta(-3);
        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void lisaysNegatiivisellaArvollaEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-7);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysMuuttaaSaldonKorkeintaanTilavuudenArvoksi() {
        varasto.lisaaVarastoon(37);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonToStringToimii() {
        String varastoString = varasto.toString();
        String odotettuString = "saldo = 0.0, vielä tilaa 10.0";
        assertTrue(odotettuString.equals(varastoString));
    }
}