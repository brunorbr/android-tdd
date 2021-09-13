package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    private final double DELTA = 0.0001;
    private final String ALBUM_TITLE = "Dawn of Chromatica";
    private final Usuario FIRST_USER = new Usuario("Lady Gaga");
    private final Usuario SECOND_USER = new Usuario("Ariana Grande");
    private final Usuario THIRD_USER = new Usuario("Pabllo Vittar");


    private double maiorLance;
    private double menorLance;
    private Leilao album;
    private List<Lance> tresMaioresLances;

    @Before
    public void setUp() {
        album = new Leilao("Dawn of Chromatica");
    }

    @Test
    public void testeGetDescricao() {
        String stringObtida = album.getDescricao();
        assertEquals(ALBUM_TITLE, stringObtida);
    }

    @Test
    public void testeGetMaiorLanceOrdemCrescente() {
        album.propoe(new Lance(FIRST_USER, 250.0));
        album.propoe(new Lance(SECOND_USER, 350.0));
        album.propoe(new Lance(THIRD_USER, 450.0));
        maiorLance = album.getMaiorLance();
        assertEquals(450.0, maiorLance, DELTA);
    }

    @Test
    public void testeGetMenorLanceOrdemCrescente() {
        album.propoe(new Lance(FIRST_USER, 250.0));
        album.propoe(new Lance(SECOND_USER, 350.0));
        album.propoe(new Lance(THIRD_USER, 450.0));
        menorLance = album.getMenorLance();
        assertEquals(250.0, menorLance, DELTA);
    }

    @Test
    public void testeGetTresMaioresLancesComTresLancesCrescente(){
        album.propoe(new Lance(FIRST_USER, 200.0));
        album.propoe(new Lance(SECOND_USER, 300.0));
        album.propoe(new Lance(THIRD_USER, 400));

        tresMaioresLances = album.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(400.00,
                tresMaioresLances.get(0).getValor(),
                DELTA);
        assertEquals(300.0,
                tresMaioresLances.get(1).getValor(),
                DELTA);
        assertEquals(300.0,
                tresMaioresLances.get(1).getValor(),
                DELTA);
    }

    @Test
    public void testeGetTresMaioresLancesListaSemLance(){
        tresMaioresLances = album.tresMaioresLances();
        assertEquals(0, tresMaioresLances.size());
    }

    @Test
    public void testeGetTresMaioresLancesListaUmLance(){
        album.propoe(new Lance(FIRST_USER, 200.0));
        tresMaioresLances = album.tresMaioresLances();
        assertEquals(1, tresMaioresLances.size());
        assertEquals(200.0,
                tresMaioresLances.get(0).getValor(),
                DELTA);
    }

    @Test
    public void testeGetTresMaioresLancesListaDoisLances(){
        album.propoe(new Lance(FIRST_USER, 200.0));
        album.propoe(new Lance(SECOND_USER, 700.0));
        tresMaioresLances = album.tresMaioresLances();
        assertEquals(2, tresMaioresLances.size());
        assertEquals(700.0,
                tresMaioresLances.get(0).getValor(),
                DELTA);
        assertEquals(200.0,
                tresMaioresLances.get(1).getValor(),
                DELTA);
    }

    @Test
    public void testeGetTresMaioresLancesListaMaisdeTresLances(){
        album.propoe(new Lance(FIRST_USER, 200.0));
        album.propoe(new Lance(SECOND_USER, 800.0));
        album.propoe(new Lance(THIRD_USER, 900.0));
        album.propoe(new Lance(FIRST_USER, 1200.0));
        album.propoe(new Lance(SECOND_USER, 2700.0));
        tresMaioresLances = album.tresMaioresLances();
        assertEquals(3, tresMaioresLances.size());
        assertEquals(2700.0,
                tresMaioresLances.get(0).getValor(),
                DELTA);
        assertEquals(1200.0,
                tresMaioresLances.get(1).getValor(),
                DELTA);
        assertEquals(900.0,
                tresMaioresLances.get(2).getValor(),
                DELTA);
    }

    @Test
    public void testeGetMaiorLanceZeroSemLances(){
        maiorLance = album.getMaiorLance();
        assertEquals(0.0, maiorLance, DELTA);
    }

    @Test
    public void testeGetMenorLanceZeroSemLances(){
        menorLance = album.getMenorLance();
        assertEquals(0.0, menorLance, DELTA);
    }

    @Test
    public void testeNaoAdicionarLanceMenorQueOMaiorLance(){
        album.propoe(new Lance(FIRST_USER, 500.0));
        album.propoe(new Lance(SECOND_USER, 300.0));

        int quantidadeLancesDevolvida = album.quantidadeLances();

        assertEquals(1, quantidadeLancesDevolvida);
    }
}