package com.app.enterprise.ticket.infra.adapters.out.db;

import com.app.enterprise.ticket.application.port.out.TicketRepositoryPortOut;
import com.app.enterprise.ticket.domain.business.Ticket;
import com.app.enterprise.ticket.domain.db.TicketEntity;
import com.app.enterprise.ticket.domain.mapper.TicketEntityMapper;
import com.app.enterprise.ticket.infra.adapters.out.db.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketPortOutImpl implements TicketRepositoryPortOut {

    private final TicketRepository repository;
    private final TicketEntityMapper ticketEntityMapper;

    @Override
    public Mono<Ticket> save(Ticket ticket) {
        log.info("[PORT-OUT] SAVING TICKET : {} ",ticket);
        TicketEntity entity = ticketEntityMapper.toEntity(ticket);
        return repository.save(entity).map(ticketEntityMapper::toBusiness);
    }

    @Override
    public Mono<Ticket> update(Ticket ticket) {
        return repository.findById(ticket.getId())
                .switchIfEmpty(Mono.error( new NoSuchFieldError()))
                .flatMap(it -> {
                    var upd = ticketEntityMapper.toEntity(ticket);
            return repository.save(upd).map(ticketEntityMapper::toBusiness);
        });
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        log.info("[PORT-OUT] DELETE TICKETs BY ID : {}",id);
        return repository.deleteById(id);
    }

    @Override
    public Mono<Ticket> findById(Long id) {
        log.info("[PORT-OUT] GET TICKETs BY ID : {}",id);
        return repository.findById(id).map(ticketEntityMapper::toBusiness);
    }

    @Override
    public Flux<Ticket> findAll() {
        log.info("[PORT-OUT] GET ALL TICKETs");
        return repository.findAll().map(ticketEntityMapper::toBusiness);
    }
}
