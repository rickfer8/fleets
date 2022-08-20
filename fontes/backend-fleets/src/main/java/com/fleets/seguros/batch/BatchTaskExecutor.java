package com.fleets.seguros.batch;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * BatchTaskExecutor.
 */
@Component
public class BatchTaskExecutor extends ThreadPoolTaskExecutor{

	private static final long serialVersionUID = 3270212138834123181L;
	
	@Value("${app.batch.process.parallel: 2}")
    private Integer processesInParallel;

    /**
     * Configure.
     */
    @PostConstruct
    public void configure() {
        setThreadNamePrefix("batch-pool");
        setCorePoolSize(processesInParallel);
        setMaxPoolSize(processesInParallel);
        initialize();
    }
}
