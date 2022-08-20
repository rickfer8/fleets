package com.fleets.seguros.batch.listener;

import static com.fleets.seguros.enums.JobNamesEnum.UPLOAD;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.fleets.seguros.batch.process.UploadCompositeJobListener;


/**
 * UploadJobConfiguration.
 */
public class UploadJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    /**
     * Upload job.
     *
     * @param stepBuilder the step builder
     * @param compositeListener the composite listener
     * @return {@link Job}
     */
    @Bean
    public Job uploadJob(final UploadStepBuilder stepBuilder, UploadCompositeJobListener compositeListener) {
    	
    	return jobs.get(UPLOAD.name())
                .start(stepBuilder.build())
                .listener(compositeListener)
                .build();
    }
    
}
