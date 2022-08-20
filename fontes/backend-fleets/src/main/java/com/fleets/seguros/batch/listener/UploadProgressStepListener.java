package com.fleets.seguros.batch.listener;

import static com.fleets.seguros.util.ExcelUtil.calcularTotalRegistros;
import static com.fleets.seguros.util.ExcelUtil.carregarPrimeiraAbaPlanilha;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;

import java.io.IOException;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.component.UploadMessagesConfiguration;
import com.fleets.seguros.constante.JobExecutionParametrosConstantes;
import com.fleets.seguros.exception.batch.FalhaLeituraArquivoException;
import com.fleets.seguros.model.Arquivo;
import com.fleets.seguros.repository.ArquivoRepository;
import com.fleets.seguros.service.ArquivoService;

import lombok.extern.slf4j.Slf4j;

/**
 * The listener interface for receiving uploadProgressStep events.
 * The class that is interested in processing a uploadProgressStep
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addUploadProgressStepListener<code> method. When
 * the uploadProgressStep event occurs, that object's appropriate
 * method is invoked.
 *
 * @see UploadProgressStepEvent
 */
@Component
@Slf4j
public class UploadProgressStepListener extends ProgressStepListener {

	@Autowired
	private ArquivoRepository arquivoRepository;

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private UploadMessagesConfiguration messagesConfiguration;

	@Override
	protected long getContagemTotal(JobParameters parameters) {
		final Long arquivoId = parameters.getLong(JobExecutionParametrosConstantes.ARQUIVO_ID_PARAM_NAME);
		
		final Arquivo arquivo = arquivoRepository.findById(arquivoId).get();

		try {
			final Sheet planilha = carregarPrimeiraAbaPlanilha(arquivoService.getConteudo(arquivo));
			return calcularTotalRegistros(planilha);
		} catch (InvalidFormatException | IOException e) {
			log.error("[UploadProgressStepListener.getContagemTotal]", e);
			return LONG_MINUS_ONE;
		} catch (RecordInputStream.LeftoverDataException e) {
			log.error("[UploadProgressStepListener.getContagemTotal]", e);
			throw new FalhaLeituraArquivoException(
					messagesConfiguration.getFalhaLeituraArquivoException(arquivo.getNomeArquivo(), arquivo.getTipoExtensao())
			);
		}
	}

}
