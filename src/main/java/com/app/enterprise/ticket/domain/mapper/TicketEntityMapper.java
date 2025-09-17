package com.app.enterprise.ticket.domain.mapper;

import com.app.enterprise.ticket.domain.business.Ticket;
import com.app.enterprise.ticket.domain.db.TicketEntity;
import com.app.enterprise.ticket.domain.rest.TicketRequestDTO;
import com.app.enterprise.ticket.domain.rest.TicketResponseDTO;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface TicketEntityMapper {
    TicketEntity toEntity(Ticket businness);
    Ticket toBusiness(TicketEntity entity);
    Ticket toBusiness(TicketRequestDTO entity);
    TicketResponseDTO toResponseDto(Ticket objBusiness);
    TicketResponseDTO toResponseDtoFromEntity(TicketEntity objEntity);

}
