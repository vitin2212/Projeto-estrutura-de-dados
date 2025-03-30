package Trabalho

import java.awt.image.BufferedImage;

public class FloodFillFila {
    public static void floodFill(BufferedImage image, int x, int y, int novaCor) {
        int corOriginal = image.getRGB(x, y);
        if (corOriginal == novaCor) return; // Evita preenchimento desnecessário

        int largura = image.getWidth();
        int altura = image.getHeight();

        Fila fila = new Fila();
        fila.insere(new Pixel(x, y));

        while (!fila.estaVazia()) {
            Pixel p = fila.remover();
            if (p == null) continue;
            int px = p.x, py = p.y;

            if (px < 0 || py < 0 || px >= largura || py >= altura) continue;
            if (image.getRGB(px, py) != corOriginal) continue;

            image.setRGB(px, py, novaCor);

            fila.insere(new Pixel(px + 1, py));  // Direita
            fila.insere(new Pixel(px - 1, py));  // Esquerda
            fila.insere(new Pixel(px, py + 1));  // Abaixo
            fila.insere(new Pixel(px, py - 1));  // Acima

            // Debug para verificar o comportamento
            System.out.println("Processando pixel: (" + px + ", " + py + ")");
        }
    }
}
