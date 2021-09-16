package br.com.alura.leilao.ui.recyclerview.adapter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Leilao;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoAdapterTest {

    @Mock
    private Context mockedContext;
    @Spy
    private ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(mockedContext);

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoReceberListaDeLeiloes(){
        doNothing().when(adapter).atualizaLista();
        adapter.atualiza(new ArrayList<Leilao>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro")
        )));
        int quantidadeLeiloesDevolvida = adapter.getItemCount();
        verify(adapter).atualizaLista();
        assertThat(quantidadeLeiloesDevolvida, is(equalTo(2)));
    }

}