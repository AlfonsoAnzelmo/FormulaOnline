package unisa.it.formulaonline.model.entity;

import java.util.Date;
import java.util.Objects;

public class Lettore {
    private Integer idLettore;
    private String email;
    private String password;
    private String nickname;
    private String scuderiaPref;
    private Boolean moderatore;
    private Date dataFineSospensione;


    public Lettore(Integer idLettore, String email, String password, String nickname, String scuderiaPref, Boolean moderatore) {
        this.idLettore = idLettore;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.scuderiaPref = scuderiaPref;
        this.moderatore = moderatore;
    }

    public Lettore() {
    }

    public Date getDataFineSospensione() {
        return dataFineSospensione;
    }

    public void setDataFineSospensione(Date dataFineSospensione) {
        this.dataFineSospensione = dataFineSospensione;
    }


    public Integer getIdLettore() {
        return idLettore;
    }

    public void setIdLettore(Integer idLettore) {
        this.idLettore = idLettore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getScuderiaPref() {
        return scuderiaPref;
    }

    public void setScuderiaPref(String scuderiaPref) {
        this.scuderiaPref = scuderiaPref;
    }

    public Boolean getModeratore() {
        return moderatore;
    }

    public void setModeratore(Boolean moderatore) {
        this.moderatore = moderatore;
    }

    @Override
    public String toString() {
        return "Lettore{" +
                "idLettore=" + idLettore +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", scuderiaPref='" + scuderiaPref + '\'' +
                ", moderatore=" + moderatore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lettore lettore = (Lettore) o;
        return Objects.equals(idLettore, lettore.idLettore) && Objects.equals(email, lettore.email) && Objects.equals(password, lettore.password) && Objects.equals(nickname, lettore.nickname) && Objects.equals(scuderiaPref, lettore.scuderiaPref) && Objects.equals(moderatore, lettore.moderatore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLettore, email, password, nickname, scuderiaPref, moderatore);
    }
}