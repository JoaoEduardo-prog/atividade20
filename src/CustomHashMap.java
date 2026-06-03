import java.util.LinkedList;

public class CustomHashMap<T> {
    private LinkedList<Dado<T>>[] tabela;
    private int tamanho;
    private int numElementos;
    private int comparacoes;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static class Dado<T> {
        long chave;
        T valor;

        Dado(long key, T value) {
            this.chave = key;
            this.valor = value;
        }
    }

    public CustomHashMap(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public CustomHashMap() {
        this.tamanho = 17;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int funcaoHash(long chave) {
        return Math.abs((int) (chave % tamanho));
    }

    public void put(long key, T value) {
        if ((double) numElementos / tamanho >= DEFAULT_LOAD_FACTOR) {
            resize();
        }

        Dado<T> dado = new Dado<>(key, value);
        int indice = funcaoHash(key);
        tabela[indice].addLast(dado);
        numElementos++;
    }

    public boolean containsKey(long key) {
        int indice = funcaoHash(key);
        for (Dado<T> item : tabela[indice]) {
            if (item.chave == key) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(long key) {
        int indice = funcaoHash(key);
        for (Dado<T> item : tabela[indice]) {
            if (item.chave == key) {
                tabela[indice].remove(item);
                numElementos--;
                return true;
            }
        }
        return false;
    }

    public T get(long key) {
        int indice = funcaoHash(key);
        for (Dado<T> item : tabela[indice]) {
            if (item.chave == key) {
                return item.valor;
            }
        }
        return null;
    }

    public void replace(long key, T value) {
        int indice = funcaoHash(key);
        for (Dado<T> item : tabela[indice]) {
            if (item.chave == key) {
                item.valor = value;
                return;
            }
        }
    }

    private void resize() {
        int novoTamanho = tamanho * 2;
        this.tamanho = novoTamanho;

        LinkedList<Dado<T>>[] novaTabela = new LinkedList[novoTamanho];
        for (int i = 0; i < novoTamanho; i++) {
            novaTabela[i] = new LinkedList<>();
        }

        for (LinkedList<Dado<T>> lista : tabela) {
            for (Dado<T> dado : lista) {
                int novoIndice = funcaoHash(dado.chave);
                novaTabela[novoIndice].add(dado);
            }
        }

        tabela = novaTabela;
        System.out.println("Novo tamanho da tabela: " + novoTamanho);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            sb.append("[").append(i).append("]");
            for (Dado<T> item : tabela[i]) {
                sb.append(" -> ").append(item.chave).append(":").append(item.valor);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}