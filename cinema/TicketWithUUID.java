package cinema;

import java.util.UUID;

public class TicketWithUUID {
    private UUID token;
    private Seat ticket;

    public TicketWithUUID(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }
    public TicketWithUUID(UUID token) {
        this.token = token;
    }
    public TicketWithUUID() {

    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
