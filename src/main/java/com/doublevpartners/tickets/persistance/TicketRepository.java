package com.doublevpartners.tickets.persistance;
import com.doublevpartners.tickets.exception.TicketsException;
import com.doublevpartners.tickets.model.Ticket;
import java.util.List;

public interface TicketRepository {
    public int getNumberOfTickets();
    public void createOrUpdateTicket(Ticket ticket) throws TicketsException;
    public Ticket getTicketbyId(int id);
    public List<Ticket> getAllTickets();
    public void deleteTicket(int id);
}
