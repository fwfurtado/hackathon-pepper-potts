package br.com.caelum.pepperpotts.configuration;

import br.com.caelum.pepperpotts.domain.Solution;
import java.time.ZoneId;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllocatorAutoConfiguration {

    @Bean
    SolverFactory<Solution> solverFactory(AllocationProperties properties) {
        return SolverFactory.createFromXmlResource(properties.getConfigurationFile());
    }

    @Bean
    Solver<Solution> solver(SolverFactory<Solution> factory) {
        return factory.buildSolver();
    }

    @Bean
    @ConditionalOnMissingBean
    ZoneId zoneId() {
        return ZoneId.systemDefault();
    }
}
