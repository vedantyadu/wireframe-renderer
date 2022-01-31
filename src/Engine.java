
public class Engine implements Runnable {

    boolean running = false;
    Window window;

    Engine(int[] dimensions) {
        window = new Window(dimensions);
        this.start();
    }

    public void start() {
        running = true;
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {

        long lastTime = System.nanoTime();
        final double ticks = 300;
        double ns = 1000000000 / ticks;
        double delta = 0;

        int frames = 0;
        int updates = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick(0.001f);
                updates++;
                delta --;
            }

            frames++;
            window.clear();
            window.update();

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Ticks: " + updates + " FPS: " + frames);
                updates = 0;
                frames = 0;
            }
        }
    }

    private void tick(float angle) {
        float[][][][] point_arr = window.points;

        for (int i = 0; i < point_arr.length; i++) {
            float[][][] point = point_arr[i];

            for (int j = 0; j < point.length; j++) {

                    point[j] = Projection.rotateX(angle, point[j]);
                    point[j] = Projection.rotateY(angle * 2, point[j]);
                    point[j] = Projection.rotateZ(angle * 3, point[j]);
            }

            window.points[i] = point;
        }
    }

    public static void main(String[] args) {
        Engine e = new Engine(new int[] {640, 480});
    }
}
