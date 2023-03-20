package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
		
		    @JsonAlias({"id_medico","medico_id"})
			Long idMedico,
			
			@NotNull
			@JsonAlias({"id_paciente","paciente_id"})
			Long idPaciente,
			
			@NotNull
			@Future
			LocalDateTime data
			
			
		) {

}
