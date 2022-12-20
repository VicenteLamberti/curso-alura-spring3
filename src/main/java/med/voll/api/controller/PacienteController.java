package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
    	Paciente pacienteSalvo = pacienteRepository.save(new Paciente(dados));
    	URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteSalvo.getId()).toUri();
    	return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(pacienteSalvo));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalharPaciente(@PathVariable Long id){
    	Paciente paciente = pacienteRepository.getReferenceById(id);
    	return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    
    @GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listarPacientes(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
		return ResponseEntity.ok(pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new));
	}
    
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizarPaciente(@Valid @RequestBody DadosAtualizacaoPaciente dados) {
    	var paciente = pacienteRepository.getReferenceById(dados.id());
    	paciente.atualizarInformacoes(dados);
    	return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativarPaciente(@PathVariable Long id) {
    	var paciente = pacienteRepository.getReferenceById(id);
    	paciente.inativar();
    	return ResponseEntity.noContent().build();
    }
}