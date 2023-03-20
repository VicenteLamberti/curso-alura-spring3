package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);
	
	

	@Query("""
			SELECT med FROM Medico med
			WHERE med.ativo = 1
			AND med.especialidade = :especialidade
			AND med.id NOT IN
			(
				SELECT consulta.medico FROM Consulta consulta
				WHERE consulta.data = :data
				AND consulta.motivoCancelamento IS NULL
			)
			ORDER BY RAND()
			LIMIT 1
			""")
	Medico escolherMedicoAleatorioDisponivelNaData(Especialidade especialidade, LocalDateTime data);



	@Query("""
			SELECT medico.ativo FROM Medico medico
			WHERE medico.id = :idMedico
			""")
	Boolean findAtivoById(Long idMedico);

}
