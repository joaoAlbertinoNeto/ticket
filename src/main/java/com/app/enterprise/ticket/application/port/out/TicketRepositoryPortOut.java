package com.app.enterprise.ticket.application.port.out;

import com.app.enterprise.ticket.domain.business.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketRepositoryPortOut {
    Mono<Ticket> save(Ticket ticket);
    Mono<Ticket> update(Ticket ticket);
    Mono<Void> deleteById(Long id);
    Mono<Ticket> findById(Long id);
    Flux<Ticket> findAll();
}