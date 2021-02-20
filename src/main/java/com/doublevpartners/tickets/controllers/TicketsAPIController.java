package com.doublevpartners.tickets.controllers;

import com.doublevpartners.tickets.exception.TicketsException;
import com.doublevpartners.tickets.model.Ticket;
import com.doublevpartners.tickets.services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ticket")
public class TicketsAPIController {
    @Autowired
    TicketServices ticketServices;
    @RequestMapping(method = RequestMethod.POST)	
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket){
        try {
            return new ResponseEntity<>(ticketServices.createTicket(ticket),HttpStatus.CREATED);
        } catch (TicketsException ex) {
            return new ResponseEntity<>("ERROR: "+ex.getMessage(),HttpStatus.BAD_REQUEST);            
        }
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)	
    public ResponseEntity<?> deleteTicket(@PathVariable ("id") int id){
        try {          
            ticketServices.deleteTicket(id);
            return new ResponseEntity<>("Ticket with id "+id+" deleted",HttpStatus.ACCEPTED);
        } catch (TicketsException ex) {
            return new ResponseEntity<>("ERROR: "+ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)	
    public ResponseEntity<?> updateTicket(@PathVariable ("id") int id, @RequestBody Ticket ticket){
        try { 
            ticketServices.updateTicket(id, ticket);
            return new ResponseEntity<>("Ticket with id "+id+" updated",HttpStatus.ACCEPTED);
        } catch (TicketsException ex) {
            return new ResponseEntity<>("ERROR: "+ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllTickets() {
        try {
            return new ResponseEntity<>(ticketServices.getAllTickets(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR: "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getTicketbyId(@PathVariable ("id") int id) {
        try {
            return new ResponseEntity<>(ticketServices.getTicketbyId(id), HttpStatus.ACCEPTED);
        } catch (TicketsException e) {
            return new ResponseEntity<>("ERROR: "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
