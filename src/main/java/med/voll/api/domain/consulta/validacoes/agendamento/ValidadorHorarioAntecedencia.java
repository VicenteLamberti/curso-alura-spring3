package med.voll.api.domain.consulta.validacoes.agendamento;

import java.time.DayOfWeek;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;


@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{
	
	public void validar(DadosAgendamentoConsulta dados) {
		LocalDateTime dataConsulta = dados.data();
		
		boolean ehDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
		boolean depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;
		if(ehDomingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
			throw new ValidacaoException("Consulta do horário de funcionamento");
		}
		
	}

}
