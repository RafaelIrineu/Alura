package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    private final Leilao console = new Leilao("Console");
    private final Usuario rafael = new Usuario("Rafael");

    //final pois não queremos modificá-los em nenhum momento dos testes

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        //criar o cenário de teste
        //executar a ação esperada
        String descricaoDevolvida = console.getDescricao();
        //testar o resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        console.propoe(new Lance(rafael, 100.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(100.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 100.0));
        console.propoe(new Lance(rafael, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 1000.0));
        console.propoe(new Lance(rafael, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(1000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        console.propoe(new Lance(rafael, 100.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 100.0));
        console.propoe(new Lance(rafael, 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 1000.0));
        console.propoe(new Lance(rafael, 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        console.propoe(new Lance(rafael,200.0));
        console.propoe(new Lance(new Usuario("Mariana"),300.0));
        console.propoe(new Lance(rafael,400.0));

        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        assertEquals(3,tresMaioresLancesDevolvidos.size());
    }
}