
package com.doublevpartners.tickets.persistance.impl;

import com.doublevpartners.tickets.exception.TicketsException;
import com.doublevpartners.tickets.model.Ticket;
import com.doublevpartners.tickets.persistance.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepositoryImpl implements TicketRepository{
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY="TICKET";
    @Override
    public int getNumberOfTickets() {
         return redisTemplate.opsForHash().values(KEY).size();
    }
    @Override
    public void createOrUpdateTicket(Ticket ticket) throws TicketsException {
        try {
             redisTemplate.opsForHash().put(KEY,ticket.getID(),ticket);
        }
        catch(Exception e){
            throw new TicketsException("Ticket could not be created or updated");
        }
    }
    @Override
    public Ticket getTicketbyId(int id) {
        Ticket ticket = (Ticket)redisTemplate.opsForHash().get(KEY,id);
        return ticket;
    }
    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = redisTemplate.opsForHash().values(KEY);
        return tickets;
    }
    @Override
    public void deleteTicket(int id) {
        redisTemplate.opsForHash().delete(KEY, id);
    }
}
