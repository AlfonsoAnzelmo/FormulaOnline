package unisa.it.formulaonline.model.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Questa classe rappresenta un commento, contiene un identificativo, il corpo, la data in cui è stato inviato,
 * la discussione a cui appartiene ed il lettore che l'ha scritto
 */
public class Commento {
    private int idCommento;
    private Discussione discussione;
    private Lettore autore;
    private Date dataCommento;
    private String corpo;

    public Commento(){
    }

    public Commento(String corpo, Discussione discussione, Date dataCommento, Lettore autore) {
        this.corpo = corpo;
        this.discussione = discussione;
        this.dataCommento = dataCommento;
        this.autore = autore;
    }

    public Commento(int idCommento, String corpo, Discussione discussione, Date dataCommento, Lettore autore) {
        this.idCommento = idCommento;
        this.corpo = corpo;
        this.discussione = discussione;
        this.dataCommento = dataCommento;
        this.autore = autore;
    }

    public int getIdCommento() {
        return idCommento;
    }

    public void setIdCommento(int idCommento) {
        this.idCommento = idCommento;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Discussione getDiscussione() {
        return discussione;
    }

    public void setDiscussione(Discussione discussione) {
        this.discussione = discussione;
    }

    public Date getDataCommento() {
        return dataCommento;
    }

    public void setDataCommento(Date dataCommento) {
        this.dataCommento = dataCommento;
    }

    public Lettore getAutore() {
        return autore;
    }

    public void setAutore(Lettore autore) {
        this.autore = autore;
    }

    @Override
    public String toString() {
        return "Commento{" +
                "idCommento=" + idCommento +
                ", corpo='" + corpo + '\'' +
                ", discussione=" + discussione +
                ", dataCommento=" + dataCommento +
                ", autore=" + autore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commento commento = (Commento) o;
        return idCommento == commento.idCommento && Objects.equals(corpo, commento.corpo) && Objects.equals(discussione, commento.discussione) && Objects.equals(dataCommento, commento.dataCommento) && Objects.equals(autore, commento.autore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommento, corpo, discussione, dataCommento, autore);
    }
}

