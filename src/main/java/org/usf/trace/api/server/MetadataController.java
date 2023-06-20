package org.usf.trace.api.server;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.usf.trace.api.server.config.TraceApiColumn.*;
import static org.usf.trace.api.server.config.TraceApiTable.INC_REQ;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.usf.trace.api.server.metadata.CombinedFieldMetadata;
import org.usf.trace.api.server.metadata.FieldMetadata;
import org.usf.trace.api.server.metadata.SimpleFieldMetadata;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping(value = "metadata", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MetadataController {

    @GetMapping("aggregate")
    List<FieldMetadata> fetch() {
        return asList(
                new SimpleFieldMetadata(INC_REQ, COUNT, COUNT.reference(), "Nombre d'appels", "count"),
                new SimpleFieldMetadata(INC_REQ, ELAPSEDTIME, ELAPSEDTIME.reference(), "temps de réponse (s)", "s"),
                new SimpleFieldMetadata(INC_REQ, AVG_ELAPSEDTIME, AVG_ELAPSEDTIME.reference(), "temps de réponse moyen(s)", "s"),
                new SimpleFieldMetadata(INC_REQ, MAX_ELAPSEDTIME, MAX_ELAPSEDTIME.reference(), "temps de réponse max(s)", "s"),
                new SimpleFieldMetadata(INC_REQ, MIN_ELAPSEDTIME, MIN_ELAPSEDTIME.reference(), "temps de réponse min(s)", "s"),

                new CombinedFieldMetadata("nombre d'appels OK / KO", asList(
                        new SimpleFieldMetadata(INC_REQ, COUNT_STATUS_ERROR, COUNT_STATUS_ERROR.reference(), "nombre d'appels en erreur", "count"),
                        new SimpleFieldMetadata(INC_REQ, COUNT_STATUS_SUCCES, COUNT_STATUS_SUCCES.reference(), "nombre d'appels OK", "count"))),

                new CombinedFieldMetadata("nombre d'appels 2xx/4xx/5xx", asList(
                        new SimpleFieldMetadata(INC_REQ, COUNT_STATUS_ERROR_CLIENT, COUNT_STATUS_ERROR_CLIENT.reference(), "nombre d'appels en erreur client", "count"),
                        new SimpleFieldMetadata(INC_REQ, COUNT_STATUS_ERROR_SERVER, COUNT_STATUS_ERROR_SERVER.reference(), "nombre d'appels en erreur serveur", "count"),
                        new SimpleFieldMetadata(INC_REQ, COUNT_STATUS_SUCCES, COUNT_STATUS_SUCCES.reference(), "nombre d'appels OK", "c°"))),

                new CombinedFieldMetadata("nombre d'appels par temps de réponse", asList(
                        new SimpleFieldMetadata(INC_REQ, COUNT_ELAPSEDTIME_SLOWEST, COUNT_ELAPSEDTIME_SLOWEST.reference(), "temps de réponse les plus lents ", "count"),
                        new SimpleFieldMetadata(INC_REQ, COUNT_ELAPSEDTIME_SLOW, COUNT_ELAPSEDTIME_SLOW.reference(), "temps de réponse lent", "count"),
                        new SimpleFieldMetadata(INC_REQ, COUNT_ELAPSEDTIME_MEDIUM, COUNT_ELAPSEDTIME_MEDIUM.reference(), "temps de réponse moyen", "count"),
                        new SimpleFieldMetadata(INC_REQ, COUNT_ELAPSEDTIME_FAST, COUNT_ELAPSEDTIME_FAST.reference(), "temps de réponse rapide", "count"),
                        new SimpleFieldMetadata(INC_REQ, COUNT_ELAPSEDTIME_FASTEST, COUNT_ELAPSEDTIME_FASTEST.reference(), "temps de réponse les plus rapides", "count")))
        );
    }

    @GetMapping("filter")
    List<SimpleFieldMetadata> fetchFilters() {
        return asList(
                new SimpleFieldMetadata(INC_REQ, PROTOCOL, PROTOCOL.reference(), "Protocole", "count"),
                new SimpleFieldMetadata(INC_REQ, HOST, HOST.reference(), "Hôte", "count"),
                new SimpleFieldMetadata(INC_REQ, PORT, PORT.reference(), "Port", "count"),
                new SimpleFieldMetadata(INC_REQ, PATH, PATH.reference(), "Path", "count"),
                new SimpleFieldMetadata(INC_REQ, QUERY, QUERY.reference(), "Query", "count"),
                new SimpleFieldMetadata(INC_REQ, MTH, MTH.reference(), "Methode", "count"),
                new SimpleFieldMetadata(INC_REQ, STATUS, STATUS.reference(), "Status", "count"),
                new SimpleFieldMetadata(INC_REQ, SIZE, SIZE.reference(), "Size", "count"),
                new SimpleFieldMetadata(INC_REQ, START_DATETIME, START_DATETIME.reference(), "Date de début", "count"),
                new SimpleFieldMetadata(INC_REQ, FINISH_DATETIME, FINISH_DATETIME.reference(), "Date de fin", "count"),
                new SimpleFieldMetadata(INC_REQ, THREAD, THREAD.reference(), "Thread", "count"),
                new SimpleFieldMetadata(INC_REQ, CONTENT_TYPE, CONTENT_TYPE.reference(), "Content-Type", "count"),
                new SimpleFieldMetadata(INC_REQ, ACTION, ACTION.reference(), "Endpoints", "count"),
                new SimpleFieldMetadata(INC_REQ, RESOURCE, RESOURCE.reference(), "Ressource", "count"),
                new SimpleFieldMetadata(INC_REQ, CLIENT, CLIENT.reference(), "Client", "count"),
                new SimpleFieldMetadata(INC_REQ, GROUPE, GROUPE.reference(), "Groupe", "count"),
                new SimpleFieldMetadata(INC_REQ, AS_DATE, AS_DATE.reference(), "Format date", "count"),
                new SimpleFieldMetadata(INC_REQ, BY_DAY, "START_DATETIME.date", "Format jour", "count"),
                new SimpleFieldMetadata(INC_REQ, BY_MONTH, BY_MONTH.reference(), "Format mois", "count"),
                new SimpleFieldMetadata(INC_REQ, BY_YEAR, BY_YEAR.reference(), "Format Year", "count")
        );
    }
}
