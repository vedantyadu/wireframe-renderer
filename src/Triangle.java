
public class Triangle {

    public static void drawTriangle(float[][] a, float[][] b, float[][] c, int[] color, Window window, boolean fill) {
        Line.draw(a, b, color, window);
        Line.draw(b, c, color, window);
        Line.draw(c, a, color, window);
    }

}