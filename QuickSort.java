import java.io.*;
import java.util.*;

public class QuickSortJava {


    public static List<Integer> lerArquivo(String caminho) throws IOException {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                numeros.add(Integer.valueOf(linha.trim()));
            }
        }
        return numeros;
    }


    public static void quickSort(List<Integer> numeros, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionar(numeros, inicio, fim);
            quickSort(numeros, inicio, indicePivo - 1);
            quickSort(numeros, indicePivo + 1, fim);
        }
    }

    public static int particionar(List<Integer> numeros, int inicio, int fim) {
        int pivo = numeros.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (numeros.get(j) <= pivo) {
                i++;
                Collections.swap(numeros, i, j);
            }
        }

        Collections.swap(numeros, i + 1, fim);
        return i + 1;
    }


    public static void escreverArquivo(List<Integer> numeros, String caminho) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (int numero : numeros) {
                bw.write(numero + "\n");
            }
        }
    }


    public static void infoSistema() {
        System.out.println("\n=== Informações do Sistema ===");
        System.out.println("Linguagem: Java");
        System.out.println("Versão: " + System.getProperty("java.version"));
        System.out.println("Sistema Operacional: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        System.out.println("Arquitetura: " + System.getProperty("os.arch"));
        System.out.println("==============================\n");
    }

    public static void main(String[] args) {
        try {

            String arquivoEntrada = "arq.txt"; // coloque o caminho completo se precisar
            String arquivoSaida   = "arq-saida-quick-java.txt";


            infoSistema();


            long inicioTempo = System.nanoTime();

            List<Integer> numeros = lerArquivo(arquivoEntrada);
            quickSort(numeros, 0, numeros.size() - 1);
            escreverArquivo(numeros, arquivoSaida);

            long fimTempo = System.nanoTime();
            double tempoExecucao = (fimTempo - inicioTempo) / 1_000_000.0;

            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); 
            long memoriaUsada = (runtime.totalMemory() - runtime.freeMemory()) / 1024;

            System.out.printf("Tempo de execução: %.2f ms\n", tempoExecucao);
            System.out.printf("Memória utilizada: %d KB\n", memoriaUsada);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
