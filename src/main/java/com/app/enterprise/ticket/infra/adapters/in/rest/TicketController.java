package com.app.enterprise.ticket.infra.adapters.in.rest;

import com.app.enterprise.ticket.application.port.in.TicketMgmtUseCase;
import com.app.enterprise.ticket.domain.mapper.TicketEntityMapper;
import com.app.enterprise.ticket.domain.rest.TicketRequestDTO;
import com.app.enterprise.ticket.domain.rest.TicketResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketMgmtUseCase service;
    private final TicketEntityMapper mapper;

    public TicketController(TicketMgmtUseCase service, TicketEntityMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public Mono<ResponseEntity<TicketResponseDTO>> create (@RequestBody TicketRequestDTO dto) {
        return service.create(dto).map(ResponseEntity.status(HttpStatus.CREATED)::body).onErrorResume(HttpClientErrorException.NotFound.class,
                e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TicketResponseDTO>>  findById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).
                        onErrorResume(HttpClientErrorException.NotFound.class,
                        e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping
    public Flux<TicketResponseDTO> findAll() {
        return service.getALl();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TicketResponseDTO>> update(@PathVariable Long id, @RequestBody TicketRequestDTO dto) {
        return service.update(id,dto).map(ResponseEntity::ok).onErrorResume(NoSuchFieldError.class,
                e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable Long id) {
        return service.delete(id).then(Mono.just(ResponseEntity.noContent().build()));
    }
}
