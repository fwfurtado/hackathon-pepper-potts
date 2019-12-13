package br.com.caelum.pepperpotts.api;

import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AllocatorController {

    private final AllocatorService service;

    public AllocatorController(AllocatorService service) {
        this.service = service;
    }

    @PostMapping(value = "allocate/{date}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<TimeBox> allocateBy(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestBody @Valid TasksInputWrapper input) {
        return service.generateAllocationFor(input, date);
    }
}
