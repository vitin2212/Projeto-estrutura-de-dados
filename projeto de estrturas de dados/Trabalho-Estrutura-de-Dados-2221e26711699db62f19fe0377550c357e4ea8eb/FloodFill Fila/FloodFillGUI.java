import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFillGUI extends JFrame {
    private BufferedImage image;
    private JLabel imageLabel;

    public FloodFillGUI(String imagePath) {
        setTitle("Flood Fill - Automático");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Carregar imagem usando a classe ImageLoader
        image = ImageLoader.loadImage(imagePath);
        if (image == null) {
            System.out.println("Erro ao carregar a imagem.");
            System.exit(1);
        }

        // Criar JLabel com a imagem
        imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel, BorderLayout.CENTER);

        setSize(image.getWidth(), image.getHeight());
        setLocationRelativeTo(null);
        setVisible(true);

        // Inicia o Flood Fill automaticamente
        iniciarFloodFill(Color.BLUE.getRGB());

        // Salvar a imagem
        ImageSaver.saveImage(image, "output.png");
    }

    private void iniciarFloodFill(int novaCor) {
        // Cria um novo thread para o Flood Fill, para não bloquear a interface
        new Thread(() -> {
            final int delay = 5; // Delay aumentado para visualização mais fluida

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (ehPreto(image.getRGB(x, y))) {
                        // Preenche o pixel atual usando Fila (BFS)
                        FloodFillFila.floodFill(image, x, y, novaCor);

                        // Atualiza a interface gráfica para mostrar a animação de pintura
                        SwingUtilities.invokeLater(() -> imageLabel.repaint());

                        // Adiciona um delay para visualização gradual
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();  // Inicia o thread para evitar travamentos na interface gráfica
    }

    private boolean ehPreto(int cor) {
        int r = (cor >> 16) & 0xFF;
        int g = (cor >> 8) & 0xFF;
        int b = cor & 0xFF;
        return r < 100 && g < 100 && b < 100;  // Detecta tons escuros
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FloodFillGUI("images/bolaVolei.jpg"));
    }
}
