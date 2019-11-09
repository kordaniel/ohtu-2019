package ohtu.verkkokauppa;

public interface InterfacePankki {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}
