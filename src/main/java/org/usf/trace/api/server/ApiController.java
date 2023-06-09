package org.usf.trace.api.server;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.accepted;
import static org.usf.trace.api.server.Utils.requireSingle;
import static org.usf.traceapi.core.RemoteTraceSender.INCOMING_ENDPOINT;
import static org.usf.traceapi.core.RemoteTraceSender.MAIN_ENDPOINT;
import static org.usf.traceapi.core.RemoteTraceSender.TRACE_ENDPOINT;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.usf.traceapi.core.IncomingRequest;
import org.usf.traceapi.core.MainRequest;
import org.usf.traceapi.core.OutcomingRequest;
import org.usf.traceapi.core.Session;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping(value = TRACE_ENDPOINT, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiController {
	
    private final RequestDao dao;
    private final SessionQueueService queueService;

    @PutMapping(INCOMING_ENDPOINT)
    public ResponseEntity<Void> saveRequest(@RequestBody IncomingRequest req) {
        return appendRequest(req);
    }
    
    @PutMapping(MAIN_ENDPOINT)
    public ResponseEntity<Void> saveRequest(@RequestBody MainRequest req) {
        return appendRequest(req);
    }
    
    private ResponseEntity<Void> appendRequest(Session session){
        queueService.add(session);
        return accepted().build();
    }

    @GetMapping(INCOMING_ENDPOINT)
    public List<IncomingRequest> getIncomingRequestByIds(
    		@RequestParam(defaultValue = "true", name = "lazy") boolean lazy, 
    		@RequestParam(required = false, name = "id") String[] id) { // without tree
        return dao.getIncomingRequestById(lazy, id);
    }

    @GetMapping("incoming/request/{id}")
    public IncomingRequest getIncomingRequestById(@PathVariable String id) { // without tree
        return requireSingle(dao.getIncomingRequestById(true, id));
    }

    @GetMapping(MAIN_ENDPOINT)
    public List<MainRequest> getMainRequestByIds(
            @RequestParam(defaultValue = "true", name = "lazy") boolean lazy,
            @RequestParam(required = false, name = "id") String[] id) { // without tree
        return dao.getMainRequestById(lazy, id);
    }

    @GetMapping("main/request/{id}")
    public MainRequest getMainRequestById(@PathVariable String id) { // without tree
        return requireSingle(dao.getMainRequestById(true, id));
    }

    @GetMapping("incoming/request/{id}/out")
    public OutcomingRequest getOutcomingRequestById(@PathVariable String id) {
        return dao.getOutcomingRequestById(id);
    }

    @GetMapping("incoming/request/{id}/tree") //LATER
    public IncomingRequest getIncomingRequestTreeById(@PathVariable String id) {
        return requireSingle(dao.getIncomingRequestById(true, id)); //change query
    }
    
}


