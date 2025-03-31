class Fila {
    private Node inicio;
    private Node fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }

    public void enfileirar(Pixel valor) {
        Node novoNo = new Node(valor);
        if (fim == null) {
            inicio = novoNo;
        } else {
            fim.proximo = novoNo;
        }
        fim = novoNo;
    }

    public Pixel desenfileirar() {
        if (inicio == null) {
            return null;
        }
        Pixel valorRemovido = inicio.valor;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        return valorRemovido;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
