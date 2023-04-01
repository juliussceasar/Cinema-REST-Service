package cinema;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema(9, 9);
    List<TicketWithUUID> tickets = new ArrayList<>(); //bought tickets
    InTotal inTotal = new InTotal(cinema.available_seats.size(),
            0);

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buySeat(@RequestBody Seat seat2) {
        for (Seat seat : cinema.available_seats) {
            if (seat.getRow() == seat2.getRow()
                    && seat.getColumn() == seat2.getColumn()) {
                if (!seat.isTaken()) {
                    seat.setTaken(true);
                    TicketWithUUID ticket = new TicketWithUUID(UUID.randomUUID(), seat);
                    tickets.add(ticket);
                    inTotal.setCurrentIncome(inTotal.getCurrentIncome() + ticket.getTicket().getPrice());
                    return new ResponseEntity<>(ticket,
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new AppError("The ticket has been already purchased!"),
                            HttpStatus.BAD_REQUEST);
                }
            }
        }
        return new ResponseEntity<>(new AppError("The number of a row or a column is out of bounds!"),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnSeat(@RequestBody TicketWithUUID ticket1) {
        UUID token = ticket1.getToken();
        for (TicketWithUUID ticket : tickets) {
            if (ticket.getToken().equals(token) && tickets.contains(ticket)) {
                ReturnedTicket returnedTicket = new ReturnedTicket(ticket.getTicket());
                tickets.remove(ticket);
                ticket.getTicket().setTaken(false);
                inTotal.setCurrentIncome(inTotal.getCurrentIncome() - ticket.getTicket().getPrice());
                return new ResponseEntity<>(returnedTicket,
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new AppError("Wrong token!"),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> returnStats(@RequestParam Optional<String> password) {
        if (password.isPresent() && "super_secret".equals(password.get())) {
            inTotal.setNumberOfAvailableSeats(cinema.available_seats.size() - tickets.size());
            inTotal.setNumberOfPurchasedTickets(tickets.size());
            return new ResponseEntity<>(inTotal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AppError("The password is wrong!"),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
