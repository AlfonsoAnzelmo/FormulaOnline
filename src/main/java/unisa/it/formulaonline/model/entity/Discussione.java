package unisa.it.formulaonline.model.entity;

import java.util.Objects;

/**
 * Questa classe rappresenta una categoria di discussione. Contiene l'identificativo, un titolo,
 * il numero di commenti che ha ricevuto, il lettore che l'ha creata e la categoria a cui appartiene
 */
public class Discussione {
    private int idDiscussione, numeroCommenti;

    private Categoria categoria;
    private String titolo;
    private Lettore lettore;

    public Discussione() {
    }

    public Discussione(int numeroCommenti, Categoria categoria,
                       String titolo, Lettore lettore) {
        this.numeroCommenti = numeroCommenti;
        this.categoria = categoria;
        this.titolo = titolo;
        this.lettore = lettore;
    }

    public Discussione(int idDiscussione, int numeroCommenti, Categoria categoria, String titolo, Lettore lettore) {
        this.idDiscussione = idDiscussione;
        this.numeroCommenti = numeroCommenti;
        this.categoria = categoria;
        this.titolo = titolo;
        this.lettore = lettore;
    }

    public int getIdDiscussione() {
        return idDiscussione;
    }

    public void setIdDiscussione(int idDiscussione) {
        this.idDiscussione = idDiscussione;
    }

    public int getNumeroCommenti() {
        return numeroCommenti;
    }

    public void setNumeroCommenti(int numeroCommenti) {
        this.numeroCommenti = numeroCommenti;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Lettore getLettore() {
        return lettore;
    }

    public void setLettore(Lettore lettore) {
        this.lettore = lettore;
    }

    @Override
    public String toString() {
        return "Discussione{" +
                "idDiscussione=" + idDiscussione +
                ", numeroCommenti=" + numeroCommenti +
                ", categoria=" + categoria +
                ", titolo='" + titolo + '\'' +
                ", lettore=" + lettore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discussione that = (Discussione) o;
        return idDiscussione == that.idDiscussione && numeroCommenti == that.numeroCommenti && Objects.equals(categoria, that.categoria) && Objects.equals(titolo, that.titolo) && Objects.equals(lettore, that.lettore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiscussione, numeroCommenti, categoria, titolo, lettore);
    }
}
