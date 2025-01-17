package unisa.it.formulaonline.model.entity;

import java.util.Objects;

/**
 * Questa classe rappresenta una categoria di discussione. Contiene l'identificativo, un nome, una descrizone,
 * la categoria padre nel caso sia una sottocategoria, ed lettore che l'ha creata
 */

public class Categoria {
    private int idCategoria;
    private String nome, descrizione;
    private Categoria categoriaPadre;
    private Lettore creatore;

    public Categoria(){

    }

    public Categoria(String nome, String descrizione, Categoria padre, Lettore creatore){
        this.nome = nome;
        this.descrizione = descrizione;
        this.categoriaPadre =  padre;
        this.creatore = creatore;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    public Lettore getCreatore() {
        return creatore;
    }

    public void setCreatore(Lettore creatore) {
        this.creatore = creatore;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", categoriaPadre=" + categoriaPadre +
                ", creatore=" + creatore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria == categoria.idCategoria && Objects.equals(nome, categoria.nome) && Objects.equals(descrizione, categoria.descrizione) && Objects.equals(categoriaPadre, categoria.categoriaPadre) && Objects.equals(creatore, categoria.creatore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, nome, descrizione, categoriaPadre, creatore);
    }
}
