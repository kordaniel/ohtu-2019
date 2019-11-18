package ohtu.verkkokauppa;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private String kaupanTili;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kaupanTili = "33333-44455";
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(22);

        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki, times(1)).tilisiirto(eq("pekka"), eq(22), eq("12345"), eq(kaupanTili), eq(5));
    }

    @Test
    public void kahdenTuotteenOstonPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(123);

        when(varasto.saldo(11)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(1);

        when(varasto.haeTuote(11)).thenReturn(new Tuote(1, "piima", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olut olut", 8));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(11);
        k.lisaaKoriin(2);
        k.tilimaksu("anu", "11111");

        verify(pankki, times(1)).tilisiirto(eq("anu"), eq(123), eq("11111"), eq(kaupanTili), eq(11));
    }

    @Test
    public void kahdenSamanTuotteenOstonPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(123);

        when(varasto.saldo(11)).thenReturn(10).thenReturn(9);

        when(varasto.haeTuote(11))
            .thenReturn(new Tuote(1, "piima", 3))
            .thenReturn(new Tuote(1, "piima", 3));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(11);
        k.lisaaKoriin(11);
        k.tilimaksu("guru", "1337-111");

        verify(pankki, times(1)).tilisiirto(eq("guru"), eq(123), eq("1337-111"), eq(kaupanTili), eq(6));
    }

    @Test
    public void kahdenTuotteenJostaYksiLoppuOstonPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(123);

        when(varasto.saldo(11)).thenReturn(1);
        when(varasto.saldo(123)).thenReturn(0);

        when(varasto.haeTuote(11)).thenReturn(new Tuote(11, "Brewermaster", 7));
        when(varasto.haeTuote(123)).thenReturn(new Tuote(123, "Piltti", 2));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(11);
        k.lisaaKoriin(123);
        k.tilimaksu("Pekka", "123");

        verify(pankki, times(1)).tilisiirto(eq("Pekka"), eq(123), eq("123"), eq(kaupanTili), eq(7));
    }
    /****
     * tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
    aloitetaan asiointi, koriin lisätään tuote, jota varastossa on ja suoritetaan ostos, 
    eli kutsutaan metodia kaupan tilimaksu(), varmista että kutsutaan pankin metodia tilisiirto 
    oikealla asiakkaalla, tilinumeroilla ja summalla
    tämä siis on muuten copypaste esimerkistä, mutta verify:ssä on tarkastettava että 
    parametreilla on oikeat arvot

    aloitetaan asiointi, koriin lisätään kaksi eri tuotetta, joita varastossa on ja suoritetaan
     ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, 
     tilinumerolla ja summalla

    aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta, jota on varastossa tarpeeksi 
    ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla 
    asiakkaalla, tilinumerolla ja summalla

    aloitetaan asiointi, koriin lisätään tuote, jota on varastossa tarpeeksi ja tuote joka on 
    loppu ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla 
    asiakkaalla, tilinumerolla ja summalla
    *****/
}