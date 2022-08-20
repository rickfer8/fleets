package com.fleets.seguros.batch.process;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * ProcessTaskExecutor.
 */
@Component
public class ProcessTaskExecutor extends ThreadPoolTaskExecutor {

	private static final long serialVersionUID = -5453166241239599408L;
	
	@Value("${app.batch.process.threads: 1}")
    private Integer processThreads;

    /**
     * Configure.
     */
    @PostConstruct
    public void configure() {
        setThreadNamePrefix("process-pool");
        setCorePoolSize(getProcessThreads());
        setMaxPoolSize(getProcessThreads());
        initialize();
    }

    /**
     * Retorna o process threads.
     *
     * @return ProcessThreads
     */
    public Integer getProcessThreads() {
        return processThreads;
    }
}
