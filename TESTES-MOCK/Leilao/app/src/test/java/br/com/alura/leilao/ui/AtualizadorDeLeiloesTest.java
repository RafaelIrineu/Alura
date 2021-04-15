package br.com.alura.leilao.ui;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeLeiloesTest {

    @Mock
    private ListaLeilaoAdapter adapter;
    @Mock //queremos apenas simular
    private LeilaoWebClient client;
    @Mock
    private Context context;
    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() {
        AtualizadorDeLeiloes atualizador = new AtualizadorDeLeiloes();

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

        atualizador.buscaLeiloes(adapter, client, context);

        //precisamos verificar se os métodos foram chamados
        verify(client).todos(any(RespostaListener.class));

        //precisou colocar equals e hash codes em Leilao
        verify(adapter).atualiza(new ArrayList<Leilao>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro"))));
    }

    @Test
    public void deve_ApresentarFalha_QuandoFalharABuscaDeLeiloes(){
        AtualizadorDeLeiloes atualizador = Mockito.spy(new AtualizadorDeLeiloes());
        doNothing().when(atualizador).mostraMensagemDeFalha(context);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.falha(anyString());
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));

        atualizador.buscaLeiloes(adapter, client, context);

        verify(atualizador).mostraMensagemDeFalha(context);
    }
}