package com.app.enterprise.ticket.domain.db;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table(name = "tickets")
public class TicketEntity {

    @Id
    private Long id;

    private String title;
    private String description;

    public TicketEntity() {}

    public TicketEntity(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

}