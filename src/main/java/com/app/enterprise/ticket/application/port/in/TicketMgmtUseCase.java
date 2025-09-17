package com.app.enterprise.ticket.application.port.in;

import com.app.enterprise.ticket.domain.rest.TicketRequestDTO;
import com.app.enterprise.ticket.domain.rest.TicketResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketMgmtUseCase {
    public Mono<TicketResponseDTO> create (TicketRequestDTO ticketRequestDTO);
    public Mono<TicketResponseDTO> update (Long id , TicketRequestDTO ticketRequestDTO);
    public Mono<Void> delete (Long id);
    public Flux<TicketResponseDTO> getALl ();
    public Mono<TicketResponseDTO> getById (Long id);
}
