package zpo;

import java.util.ArrayList;

public class Uczen {

    private String imie;
    private String nazwisko;
    private ArrayList<Integer> oceny = new ArrayList<>();
    private boolean czyWystawiona;
    private int ocenaKoncowa;

    private void sprawdzWystawiona() {
        if (czyWystawiona) {
            throw new IllegalStateException("Wystawiona ocena końcowa!");
        }
    }

    private void sprawdzNieWystawiona() {
        if (!czyWystawiona) {
            throw new IllegalStateException("Ocena końcowa nie wystawiona!");
        }
    }

    public Uczen(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Uczen() {

    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void dodajOcene(int ocena) {
        sprawdzWystawiona();
        poprawnoscOceny(ocena);
        this.oceny.add(ocena);
    }

    public int liczbaOcen() {
        return this.oceny.size();
    }

    public double sredniaOcen() {

        if (this.oceny.isEmpty()) {
            throw new IllegalArgumentException("Uczen nie ma ocen!");
        }
        double suma = 0;
        for (int i = 0; i < this.oceny.size(); i++) {
            suma += this.oceny.get(i);
        }
        return suma / this.oceny.size();
    }

    private void poprawnoscIndexu(int index) {
        if (index < 0 || index >= oceny.size()) {
            throw new IllegalArgumentException("Niepoprawny indeks!");
        }
    }

    private void poprawnoscOceny(int ocena) {
        if (ocena < 1 || ocena > 6) {
            throw new IllegalArgumentException("Niepoprawna ocena!");
        }
    }

    public int sprawdzOcene(int index) {
        poprawnoscIndexu(index);
        return oceny.get(index);
    }

    public void usunOcene(int index) {
        sprawdzWystawiona();
        poprawnoscIndexu(index);
        oceny.remove(index);
    }

    public void zmienOcene(int index, int ocena) {
        sprawdzWystawiona();
        poprawnoscIndexu(index);
        poprawnoscOceny(ocena);
        oceny.set(index, ocena);
    }

    public int jakaOcenaWypada() {
        return (int) Math.round(sredniaOcen());
    }

    public boolean czyKlasyfikowany() {
        return !this.oceny.isEmpty();
    }

    public boolean czyWystawionaOcenaKoncowa() {
        return this.czyWystawiona;
    }

    public void wystawienieOcenyKoncowej() {
        this.ocenaKoncowa = jakaOcenaWypada();
        this.czyWystawiona = true;
    }

    public int getOcenaKoncowa() {
        sprawdzNieWystawiona();
        return this.ocenaKoncowa;
    }

    public ArrayList<Integer> getOceny() {
        return this.oceny;
    }
}
