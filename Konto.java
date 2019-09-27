package zpo;

public class Konto {

    private Osoba wlasciciel;
    private int saldo;
    private int maksymalnyDebet;

    private void sprawdzMaksymalnyDebet(Osoba wlasciciel, int maksymalnyDebet) {

        if (maksymalnyDebet < 0) {
            throw new IllegalArgumentException("Debet nie moze byc ujemny!");
        }

        if (!wlasciciel.pelnoletnia() && maksymalnyDebet > 0) {
            throw new IllegalArgumentException("Osoba niepelnoletnia nie moze miec debetu!");
        }
    }

    private void sprawdzDebet(int saldo, int maksymalnyDebet) {
        if (saldo < -maksymalnyDebet) {
            throw new IllegalArgumentException("Saldo ponizej maksymalnego debetu!");
        }
    }

    private void sprawdzKwoty(int kwota) {
        if (kwota < 0) {
            throw new IllegalArgumentException("Kwota nie moze byc ujemna!");
        }
    }

    public Konto(Osoba wlasciciel, int saldo, int maksymalnyDebet) {
        sprawdzMaksymalnyDebet(wlasciciel, maksymalnyDebet);
        sprawdzDebet(saldo, maksymalnyDebet);
        this.wlasciciel = wlasciciel;
        this.saldo = saldo;
        this.maksymalnyDebet = maksymalnyDebet;
    }

    public Konto(Osoba wlasciciel) {
        this(wlasciciel, 0, 0);
    }

    public Konto(Osoba wlasciciel, int saldo) {
        this(wlasciciel, saldo, 0);
    }

    public Osoba getWlasciciel() {
        return wlasciciel;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getMaksymalnyDebet() {
        return maksymalnyDebet;
    }

    public void setMaksymalnyDebet(int maksymalnyDebet) {
        if (saldo < maksymalnyDebet) {
            throw new IllegalArgumentException("Saldo ponizej debetu!");
        }
        this.maksymalnyDebet = maksymalnyDebet;
    }

    public void wplataKwoty(int kwota) {
        sprawdzKwoty(kwota);
        this.saldo += kwota;
    }

    public void wyplataKwoty(int kwota) {
        sprawdzKwoty(kwota);
        sprawdzDebet(saldo - kwota, maksymalnyDebet);
        this.saldo -= kwota;
    }

    public void przelew(Konto konto, int kwota) {
        if (konto == this) {
            throw new IllegalArgumentException("Przelew na to samo konto!");
        }
        wyplataKwoty(kwota);
        konto.wplataKwoty(kwota);

    }
}
