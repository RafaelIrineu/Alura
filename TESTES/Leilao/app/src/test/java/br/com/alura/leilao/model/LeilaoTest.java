package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        //criar o cenário de teste
        Leilao console = new Leilao("Console");
        //executar a ação esperada
        String descricaoDevolvida = console.getDescricao();
        //testar o resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Rafa"), 100.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(100.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Mari"), 100.0));
        computador.propoe(new Lance(new Usuario("Fael"), 200.0));

        double maiorLanceDevolvido = computador.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Mari"), 1000.0));
        carro.propoe(new Lance(new Usuario("Fael"), 200.0));

        double maiorLanceDevolvido = carro.getMaiorLance();

        assertEquals(1000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Rafa"), 100.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Mari"), 100.0));
        computador.propoe(new Lance(new Usuario("Fael"), 200.0));

        double menorLanceDevolvido = computador.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Mari"), 1000.0));
        carro.propoe(new Lance(new Usuario("Fael"), 200.0));

        double menorLanceDevolvido = carro.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }
}