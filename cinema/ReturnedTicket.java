package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ReturnedTicket {
    @JsonProperty("returned_ticket")
    private final Seat returnedTicket;

    public ReturnedTicket(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
}
