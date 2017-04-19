import com.sun.corba.se.impl.orbutil.graph.Graph;

/**
 * Created by Cheong on 3/24/2017.
 */
public class LinearRegression {
    public enum CalculationMethod { LEAST_SQURE_METHOD, FITTED_REGRESSION_METHOD };

    private double _slope;
    private double _intercept;

    private LinearRegression(double slope, double intercept) {
        _slope = slope;
        _intercept = intercept;
    }

    /**
     * 입출력값 정의하여 모델 생성
     * @param inputs 입력값 (x)
     * @param outputs 출력값 (y)
     * @return 학습한 LinearRegression Object
     */
    public static LinearRegression learn(double[] inputs, double[] outputs) {
        return learn(inputs, outputs, CalculationMethod.LEAST_SQURE_METHOD);
    }

    /**
     * 입출력값 및 계산 방법 정의하여 모델 생성
     * @param inputs 입력값 (x)
     * @param outputs 출력값 (y)
     * @param calculationMethod 학습에 사용할 연산 방식 선택
     * @return 학습한 LinearRegression Object
     */
    public static LinearRegression learn(double[] inputs, double[] outputs, CalculationMethod calculationMethod) {
        GraphInfo gInfo;

        switch (calculationMethod) {
            case FITTED_REGRESSION_METHOD:
                gInfo = LinearRegression.fittedRegressionLine(inputs, outputs);
                break;
            case LEAST_SQURE_METHOD:
            default:
                gInfo = LinearRegression.methodOfLeastSquare(inputs, outputs);
                break;
        }

        return new LinearRegression(gInfo.slope, gInfo.intercept);
    }

    /**
     * 회귀분석의 기본적인 모델(기댓값과 공분산을 이용한 방법)
     * @param inputs 입력값 (x)
     * @param outputs 출력값 (y)
     * @return 학습한 정보 (GraphInfo 구조체)
     */
    private static GraphInfo fittedRegressionLine(double[] inputs, double[] outputs) {
        double xBar;
        double yBar;
        double sumXX = 0;       //methodOfLeastSquare의 sumXX,sumXY와 다름
        double sumXY = 0;
        int n = inputs.length;

        xBar = sum(inputs) / n;            //x의 평균값 E(x)
        yBar = sum(outputs) / n;           //y의 평균값 E(y)

        for (int i = 0; i < n; i++) {
            sumXX += (inputs[i] - xBar) * (inputs[i] - xBar);
            sumXY += (inputs[i] - xBar) * (outputs[i] - yBar);
        }

        GraphInfo gInfo = new GraphInfo();
        gInfo.slope = sumXY / sumXX;
        gInfo.intercept = yBar - gInfo.slope * xBar;

        System.out.println("slope = " + gInfo.slope);
        System.out.println("intercept = " + gInfo.intercept);

        getCoefficientOfDetermination(inputs, outputs); //R-Squared
        return gInfo;
    }

    /**
     * 최소제곱법을 이용한 회귀모델
     * @param inputs 입력값 (x)
     * @param outputs 출력값 (y)
     * @return 학습한 정보 (GraphInfo 구조체)
     */
    private static GraphInfo methodOfLeastSquare(double[] inputs, double[] outputs) {
        double sumX;
        double sumY;
        double sumXX = 0;              //clacFittedRegressionLine의 sumXY, sumXX와 다름
        double sumXY = 0;
        int n = inputs.length;

        sumX = sum(inputs);
        sumY = sum(outputs);

        for (int i = 0; i < n; i++) {
            sumXX += inputs[i] * inputs[i];
        }
        for (int i = 0; i < n; i++) {
            sumXY += inputs[i] * outputs[i];
        }

        GraphInfo gInfo = new GraphInfo();
        gInfo.slope = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        gInfo.intercept = (sumY - gInfo.slope * sumX) / n;

        System.out.println("slope = " + gInfo.slope);
        System.out.println("intercept = " + gInfo.intercept);


        return gInfo;
    }

    /**
     * R-Squared(=결정계수) 계산    0 < R - Squared < 1 이며 1에 가까울수록 좋음
     * @param inputs 입력값 (x)
     * @param outputs 출력값 (y)
     * @return r^2 (R-Squared) 값
     */
    private static double getCoefficientOfDetermination(double[] inputs, double[] outputs) {
        double xBar;
        double yBar;
        double sumXY=0;        //methodOfLeastSquare, fittedRegressionLine의 sumXX,sumXY와 다름
        double sumXX=0;
        double sumYY=0;
        int n = inputs.length;
        double _rSquared;

        xBar = sum(inputs) / n;            //x의 평균값 E(x)
        yBar = sum(outputs) / n;           //y의 평균값 E(y)

        for (int i = 0; i < n; i++) {
            sumXY += (inputs[i] - xBar) * (outputs[i] - yBar);
            sumXX += (inputs[i] - xBar) * (inputs[i] - xBar);
            sumYY += (outputs[i] - yBar) * (outputs[i] - yBar);
        }

        sumXY /= n-1;
        sumXX /= n-1;
        sumYY /= n-1;

        _rSquared = (sumXY * sumXY) / (sumXX * sumYY);

        System.out.println("_rSquared = " + _rSquared);

        return _rSquared;
    }

    private static double sum(double[] inputs) {
        double sum = 0;

        for (double i : inputs) {
            sum += i;
        }

        return sum;
    }


    /**
     * input 값에 대한 x 값 계산
     * @param input 입력값
     * @return y값
     */
    public double transform(double input) {
        return _slope * input + _intercept;
    }
}
