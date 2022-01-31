
public class Maths {

    public static float[][] matrixMul(float[][] a, float[][] b) {

        float[][] answer = new float[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < b[0].length; j++) {

                for (int k = 0; k < a[0].length; k++) {
                    answer[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return answer;
    }
}
