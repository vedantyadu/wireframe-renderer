import java.awt.*;

public class Line {

    public static float[][][]swap(int index, float[][] start, float[][] end) {
        if (start[index][0] > end[index][0]) {
            return new float[][][] {end, start};
        }
        else {
            return new float[][][] {start, end};
        }
    }

    public static void draw(float[][] start, float[][] end, int[] color, Window window) {

        float[][] projected_start = Projection.project(start, 1);
        float[][] projected_end = Projection.project(end, 1);

        start = projected_start;
        end = projected_end;

        float dx = end[0][0] - start[0][0];
        float dy = end[1][0] - start[1][0];
        float m = dy / dx;
        float p;

        int clr = new Color(color[0], color[1], color[2]).getRGB();

        dx = Math.abs(dx);
        dy = Math.abs(dy);

        if (Math.abs(m) <= 1) {

            float[][][] swapped = swap(0, start, end);
            int y = Math.round(swapped[0][1][0]);

            p = 2 * dy - dx;

            for (int x = Math.round(swapped[0][0][0]); x <= Math.round(swapped[1][0][0]); x++) {

                window.putPixel(x, y, clr);

                p = p + 2 * dy;

                if (p > 0) {

                    p = p - 2 * dx;

                    if (m >= 0) {
                        y += 1;
                    }
                    if (m < 0) {
                        y -= 1;
                    }
                }
            }
        }

        else if (Math.abs(m) > 1) {

            float[][][] swapped = swap(1, start, end);
            int x = Math.round(swapped[0][0][0]);

            p = 2 * dx - dy;

            for (int y = Math.round(swapped[0][1][0]); y <= Math.round(swapped[1][1][0]); y++) {

                window.putPixel(x, y, clr);

                p = p + 2 * dx;

                if (p > 0) {

                    p = p - 2 * dy;

                    if (m >= 0) {
                        x += 1;
                    }
                    if (m < 0) {
                        x -= 1;
                    }
                }
            }
        }
    }
}
