package br.com.alura.leilao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(appContext);
        adapter.atualiza(new ArrayList<Leilao>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro")
        )));

        int quantidadeLeiloesDevolvida = adapter.getItemCount();

        assertThat(quantidadeLeiloesDevolvida, is(equalTo(2)));
    }
}
