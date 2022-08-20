package com.fleets.seguros.batch.listener;


import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fleets.seguros.batch.process.ProcessTaskExecutor;
import com.fleets.seguros.batch.process.UploadProcessor;
import com.fleets.seguros.batch.reader.UploadReader;
import com.fleets.seguros.batch.writer.UploadWriter;
import com.fleets.seguros.dto.UploadProcessorDTO;
import com.fleets.seguros.model.Cotacao;


/**
 * Classe pra criar os passos do processo de upload utilizado pelo Spring Batch.
 */
@Component
public class UploadStepBuilder {

    private static final String UPLOAD_SINGLE_STEP = "uploadStep";
    private static final Integer DOIS = 2;

    /**
     * Quantidade de processamentos a acumular para despachar para o writer
     */
    @Value("${app.batch.process.upload.chunk-size: 1}")
    private Integer chunkSize;
    
    /**
     * Quantidade de threads liberadas para este step
     */
    private Integer maxThreads = DOIS;
    
    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private UploadReader reader;

    @Autowired
    private UploadProcessor processor;

    @Autowired
    private UploadWriter writer;

    @Autowired
    private ProcessTaskExecutor executor;
    
    @Autowired
    private UploadProgressStepListener progressStepListener;
    
    @Autowired
    private ProgressChunkListener progressChunkListener;

    @Autowired
    private UploadLogErroJobListener uploadLogErroJobListener;
    
    /**
     * Constroí objeto Step.
     *
     * @return {@link Step}
     */
    public Step build() {
        final SimpleStepBuilder<UploadProcessorDTO, Cotacao> builder = steps.get(UPLOAD_SINGLE_STEP).chunk(chunkSize);

        builder.reader(reader)
		    	.listener(progressStepListener)
		        .listener(progressChunkListener)
                .listener(uploadLogErroJobListener);

        builder.processor(processor)
                .taskExecutor(executor)
                .throttleLimit(maxThreads);

        builder.writer(writer)
                .allowStartIfComplete(true);
        
        return builder.build();
    }

}
