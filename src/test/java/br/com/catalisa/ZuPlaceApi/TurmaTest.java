package br.com.catalisa.ZuPlaceApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TurmaTest {

    private Turma turma;

    @BeforeEach
    public void setUp() {
        turma = new Turma();
    }

    @Test
    public void testSeAdicionaUmaPessoa() throws Exception {
        Pessoa pessoa = new Pessoa(1, "Guilherme");
        turma.adicionarPessoa(pessoa);
        assertEquals(1, turma.getPessoas().size());
    }

    @Test
    public void testAdicionarPessoaInvalida() {
        Pessoa pessoa = new Pessoa(-1, "");
        assertThrows(Exception.class, () -> turma.adicionarPessoa(pessoa));
    }

    @Test
    public void testAdicionarPessoaComIdInvalido() {
        Pessoa pessoa = new Pessoa(-1, "Gui");
        assertThrows(Exception.class, () -> turma.adicionarPessoa(pessoa));
    }

    @Test
    public void testAdicionarPessoaComNomeInvalido() {
        Pessoa pessoa = new Pessoa(1, "");
        assertThrows(Exception.class, () -> turma.adicionarPessoa(pessoa));
    }

    @Test
    public void testAdicionarPessoaComMesmoIDDeveLancarExcecao() throws Exception {
        Pessoa pessoa1 = new Pessoa(1, "Gui");
        Pessoa pessoa2 = new Pessoa(1, "Mary");

        turma.adicionarPessoa(pessoa1);

        assertThrows(Exception.class, () -> turma.adicionarPessoa(pessoa2));
    }

    @Test
    public void testRemoverTodasPessoas() throws Exception {
        Pessoa pessoa1 = new Pessoa(1, "Gui");
        Pessoa pessoa2 = new Pessoa(2, "Mary");

        turma.adicionarPessoa(pessoa1);
        turma.adicionarPessoa(pessoa2);

        assertEquals(2, turma.getPessoas().size());

        turma.removerTodasPessoas();

        assertEquals(0, turma.getPessoas().size());
    }
}
