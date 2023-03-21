package med.voll.api.domain.medico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Assim é pra utilizar o próprio banco Mysql
@ActiveProfiles("test")
class MedicoRepositoryTest {

	@Test
	void testEscolherMedicoAleatorioDisponivelNaData() {
		
	}

}
