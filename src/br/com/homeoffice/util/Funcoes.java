package br.com.homeoffice.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Funcoes {
	
    public static String preencheAEsquerda(String valor, char caracter, int tamanho) {
        return String.format("%" + String.valueOf(caracter) + String.valueOf(tamanho) + "d", valor);
    }

    public static String formataDataEmString(Date data) {
        SimpleDateFormat out;
        try {
            out = new SimpleDateFormat("dd/MM/yyyy");
            return out.format(data);
        } catch (Exception e) {
            return "Erro ao formatar a Data";
        }
    }

    public static String formataDataHoraEmString(Date data) {
        SimpleDateFormat out;
        try {
            out = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return out.format(data);
        } catch (Exception e) {
            return "Erro ao formatar a data e hora";
        }
    }

    public static String formataHoraEmString(Date data) {
        SimpleDateFormat out;
        try {
            out = new SimpleDateFormat("HH:mm:ss");
            return out.format(data);
        } catch (Exception e) {
            return "Erro ao formatar a Hora";
        }
    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou
     * nula, retorna null - para facilitar em casos onde formul�rios podem ter
     * campos de datas vazios.
     *
     * @param data String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws Exception Caso a String esteja no formato errado
     */
    public static Date formataStringEmData(String data) {
        if (data == null || data.equals("")) {
            return null;
        }
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date) formatter.parse(data);
        } catch (Exception e) {
        }
        return date;
    }

    public static String concatenaAspas(String valor) {
        return "";
    }

    public static String retornaSistemaOperacional() {
        return System.getProperties().get("os.name").toString();
    }

    public static String removeCaracteresEspeciais(String texto) {
        /*
         * Remove alguns caracteres especiais da string
         */
        texto = texto.replaceAll("\\.?", "");
        texto = texto.replaceAll("-?", "");
        texto = texto.replaceAll("\\(?", "");
        texto = texto.replaceAll("\\)?", "");
        texto = texto.replaceAll("\\_?", "");
        texto = texto.replaceAll(" ", "");
        return texto;
    }

    public static boolean ehAlfa(String texto) {
        return texto.matches("[a-zA-Z]*");
    }

    public static Map<String, String> listaCamposComValores(Class<?> classe, Map<String, String> listaParametro) {
        Map<String, String> listaCampos = new HashMap<String, String>();
        try {
            Field[] campos = classe.getDeclaredFields();
            Method[] metodos = classe.getDeclaredMethods();
            for (Field f : campos) {
                f.getAnnotations();
                for (Method m : metodos) {
                    m.setAccessible(true);
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ocorrido: " + ex);
        }
        return listaCampos;
    }

    public static boolean validaCPF(String strCpf) {
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        //Primeiro resto da divis�o por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11 menos o resultado anterior.
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        //Segundo resto da divis�o por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11 menos o resultado anterior.
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        //Digito verificador do CPF que est� sendo validado.
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
    }

    public static Character retornaSexo(String sexo) {

        Character sexoValidado = null;

        if (sexo != null && !sexo.isEmpty()) {
            if (sexo.toLowerCase().equals("female")) {
                sexoValidado = 'F';
            } else if (sexo.toLowerCase().equals("male")) {
                sexoValidado = 'M';
            }
        }

        return sexoValidado;
    }

    public static String recuperaNomeUsuario(String email) {

        String nomeUsuario = null;

        if (email != null && !email.isEmpty()) {
            int posArroba = email.indexOf("@");
            nomeUsuario = email.substring(0, posArroba).toLowerCase();
        }

        return nomeUsuario;
    }

    public static String primeiroNome(String nome) {
        return nome.trim().split(" ")[0];
    }

 }  

