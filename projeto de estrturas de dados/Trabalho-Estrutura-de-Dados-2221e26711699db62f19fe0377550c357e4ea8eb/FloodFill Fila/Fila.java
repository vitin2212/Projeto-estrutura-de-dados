class Fila {
    private Node frente;
    private Node tras;

    public Fila() {
        this.frente = this.tras = null;
    }

    public void insere(Pixel valor) { // Enqueue
        Node novoNo = new Node(valor);
        if (estaVazia()) {
            frente = tras = novoNo;
        } else {
            tras.proximo = novoNo;
            tras = novoNo;
        }
    }

    public Pixel remover() { // Dequeue
        if (estaVazia()) {
            return null;
        }
        Pixel valorRemovido = frente.valor;
        frente = frente.proximo;
        if (frente == null) { // Se a fila ficou vazia, atualizar tras também
            tras = null;
        }
        return valorRemovido;
    }

    public boolean estaVazia() {
        return frente == null;
    }
}
