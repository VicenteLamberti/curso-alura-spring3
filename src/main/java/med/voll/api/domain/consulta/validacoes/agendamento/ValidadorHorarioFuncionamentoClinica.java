package med.voll.api.domain.consulta.validacoes.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {
	
	public void validar(DadosAgendamentoConsulta dados) {
		LocalDateTime dataConsulta = dados.data();
		
		LocalDateTime agora = LocalDateTime.now();
		
		long diferenca = Duration.between(agora, dataConsulta).toMinutes();
		
		if(diferenca < 30) {
			throw new ValidacaoException("Antecedencia minima deve ser de 30 minutos");
		}
		
	}

}
