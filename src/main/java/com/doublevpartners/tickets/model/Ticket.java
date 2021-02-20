package com.doublevpartners.tickets.model;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Ticket implements Serializable{
    private int ID;
    private String user;
    private Date creationDate;
    private Date updateDate;
    private Status status;
    
    public Ticket(int ID, String user, Date creationDate, Date updateDate, Status status) {
        this.ID = ID;
        this.user = user;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.status = status;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
