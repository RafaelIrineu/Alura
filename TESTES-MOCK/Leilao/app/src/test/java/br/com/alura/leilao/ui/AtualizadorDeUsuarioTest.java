package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deve_AtualizarListaDeUsuarios_QuandoSalvarUsuario(){
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(dao, adapter, recyclerView);

        Usuario mari = new Usuario("Mari");
        when(dao.salva(mari)).thenReturn(new Usuario(1,"Mari"));
        //o thenReturn pega o método que está sendo modificiado e analisa o tipo de retorno
        //assim podemos atribuir o tipo de retorno adequado
        when(adapter.getItemCount()).thenReturn(1);

        atualizador.salva(mari);

        verify(dao).salva(new Usuario("Mari"));
        verify(adapter).adiciona(new Usuario(1,"Mari"));
        verify(recyclerView).smoothScrollToPosition(0);

        //usar hard coded para evitar falsos positivos
    }
}