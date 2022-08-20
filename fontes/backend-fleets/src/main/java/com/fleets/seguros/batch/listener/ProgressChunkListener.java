package com.fleets.seguros.batch.listener;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.service.batch.JobExecutionDataService;



/**
 * The listener interface for receiving progressChunk events.
 * The class that is interested in processing a progressChunk
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addProgressChunkListener<code> method. When
 * the progressChunk event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ProgressChunkEvent
 */
@StepScope
@Component
public class ProgressChunkListener implements ChunkListener {

	@Autowired
	private JobExecutionDataService jobExecutionDataService;

	private Long ultimaContagem = LONG_ZERO;
	
	@Override
	public void beforeChunk(ChunkContext context) {
		// do nothing
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		// do nothing
	}
	
	@Override
	public void afterChunk(ChunkContext context) {
		final StepExecution stepExecution = context.getStepContext().getStepExecution();
		atualizarContagem(stepExecution.getJobExecution(), Long.valueOf(stepExecution.getWriteCount()));
	}

	private synchronized void atualizarContagem(final JobExecution jobExecution, final Long contagem) {
		if (contagem > ultimaContagem) {
			jobExecutionDataService.atualizar(jobExecution.getId(), contagem);
			ultimaContagem = contagem;
		}
	}
}