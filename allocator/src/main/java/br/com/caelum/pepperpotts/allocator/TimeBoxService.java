package br.com.caelum.pepperpotts.allocator;

import static java.util.stream.IntStream.rangeClosed;

import br.com.caelum.pepperpotts.domain.Task;
import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class TimeBoxService {

    private static final int THIRTY_MINUTES = 30;
    private final ZoneId zoneId;

    public TimeBoxService(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public List<TimeBox> generateBy(LocalDate reference, List<Task> tasks) {

        return rangeClosed(0, 23)
            .mapToObj(hour -> toPairDateTime(reference, hour))
            .flatMap(List::stream)
            .map(TimeBox::new)
            .collect(Collectors.toList());
    }

    private List<ZonedDateTime> toPairDateTime(LocalDate reference, int hour) {
        ZonedDateTime full = ZonedDateTime.of(reference, LocalTime.of(hour, 0), zoneId);
        ZonedDateTime half = ZonedDateTime.of(reference, LocalTime.of(hour, THIRTY_MINUTES), zoneId);

        return Arrays.asList(full, half);
    }
}
