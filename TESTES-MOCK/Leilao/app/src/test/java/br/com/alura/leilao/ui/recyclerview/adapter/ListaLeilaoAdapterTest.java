package br.com.alura.leilao.ui.recyclerview.adapter;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import br.com.alura.leilao.model.Leilao;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoAdapterTest {

    @Mock
    private Context context;
    @Spy
    ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoReceberListaDeLeiloes(){

        //mockito, não faça nada quando o adapter chamar o notify
        //o mockito não consegue alterar o comportamento de métodos ou classes final ou estáticos
        //extrair o notify para um método publico
        doNothing().when(adapter).atualizaLista();

        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computador"))));
        int quantidadeLeiloesDevolvida = adapter.getItemCount();

        //serve para verificar se de fato o método foi chamado, através do objeto mocado
        verify(adapter).atualizaLista();
        assertThat(quantidadeLeiloesDevolvida,is(2));
    }
}