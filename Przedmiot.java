package zpo;

import java.util.ArrayList;

public class Przedmiot {

    private String nazwaPrzedmiotu;
    private ArrayList<Uczen> uczniowie = new ArrayList<>();

    private void sprawdzNumer(int numer) {
        if (numer <= 0 || numer > uczniowie.size()) {
            throw new IllegalArgumentException("Niepoprawny numer ucznia!");
        }
    }

    public Przedmiot(String nazwaPrzedmiotu) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }

    public void dodajUcznia(Uczen uczen) {
        this.uczniowie.add(uczen);
    }

    public int liczbaUczniow() {
        return this.uczniowie.size();
    }

    public Uczen sprawdzUcznia(int numer) {
        sprawdzNumer(numer);
        return this.uczniowie.get(numer);
    }

    public Uczen usunUcznia(int numer) {
        sprawdzNumer(numer);
        return uczniowie.remove(numer);
    }

    public int sumaOcenUcznia(Uczen u) {
        int suma = 0;
        for (int i = 0; i < u.liczbaOcen(); i++) {
            suma += u.getOceny().get(i);  //sumujemy wszystkie oceny danego ucznia
        }
        return suma;
    }

    public double sredniaWszystkichOcen() {
        double suma = 0;
        int liczbaOcen = 0;
        if (uczniowie.isEmpty()) {
            throw new IllegalArgumentException("Brak uczniów!");
        } else {
            for (Uczen u : uczniowie) {
                suma += sumaOcenUcznia(u);  //sumujemy wszystkie sumy ocen uczniów z tablicy
                liczbaOcen += u.liczbaOcen();   //sumujemy liczby ocen poszczególnych uczniów
            }
        }
        return suma / liczbaOcen;   //sprawdzenie średniej ocen (średnia wszystkich ocen wszystkich uczniów)
    }

    public double sredniaOcenKoncowych() {
        double sumaSrednich = 0;
        int liczbaUczniowZOcenaKoncowa = 0;
        if (uczniowie.isEmpty()) {
            throw new IllegalArgumentException("Brak uczniów!");
        } else {
            for (Uczen u : uczniowie) {
                if (u.czyKlasyfikowany() && u.czyWystawionaOcenaKoncowa()) {  //sprawdzamy czy uczeń jest klasyfikowany oraz czy ma wystawioną ocenę końcową
                    sumaSrednich += u.getOcenaKoncowa();  //sumujemy oceny końcowe uczniów z tablicy (tych, którzy są klasyfikowani)
                    liczbaUczniowZOcenaKoncowa++;
                }
            }
        }

        return sumaSrednich / liczbaUczniowZOcenaKoncowa;
    }

    public int liczbaUczniowKlasyfikowanych() {
        int suma = 0;
        for (Uczen u : uczniowie) {
            if (u.czyKlasyfikowany()) { //sprawdzamy czy uczeń ma oceny, jeżeli tak to może byc klasyfikowany (ma co najmniej jedną ocenę)
                suma++;
            }
        }
        return suma;
    }

    public int uczniowieKtorzyNieZdali() {
        int suma = 0;
        for (Uczen u : uczniowie) {
            if (u.getOcenaKoncowa() == 1) { //sprawdzamy czy uczeń ma wystawioną ocenę końcową i jest to 1
                suma++;
            }
        }
        return suma;
    }
}
