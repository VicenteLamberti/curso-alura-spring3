package med.voll.api.domain.consulta;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosDetalhamentoConsulta(
			Long id, 
			@JsonProperty("id_medico")
			Long idMedico, 
			@JsonProperty("id_paciente")
			Long idPaciente, 
			LocalDateTime data
		) {

}
