package br.com.alura.leilao.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorDeMoeda {

    public String formatar(double valor){
        NumberFormat formatador = DecimalFormat.getCurrencyInstance(new Locale("pt","br"));
        return formatador.format(valor);
    }
}
