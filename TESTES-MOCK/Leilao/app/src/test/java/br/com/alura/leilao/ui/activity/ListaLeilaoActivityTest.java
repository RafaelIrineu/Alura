package br.com.alura.leilao.ui.activity;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoActivityTest {

    @Mock
    private Context context;
    @Spy
    private ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);
    @Mock //queremos apenas simular
    private LeilaoWebClient client;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        Mockito.doNothing().when(adapter).atualizaLista();

        //primeiro criamos o doAnswer para poder modificar o comportamento do
        //respostaListener, e dentro dele quando o todos() for executado
        //ele vai chamar o sucesso enviando a lista

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<Leilao>(Arrays.asList(
                        new Leilao("Computador"),
                        new Leilao("Carro"))));
                return null;
            }
        }).when(client).todos(ArgumentMatchers.any(RespostaListener.class));

        activity.buscaLeiloes(adapter, client);
        //colocar um delay devido a chamada assincrona, para dar tempo de retornar valor
        Thread.sleep(2000);
        int quantidadeDeLeiloesDevolvida = adapter.getItemCount();

        assertThat(quantidadeDeLeiloesDevolvida,is(2));
    }
}