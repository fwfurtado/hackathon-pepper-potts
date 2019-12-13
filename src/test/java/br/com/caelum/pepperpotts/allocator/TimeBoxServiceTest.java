package br.com.caelum.pepperpotts.allocator;

import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TimeBoxServiceTest {

    private static final LocalDate DATE_REFERENCE = LocalDate.of(2019, Month.MAY, 18);

    @Test
    void shouldReturnARangeOfTimeBoxesByLocalDate() {
        TimeBoxService service = new TimeBoxService(ZoneId.systemDefault());

        List<TimeBox> timeBoxes = service.generateBy(DATE_REFERENCE, tasks);

        Assertions.assertThat(timeBoxes).hasSize(48);
        Assertions.assertThat(timeBoxes).first().isEqualTo(new TimeBox(ZonedDateTime.of(DATE_REFERENCE, LocalTime.of(0,0), ZoneId.systemDefault())));
        Assertions.assertThat(timeBoxes).last().isEqualTo(new TimeBox(ZonedDateTime.of(DATE_REFERENCE, LocalTime.of(23,30), ZoneId.systemDefault())));
    }
}