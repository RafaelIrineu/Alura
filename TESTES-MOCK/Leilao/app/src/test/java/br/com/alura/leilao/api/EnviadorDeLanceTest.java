package br.com.alura.leilao.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {

    @Mock
    private LeilaoWebClient client;
    @Mock
    private EnviadorDeLance.LanceProcessadoListener listener;
    @Mock
    private AvisoDialogManager manager;
    @Mock
    private Leilao leilao;

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoLanceForMenorQueUltimoLance() {
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                manager
        );

        doThrow(LanceMenorQueUltimoLanceException.class)
                .when(leilao).propoe(any(Lance.class));

        enviador.envia(leilao, new Lance(new Usuario("Mari"), 50.0));

        verify(manager).mostraAvisoLanceMenorQueUltimoLance();
        verify(client, never()).propoe(any(Lance.class), anyLong(), any(RespostaListener.class));
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoUsuarioComCincoLancesEnviarLance() {
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                manager
        );
        doThrow(UsuarioJaDeuCincoLancesException.class)
                .when(leilao).propoe(any(Lance.class));

        enviador.envia(leilao, new Lance(new Usuario("Mari"), 200.0));

        verify(manager).mostraAvisoUsuarioJaDeuCincoLances();
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoOMesmoUsuarioDerDoisLancesSeguidos() {
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                manager
        );
        doThrow(LanceSeguidoDoMesmoUsuarioException.class)
                .when(leilao).propoe(any(Lance.class));

        enviador.envia(leilao, new Lance(new Usuario("Mari"), 200.0));

        verify(manager).mostraAvisoLanceSeguidoDoMesmoUsuario();
        verify(client, never()).propoe(any(Lance.class), anyLong(), any(RespostaListener.class));
    }

    @Test
    public void deve_NotificarLanceProcessado_QuandoEnviarLanceParaApiComSucesso() {
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                manager
        );

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                RespostaListener<Void> argument = invocation.getArgument(2);
                argument.sucesso(any(Void.class));
                return null;
            }
        }).when(client).propoe(any(Lance.class), anyLong(), any(RespostaListener.class));

        enviador.envia(leilao, new Lance(new Usuario("Mari"), 200.0));

        verify(listener).processado(any(Leilao.class));
        verify(manager, never()).mostraToastFalhaNoEnvio();
    }
}