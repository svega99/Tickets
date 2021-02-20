package com.doublevpartners.tickets.services.impl;

import com.doublevpartners.tickets.exception.TicketsException;
import com.doublevpartners.tickets.model.Ticket;
import com.doublevpartners.tickets.persistance.TicketRepository;
import com.doublevpartners.tickets.services.TicketServices;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TicketServicesImpl implements TicketServices{
    @Autowired
    TicketRepository ticketRepository;
    @Override
    public Ticket createTicket(Ticket t) throws TicketsException {
        List<Ticket> tickets = getAllTickets();
        Collections.sort(tickets, (o1, o2) -> new Integer (o1.getID()).compareTo(new Integer(o2.getID())));
        Ticket lastTicket = tickets.get(tickets.size()-1);
        int id = lastTicket.getID() + 1 ;
        Date date = new Date();
        t.setID(id);
        t.setCreationDate(date);
        t.setUpdateDate(date);
        ticketRepository.createOrUpdateTicket(t);
        return t;
    }
    @Override
    public void deleteTicket(int id) throws TicketsException {
        ticketRepository.deleteTicket(id);
    }
    @Override
    public void updateTicket(int id, Ticket newTicket) throws TicketsException {
        Ticket oldTicket = getTicketbyId(id);
        Date date = new Date();
        try {
            oldTicket.setUpdateDate(date);
            oldTicket.setUser(newTicket.getUser());
            oldTicket.setStatus(newTicket.getStatus());
            ticketRepository.createOrUpdateTicket(oldTicket);
        }catch(NullPointerException ex){
            throw new TicketsException("Ticket not found");
        }
    }
    @Override
    public Ticket getTicketbyId(int id) throws TicketsException {
        Ticket ticket=ticketRepository.getTicketbyId(id);
        return ticket;
    }
    @Override
    public List<Ticket> getAllTickets(){
        return ticketRepository.getAllTickets();
    }
    public int getNumberOfTickets() {
        return ticketRepository.getNumberOfTickets();
    }
}
