package com.doublevpartners.tickets.services;
import com.doublevpartners.tickets.exception.TicketsException;
import com.doublevpartners.tickets.model.Ticket;
import java.util.List;

public interface TicketServices {
    public Ticket createTicket(Ticket t) throws TicketsException;
    public void deleteTicket(int id) throws TicketsException;
    public void updateTicket(int id, Ticket t) throws TicketsException;
    public Ticket getTicketbyId (int id)  throws TicketsException;
    public List<Ticket> getAllTickets ();
}
