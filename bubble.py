import time
import psutil
import os
import platform

def ler_arquivo(arquivo):
    numeros = []
    with open(arquivo, 'r') as file:
        for linha in file:
            numeros.append(int(linha.strip()))
    return numeros

def bubble_sort(numeros):
    n = len(numeros)
    for i in range(n - 1):
        for j in range(n - i - 1):
            if numeros[j] > numeros[j + 1]:
                numeros[j], numeros[j + 1] = numeros[j + 1], numeros[j]

def escrever_arquivo(numeros, arquivo):
    with open(arquivo, 'w') as file:
        for numero in numeros:
            file.write(f"{numero}\n")

def memoria_utilizada():
    processo = psutil.Process(os.getpid())
    return processo.memory_info().rss

def info_sistema():
    print("\n=== Informações do Sistema ===")
    print(f"Linguagem: Python")
    print(f"Versão: {platform.python_version()}")
    print(f"Sistema Operacional: {platform.system()} {platform.release()}")
    print(f"Processador: {platform.processor()}")
    mem = psutil.virtual_memory()
    print(f"Memória RAM total: {mem.total / (1024**3):.2f} GB")
    print("==============================\n")

def main():

    arquivo_entrada = "/content/arq.txt"
    arquivo_saida   = "/content/arq-saida-python.txt"

    # Exibe informações do sistema
    info_sistema()

    inicio_tempo = time.time()

    numeros = ler_arquivo(arquivo_entrada)
    bubble_sort(numeros)
    escrever_arquivo(numeros, arquivo_saida)

    fim_tempo = time.time()

    tempo_execucao = (fim_tempo - inicio_tempo) * 1000
    memoria_rss_kb = memoria_utilizada() / 1024

    print(f"Tempo de execução: {tempo_execucao:.2f} ms")
    print(f"Memória RSS utilizada: {memoria_rss_kb:.2f} KB")

if __name__ == "__main__":
    main()
