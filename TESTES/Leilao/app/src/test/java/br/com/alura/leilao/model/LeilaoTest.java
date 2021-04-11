package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
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

        assertEquals(100.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 100.0));
        console.propoe(new Lance(rafael, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 1000.0));
        console.propoe(new Lance(rafael, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(1000.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        console.propoe(new Lance(rafael, 100.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 100.0));
        console.propoe(new Lance(rafael, 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(new Usuario("Mariana"), 1000.0));
        console.propoe(new Lance(rafael, 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances() {
        console.propoe(new Lance(rafael, 200.0));
        console.propoe(new Lance(new Usuario("Mariana"), 300.0));
        console.propoe(new Lance(rafael, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeUmLance() {
        console.propoe(new Lance(rafael, 200.0));

        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeDoisLances() {
        console.propoe(new Lance(rafael, 200.0));
        console.propoe(new Lance(new Usuario("Mariana"), 300.0));

        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        console.propoe(new Lance(rafael, 300.0));
        final Usuario MARIANA = new Usuario("Mariana");
        console.propoe(new Lance(MARIANA, 400.0));
        console.propoe(new Lance(rafael, 500.0));
        console.propoe(new Lance(MARIANA, 600.0));

        final List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = console.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(600.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        console.propoe(new Lance(rafael, 700.0));

        final List<Lance> tresMaioresLancesDevolvidosParaCincoLances = console.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }
}