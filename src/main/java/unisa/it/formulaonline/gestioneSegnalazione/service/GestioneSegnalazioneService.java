package unisa.it.formulaonline.gestioneSegnalazione.service;

import unisa.it.formulaonline.model.entity.Segnalazione;

import java.sql.Date;
import java.util.List;

/**
 * Interfaccià che espone i metodi per gestire le segnalazioni, crearle, risolverle e sospendere gli utenti
 */
public interface GestioneSegnalazioneService {
    /**
     * Salva una nuova segnalazione
     * @param segnalazione
     * @return la segnalazione se creata con successo, null altrimenti
     */
    public Segnalazione creaSegnalazione(Segnalazione segnalazione);

    /**
     * risolve una segnalazione e sospende il lettore fino alla data decisa
     * @param segnalazione
     * @param dataFineSospensione
     */
    public void sospendiLettore(Segnalazione segnalazione, Date dataFineSospensione);

    /**
     * elimina la segnalazione dal sistema
     * @param segnalazione
     */
    public void eliminaSegnalazione(Segnalazione segnalazione);

    /**
     * restituisce la lista di tutte le segnalazioni
     * @return
     */
    public List<Segnalazione> visualizzaSegnalazioni();

}
