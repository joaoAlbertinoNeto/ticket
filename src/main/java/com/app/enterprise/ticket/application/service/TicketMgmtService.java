package com.app.enterprise.ticket.application.service;

import com.app.enterprise.ticket.application.port.in.TicketMgmtUseCase;
import com.app.enterprise.ticket.application.port.out.TicketRepositoryPortOut;
import com.app.enterprise.ticket.domain.mapper.TicketEntityMapper;
import com.app.enterprise.ticket.domain.rest.TicketRequestDTO;
import com.app.enterprise.ticket.domain.rest.TicketResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class TicketMgmtService implements TicketMgmtUseCase {

    private final TicketRepositoryPortOut ticketRepositoryPortOut;

    private final TicketEntityMapper ticketEntityMapper;

    @Override
    public Mono<TicketResponseDTO> create(TicketRequestDTO ticketRequestDTO) {
        var ticket = ticketEntityMapper.toBusiness(ticketRequestDTO);
        return ticketRepositoryPortOut.save(ticket).map(ticketEntityMapper::toResponseDto);
    }

    @Override
    public Mono<TicketResponseDTO> update(Long id, TicketRequestDTO ticketRequestDTO) {
        var ticket = ticketEntityMapper.toBusiness(ticketRequestDTO);
        ticket.setId(id);
        return ticketRepositoryPortOut.update(ticket).map(ticketEntityMapper::toResponseDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return ticketRepositoryPortOut.deleteById(id);
    }

    @Override
    public Flux<TicketResponseDTO> getALl() {
        return ticketRepositoryPortOut.findAll().map(ticketEntityMapper::toResponseDto);
    }

    @Override
    public Mono<TicketResponseDTO> getById(Long id) {
        return ticketRepositoryPortOut.findById(Long.valueOf(id)).map(ticketEntityMapper::toResponseDto);
    }
}
