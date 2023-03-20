package med.voll.api.domain.consulta;

import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

	private final ConsultaRepository consultaRepository;
	
	private final MedicoRepository medicoRepository;
	
	private final PacienteRepository pacienteRepository;

	

	public AgendaDeConsultas(final ConsultaRepository consultaRepository, final MedicoRepository medicoRepository,
			final PacienteRepository pacienteRepository) {
		this.consultaRepository = consultaRepository;
		this.medicoRepository = medicoRepository;
		this.pacienteRepository = pacienteRepository;
	}



	public void agendar(DadosAgendamentoConsulta dados) {
				
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			 throw new ValidacaoException("Id do paciente não existe");
		}
		
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do médico não existe");
		}
		
		Medico medico = escolherMedico(dados);
		Paciente paciente = pacienteRepository.findById(dados.idPaciente()).get();
	
		Consulta consulta = new Consulta(null, medico,paciente, dados.data());
		consultaRepository.save(consulta);
	}



	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if(dados.idMedico()!= null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		if(dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido");
		}
		
		return medicoRepository.escolherMedicoAleatorioDisponivelNaData(dados.especialidade(),dados.data());
	}
	
	
	
	
}
