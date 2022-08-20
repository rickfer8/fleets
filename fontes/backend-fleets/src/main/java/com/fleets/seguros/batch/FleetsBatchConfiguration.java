package com.fleets.seguros.batch;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fleets.seguros.batch.listener.UploadJobConfiguration;
import com.fleets.seguros.batch.process.JobService;

/**
 * BatchConfiguration.
 */

@Configuration
@EnableBatchProcessing
@Import({UploadJobConfiguration.class})
public class FleetsBatchConfiguration {
	
	 @Autowired
	    private JobService jobService;

	    /**
	     * Inicia o.
	     */
	    @PostConstruct
	    public void init() {
	        jobService.failUncompletedExecutions();
	    }

	    /**
	     * Async job launcher.
	     *
	     * @param repository the repository
	     * @param taskExecutor the task executor
	     * @return {@link JobLauncher}
	     */
	    @Bean
	    public JobLauncher asyncJobLauncher(JobRepository repository, BatchTaskExecutor taskExecutor) {
	        final SimpleJobLauncher launcher = new SimpleJobLauncher();
	        launcher.setJobRepository(repository);
	        launcher.setTaskExecutor(taskExecutor);
	        return launcher;
	    }

	    /**
	     * Post processor.
	     *
	     * @param jobRegistry the job registry
	     * @return {@link JobRegistryBeanPostProcessor}
	     */
	    @Bean
	    public JobRegistryBeanPostProcessor postProcessor(JobRegistry jobRegistry) {
	        final JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
	        postProcessor.setJobRegistry(jobRegistry);
	        return postProcessor;
	    }

}
