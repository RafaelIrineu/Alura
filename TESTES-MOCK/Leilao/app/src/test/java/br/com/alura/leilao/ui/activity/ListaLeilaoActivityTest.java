package br.com.alura.leilao.ui.activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoActivityTest {

    @Mock
    private ListaLeilaoAdapter adapter;
    @Mock //queremos apenas simular
    private LeilaoWebClient client;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        //primeiro criamos o doAnswer para poder modificar o comportamento do
        //respostaListener, e dentro dele quando o todos() for executado
        //ele vai chamar o sucesso enviando a lista

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<Leilao>(Arrays.asList(
                        new Leilao("Computador"),
                        new Leilao("Carro"))));
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));

        activity.buscaLeiloes(adapter, client);
        //colocar um delay devido a chamada assincrona, para dar tempo de retornar valor
        //Thread.sleep(2000);
        //não precisa mais do sleep pois estamos simulando o comportamento da api

        //precisamos verificar se os métodos foram chamados
        verify(client).todos(any(RespostaListener.class));

        //precisou colocar equals e hash codes em Leilao
        verify(adapter).atualiza(new ArrayList<Leilao>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro"))));
    }
}