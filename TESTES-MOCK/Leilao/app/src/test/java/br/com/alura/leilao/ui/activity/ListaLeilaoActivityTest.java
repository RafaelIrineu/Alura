package br.com.alura.leilao.ui.activity;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListaLeilaoActivityTest {

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        //colocar um delay devido a chamada assincrona, para dar tempo de retornar valor
        activity.configuraAdapter();
        activity.buscaLeiloes();
        Thread.sleep(2000);
        int quantidadeDeLeiloesDevolvida = activity.getAdapter().getItemCount();

        assertThat(quantidadeDeLeiloesDevolvida,is(3));
    }
}