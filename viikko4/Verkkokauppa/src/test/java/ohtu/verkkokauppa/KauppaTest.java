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

    @Test
    public void aloitaAsiointiNollaaOstokset() {
        when(viite.uusi()).thenReturn(55);

        when(varasto.saldo(1)).thenReturn(50);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "pottuja", 2));

        when(varasto.saldo(2)).thenReturn(25);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "lihaa", 15));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("paavo", "77777");

        verify(pankki).tilisiirto(eq("paavo"), eq(55), eq("77777"), eq(kaupanTili), eq(15));
    }

    @Test
    public void kaksiAsiointiaLaskuttaaEriViitenumerolla() {
        when(viite.uusi()).thenReturn(55).thenReturn(56);

        when(varasto.saldo(1)).thenReturn(50);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "pottuja", 2));

        when(varasto.saldo(2)).thenReturn(25);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "lihaa", 15));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "123123");

        verify(viite, times(1)).uusi();
        verify(pankki).tilisiirto(eq("pekka"), eq(55), eq("123123"), eq(kaupanTili), eq(2));

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("paavo", "77777");

        verify(viite, times(2)).uusi();
        verify(pankki).tilisiirto(eq("paavo"), eq(56), eq("77777"), eq(kaupanTili), eq(15));
    }
    
    @Test
    public void tuotteenPoistaminenKoristaPalauttaaTuotteenVarastoon() {
        when(varasto.saldo(1)).thenReturn(10);
        
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);

        verify(varasto, times(1)).palautaVarastoon(null);
        
    }
}