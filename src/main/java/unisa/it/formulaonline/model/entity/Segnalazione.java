package unisa.it.formulaonline.model.entity;

public class Segnalazione {
    private int idSegnalazione;
    private Commento commento;
    private Lettore autore;
    private String corpo;

    public Segnalazione() {
    }

    public Segnalazione(int idSegnalazione) {
        this.idSegnalazione = idSegnalazione;
    }

    public Segnalazione(Commento commento, Lettore autore, String corpo) {
        this.commento = commento;
        this.autore = autore;
        this.corpo = corpo;
    }

    public Segnalazione(int idSegnalazione, Commento commento, Lettore autore, String corpo) {
        this.idSegnalazione = idSegnalazione;
        this.commento = commento;
        this.autore = autore;
        this.corpo = corpo;
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

    public int getIdSegnalazione() {
        return idSegnalazione;
    }

    public void setIdSegnalazione(int idSegnalazione) {
        this.idSegnalazione = idSegnalazione;
    }
}
