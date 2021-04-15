package br.com.alura.leilao.ui.activity;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoActivityTest {

    @Mock
    private Context context;
    @Spy
    private ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        Mockito.doNothing().when(adapter).atualizaLista();

        activity.buscaLeiloes(adapter);
        //colocar um delay devido a chamada assincrona, para dar tempo de retornar valor
        Thread.sleep(2000);
        int quantidadeDeLeiloesDevolvida = adapter.getItemCount();

        assertThat(quantidadeDeLeiloesDevolvida,is(3));
    }
}