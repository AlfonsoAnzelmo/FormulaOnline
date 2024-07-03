package unisa.it.formulaonline.model.entity;

public class Segnalazione {
    private Commento commento;
    private Lettore autore;
    private String corpo;
    private Lettore gestita;

    public Segnalazione() {
    }

    public Segnalazione(Commento commento, Lettore autore, String corpo) {
        this.commento = commento;
        this.autore = autore;
        this.corpo = corpo;
    }

    public Segnalazione(Commento commento, Lettore autore, String corpo, Lettore gestita) {
        this.commento = commento;
        this.autore = autore;
        this.corpo = corpo;
        this.gestita = gestita;
    }

    public Commento getCommento() {
        return commento;
    }

    public void setCommento(Commento commento) {
        this.commento = commento;
    }

    public Lettore getAutore() {
        return autore;
    }

    public void setAutore(Lettore autore) {
        this.autore = autore;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Lettore getGestita() {
        return gestita;
    }

    public void setGestita(Lettore gestita) {
        this.gestita = gestita;
    }
}
