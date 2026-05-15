package pl.lotto.domain.numberreceiver;


import lombok.AllArgsConstructor;
import pl.lotto.domain.numberreceiver.dto.InputNumbersResultDto;


import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
//builder input
import static pl.lotto.domain.numberreceiver.dto.InputNumbersResultDto.*;

@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private final NumberReceiverRepository repository;
    private final Clock clock;

    public InputNumbersResultDto inputNumbers(Set<Integer> numbersFromUser) {
        boolean areAllNumbersInRange = validator.areAllNumbersInRange(numbersFromUser);
        if (areAllNumbersInRange) {
            String ticketId = UUID.randomUUID().toString();
            LocalDateTime drawDate = LocalDateTime.now(clock);
            Ticket save = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            return builder()
                    .drawDate(save.drawDate())
                    .ticketId(save.ticketId())
                    .numbersFromUser(numbersFromUser)
                    .message("success")
                    .build();
        }
        return builder()
                .message("failed")
                .build();
    }

    public List<TicketDto> userNumbers(LocalDateTime date){
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }

}
