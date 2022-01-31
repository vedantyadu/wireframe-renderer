import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.stream.IntStream;

public class Window {

    Canvas canvas;
    BufferedImage image;
    JFrame frame;
    BufferStrategy bs;
    Graphics g;
    int[] pixelArray;
    int[] dimensions;

    float[][][][] points;

    int[] color;

    Window(int[] dimensions) {

        image = new BufferedImage(dimensions[0], dimensions[1], BufferedImage.TYPE_INT_RGB);

        Dimension size = new Dimension(dimensions[0], dimensions[1]);
        canvas = new Canvas();
        canvas.setPreferredSize(size);
        canvas.setMinimumSize(size);
        canvas.setMaximumSize(size);

        frame = new JFrame("Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();

        pixelArray = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < dimensions.length; i++) {
            if (dimensions[i] % 2 == 1) {
                dimensions[i] += 0;
            }
        }

        this.dimensions = new int[]{dimensions[0] / 2, dimensions[1] / 2};

        this.points = new float[][][][] {

                // South Face
                {{{-0.5f}, {-0.5f}, {0.5f}}, {{-0.5f}, {0.5f}, {0.5f}}, {{0.5f}, {0.5f}, {0.5f}}},
                {{{-0.5f}, {-0.5f}, {0.5f}}, {{0.5f}, {0.5f}, {0.5f}}, {{0.5f}, {-0.5f}, {0.5f}}},

                // North Face
                {{{-0.5f}, {-0.5f}, {-0.5f}}, {{-0.5f}, {0.5f}, {-0.5f}}, {{0.5f}, {0.5f}, {-0.5f}}},
                {{{-0.5f}, {-0.5f}, {-0.5f}}, {{0.5f}, {0.5f}, {-0.5f}}, {{0.5f}, {-0.5f}, {-0.5f}}},

                // East Face
                {{{0.5f}, {-0.5f}, {0.5f}}, {{0.5f}, {0.5f}, {0.5f}}, {{0.5f}, {0.5f}, {0.5f}}},
                {{{0.5f}, {-0.5f}, {0.5f}}, {{0.5f}, {0.5f}, {-0.5f}}, {{0.5f}, {-0.5f}, {-0.5f}}},

                // West Face
                {{{-0.5f}, {-0.5f}, {0.5f}}, {{-0.5f}, {0.5f}, {0.5f}}, {{-0.5f}, {0.5f}, {0.5f}}},
                {{{-0.5f}, {-0.5f}, {0.5f}}, {{-0.5f}, {0.5f}, {-0.5f}}, {{-0.5f}, {-0.5f}, {-0.5f}}},

                // Top Face
                {{{0.5f}, {0.5f}, {-0.5f}}, {{0.5f}, {0.5f}, {0.5f}}, {{-0.5f}, {0.5f}, {0.5f}}},
                {{{0.5f}, {0.5f}, {-0.5f}}, {{-0.5f}, {0.5f}, {0.5f}}, {{-0.5f}, {0.5f}, {-0.5f}}},

                // Bottom Face
                {{{0.5f}, {-0.5f}, {-0.5f}}, {{0.5f}, {-0.5f}, {0.5f}}, {{-0.5f}, {-0.5f}, {0.5f}}},
                {{{0.5f}, {-0.5f}, {-0.5f}}, {{-0.5f}, {-0.5f}, {0.5f}}, {{-0.5f}, {-0.5f}, {-0.5f}}}
        };

        this.color = new int[] {255, 255, 255};
    }

    public void clear() {
        IntStream.range(0, pixelArray.length).forEach(i -> pixelArray[i] = 0);
    }

    public void update() {

        for (int i = 0; i < points.length; i++) {
            Triangle.drawTriangle(points[i][0], points[i][1], points[i][2], color, this, true);
        }

        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bs.show();
    }

    private int correctX(int x) {
        return dimensions[0] + x;
    }

    private int correctY(int y) {
        return dimensions[1] - y;
    }

    public void putPixel(int x, int y, int color) {

        if (correctX(x) < image.getWidth() && correctX(x) >= 0) {
            if (correctY(y) < image.getHeight() && correctY(y) >= 0) {
                int index = correctX(x) + correctY(y) * this.image.getWidth();
                pixelArray[index] = color;
            }
        }
    }
}
