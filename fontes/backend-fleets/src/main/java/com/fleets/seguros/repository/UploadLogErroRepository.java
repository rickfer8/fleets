package com.fleets.seguros.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fleets.seguros.model.batch.UploadLogErro;



/**
 * UploadLogErroRepository
 *
 */
@Repository
public interface UploadLogErroRepository extends JpaRepository<UploadLogErro, Long> {

	/**
     * Find by ajustamento id.
     *
     * @param jobExecutionId the ajustamento id
     * @return {@link List}
     */
    List<UploadLogErro> findByJobExecutionId(BigDecimal jobExecutionId);

}