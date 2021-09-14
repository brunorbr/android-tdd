package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.leilao.exceptions.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exceptions.LancesConsecutivosDeMesmoUsuarioException;
import br.com.alura.leilao.exceptions.LimiteLancesUsuarioException;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        valida(lance);
        lances.add(lance);
        double valorLance = lance.getValor();
        defineValoresLimitePrimeiroLance(valorLance);
        Collections.sort(lances);
        calculaMaiorLance(valorLance);
    }

    private void defineValoresLimitePrimeiroLance(double valorLance) {
        if(lances.size() == 1){
            maiorLance = valorLance;
            menorLance = valorLance;
        }
    }

    private void valida(Lance lance) {
        if (lanceForMenorQueUltimoLance(lance))
            throw new LanceMenorQueUltimoLanceException("Lance foi menor que o maior lance");
        if(!lances.isEmpty()){
            Usuario usuarioNovo = lance.getUsuario();
            if (checkLanceConsecutivoMesmoUsuario(usuarioNovo))
                throw new LancesConsecutivosDeMesmoUsuarioException("Mesmo usuário do último lance");
            if (checkUsuarioLimiteLances(usuarioNovo))
                throw new LimiteLancesUsuarioException("Limite de 5 lances atingido");
        }
    }

    private boolean checkUsuarioLimiteLances(Usuario usuarioNovo) {
        int contadorLancesUsuario = 0;
        for (Lance l : lances) {
            Usuario existente = l.getUsuario();
            if(existente.equals(usuarioNovo)){
                contadorLancesUsuario++;
                if(contadorLancesUsuario == 5){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLanceConsecutivoMesmoUsuario(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();
        if(usuarioNovo.equals(ultimoUsuario)){
            return true;
        }
        return false;
    }

    private boolean lanceForMenorQueUltimoLance(Lance lance) {
        if(maiorLance > lance.getValor()){
            return true;
        }
        return false;
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> tresMaioresLances() {
        int maximoDeLances = lances.size();
        if (maximoDeLances > 3)
            maximoDeLances = 3;
        return lances.subList(0, maximoDeLances);
    }

    public int quantidadeLances() {
        return lances.size();
    }
}
