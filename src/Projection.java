
public class Projection {

    public static float[][] rotateX(float angle, float[][] points) {
        float[][] rotate = {{1, 0, 0},
                            {0 , (float) Math.cos(angle), (float) -Math.sin(angle)},
                            {0 , (float) Math.sin(angle), (float) Math.cos(angle)}};

        return Maths.matrixMul(rotate, points);
    }


    public static float[][] rotateY(float angle, float[][] points) {
        float[][] rotate = {{(float) Math.cos(angle), 0, (float) Math.sin(angle)},
                            {0, 1, 0},
                            {(float) -Math.sin(angle), 0, (float) Math.cos(angle)}};

        return Maths.matrixMul(rotate, points);
    }


    public static float[][] rotateZ(float angle, float[][] points) {
        float[][] rotate = {{(float) Math.cos(angle), (float) -Math.sin(angle), 0},
                            {(float) Math.sin(angle), (float) Math.cos(angle), 0},
                            {0, 0, 1}};

        return Maths.matrixMul(rotate, points);
    }

    public static float[][] project(float[][] points, float distance) {

        float z = 100 / (distance - points[2][0] * 0.5f);

        float[][] project = {{z, 0, 0},
                             {0, z, 0}};

        return Maths.matrixMul(points, project);
    }
}
