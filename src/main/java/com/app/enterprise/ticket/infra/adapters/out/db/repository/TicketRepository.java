package com.app.enterprise.ticket.infra.adapters.out.db.repository;

import com.app.enterprise.ticket.domain.db.TicketEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TicketRepository extends ReactiveCrudRepository<TicketEntity , Long> {
    public Mono<Void> deleteById(Long id) ;
    public Mono<TicketEntity> findById(Long id);
    public Flux<TicketEntity> findAll();
}