package pl.lotto.domain.numberreceiver;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
record TicketDto(LocalDateTime drawTime, String ticketId, Set<Integer>numbersFromUser) {
}
