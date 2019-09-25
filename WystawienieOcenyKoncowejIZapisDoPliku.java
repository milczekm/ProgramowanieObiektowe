package zpo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WystawienieOcenyKoncowejIZapisDoPliku {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, "cp1250");

        System.out.print("Wczytaj nazwę pliku z ocenami: ");
        String nazwaPlikuDoOdczytu = sc.nextLine();

        System.out.print("Wczytaj nazwę pliku do zapisania ocen końcowych: ");
        String nazwaPlikuDoZapisu = sc.nextLine();

        ArrayList<Uczen> listaUczniow = new ArrayList<>();

        File f = new File(nazwaPlikuDoOdczytu);

        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Scanner lineScanner = new Scanner(line);
                Uczen u = new Uczen();                  //tworzymy nowy obiekt typu Uczen, żeby zapisać w nim imię, nazwisko oraz jego oceny 
                while (lineScanner.hasNext()) {
                    String imie = lineScanner.next();
                    u.setImie(imie);
                    String nazwisko = lineScanner.next();
                    u.setNazwisko(nazwisko);
                    while (lineScanner.hasNext()) {  //w kolejnej pętli odczytujemy następne wyrazy z bieżącej linii (w tym przypadku będa to oceny)
                        String ocena = lineScanner.next();
                        u.dodajOcene(Integer.parseInt(ocena)); //po zamianie Stringa na wartość całkowitą dodajemy ocenę do listy ocen danego ucznia
                    }
                    listaUczniow.add(u); //odczytanego ucznia (imię, nazwisko, lista ocen) dodajemy do listy uczniów - tablica ta ułatwi nam późniejszy zapis do pliku
                }
            }
            s.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Nie moge otworzyć pliku");
        }
        try {
            FileWriter f1 = new FileWriter(nazwaPlikuDoZapisu);
            PrintWriter p = new PrintWriter(f1);
            for (Uczen uczen : listaUczniow) { //przegladając posiadaną tablicę uczniów zapisujemy do pliku wyjściowego pożądane wartości
                p.print(uczen.getImie() + " ");
                p.print(uczen.getNazwisko() + ": ");
                if (uczen.czyKlasyfikowany()) {     //sprawdzamy czy uczeń ma oceny i podejmujemy odpowiednią akcję
                    p.println(Math.round(uczen.sredniaOcen()));
                } else {
                    p.println("nieklasyfikowany");
                }
            }
            p.close();

        } catch (IOException ex) {
            System.out.println("Nie udało się zapisać do pliku!");
            System.exit(1);
        }

    }

}
