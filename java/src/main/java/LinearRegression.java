/**
 * Created by Cheong on 3/24/2017.
 */
public class LinearRegression {
    private double _slope;
    private double _intercept;
    private double _residual;

    private LinearRegression(double slope, double intercept) {
        _slope = slope;
        _intercept = intercept;
    }

    public static LinearRegression learn(double[] inputs, double[] outputs) {
        //setSlope(10);
        GraphInfo gInfo = LinearRegression.calcFittedRegressionLine(inputs, outputs);

        return new LinearRegression(gInfo.slope, gInfo.intercept);
    }

    public void setSlope(double slope) {
        this._slope = slope;
    }

    public void setIntercept(double intercept) {
        this._intercept = intercept;
    }

    public void setResidual(double residual) {
        this._residual = residual;
    }

    private static GraphInfo calcFittedRegressionLine(double[] inputs, double[] outputs) {
        double xBar = 0;
        double yBar = 0;
        double sumXX = 0;
        double sumXY = 0;
        int length = inputs.length;

        xBar = sum(inputs) / length;
        yBar = sum(outputs) / length;

        for (int i = 0; i < length; i++) {
            sumXX += (inputs[i] - xBar) * (inputs[i] - xBar);
            sumXY += (inputs[i] - xBar) * (outputs[i] - yBar);
        }

        GraphInfo gInfo = new GraphInfo();
        gInfo.slope = sumXY / sumXX;
        gInfo.intercept = yBar - gInfo.slope * xBar;

        System.out.println("slope = " + gInfo.slope);
        System.out.println("intercept = " + gInfo.intercept);

        return gInfo;
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
     *
     * @param input 입력값
     * @return y값
     */
    public double transform(double input) {
        return _slope * input + _intercept;
    }
}
