package zpo;

public class Osoba {

    private String imie;
    private String nazwisko;
    private int wiek;

    private void sprawdzWiek(int wiek) {
        if (wiek < 0) {
            throw new IllegalArgumentException("Wiek nie moze byc ujemny");
        }
    }

    public boolean pelnoletnia() {
        return wiek >= 18;
    }

    public Osoba(String imie, String nazwisko, int wiek) {
        sprawdzWiek(wiek);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getWiek() {
        return wiek;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setWiek(int wiek) {
        sprawdzWiek(wiek);
        this.wiek = wiek;
    }

    static boolean wszyscyPelnoletni(Osoba[] osoby) {
        int sumaPomocnicza = 0;
        for (int i = 0; i < osoby.length; i++) {
            if (!osoby[i].pelnoletnia()) {
                return false;
            }
        }
        return true;
    }

    static int sumaRocznychZarobkow(Pracownik[] pracownicy) {
        int sumaZarobkow = 0;
        for (int i = 0; i < pracownicy.length; i++) {
            sumaZarobkow += pracownicy[i].roczneZarobki();
        }
        return sumaZarobkow;
    }

}
