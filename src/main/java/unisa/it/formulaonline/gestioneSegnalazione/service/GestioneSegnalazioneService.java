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
     * @return la segnalazione se creata con successo, null altrimenti
     */
    Segnalazione creaSegnalazione(int idCommento, int idAutore, String corpo);

    /**
     * risolve una segnalazione e sospende il lettore fino alla data decisa
     */
    void sospendiLettore(int idSegnalazione, Date dataFineSospensione);

    /**
     * elimina la segnalazione dal sistema
     */
    void eliminaSegnalazione(int idSegnalazione);

    /**
     * restituisce la lista di tutte le segnalazioni
     * @return la lista delle segnalazioni, null se è vuota
     */
    List<Segnalazione> ottieniSegnalazioni();

    /**
     * restituisce la segnalazione indicata da id
     * @return la segnalazione correlata, null se non esiste
     */
    Segnalazione ottieniSegnalazioneDaId(int idSegnalazione);

}
