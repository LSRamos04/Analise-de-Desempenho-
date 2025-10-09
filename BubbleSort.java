import java.io.*;
import java.util.*;

public class BubbleSortJava {

    // Função para ler o arquivo e retornar os números
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

    // Função para ordenar usando Bubble Sort
    public static void bubbleSort(List<Integer> numeros) {
        int n = numeros.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numeros.get(j) > numeros.get(j + 1)) {
                    int temp = numeros.get(j);
                    numeros.set(j, numeros.get(j + 1));
                    numeros.set(j + 1, temp);
                }
            }
        }
    }

    // Função para escrever o arquivo com os números ordenados
    public static void escreverArquivo(List<Integer> numeros, String caminho) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (int numero : numeros) {
                bw.write(numero + "\n");
            }
        }
    }

    // Função para mostrar informações do sistema
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
            // Ajuste os caminhos
            String arquivoEntrada = "arq.txt";
            String arquivoSaida   = "arq-saida-java.txt";

            // Exibe informações do sistema
            infoSistema();

            // Marca tempo inicial
            long inicioTempo = System.nanoTime();

            // Executa leitura, ordenação e escrita
            List<Integer> numeros = lerArquivo(arquivoEntrada);
            bubbleSort(numeros);
            escreverArquivo(numeros, arquivoSaida);

            // Marca tempo final
            long fimTempo = System.nanoTime();

            // Tempo de execução em ms
            double tempoExecucao = (fimTempo - inicioTempo) / 1_000_000.0;

            // Medição de memória (em KB)
            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // pede ao Garbage Collector para limpar
            long memoriaUsada = (runtime.totalMemory() - runtime.freeMemory()) / 1024;

            System.out.printf("Tempo de execução: %.2f ms\n", tempoExecucao);
            System.out.printf("Memória utilizada: %d KB\n", memoriaUsada);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
