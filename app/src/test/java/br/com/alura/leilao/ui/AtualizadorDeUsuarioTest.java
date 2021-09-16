package br.com.alura.leilao.ui;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;
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
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(dao,
                adapter,
                recyclerView);
        Usuario novoUsuario = new Usuario("Bruno");
        when(dao.salva(novoUsuario)).thenReturn(new Usuario(1, "Bruno"));
        when(adapter.getItemCount()).thenReturn(1);
        atualizador.salva(novoUsuario);

        verify(dao).salva(novoUsuario);
        verify(adapter).adiciona(new Usuario(1, "Bruno"));
        verify(recyclerView).smoothScrollToPosition(0);
    }

}