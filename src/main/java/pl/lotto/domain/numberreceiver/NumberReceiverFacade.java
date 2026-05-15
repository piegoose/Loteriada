package pl.lotto.domain.numberreceiver;


import lombok.AllArgsConstructor;
import pl.lotto.domain.numberreceiver.dto.InputNumbersResultDto;

import java.util.Set;
//builder input
import static pl.lotto.domain.numberreceiver.dto.InputNumbersResultDto.*;

@AllArgsConstructor
public class NumberReceiverFacade {

    private NumberValidator validator;

    public InputNumbersResultDto filteredNumbers(Set<Integer> numbersFromUser) {
        boolean areAllNumbersInRange = validator.areAllNumbersInRange(numbersFromUser);
        if (areAllNumbersInRange) {
            return builder()
                    .message("success")
                    .build();
        }
        return builder()
                .message("failed")
                .build();
    }


}
