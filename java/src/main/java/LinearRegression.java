/**
 * Created by Cheong on 3/24/2017.
 */
public class LinearRegression {
    private double slope;
    private double intercept;
    private double residual;

    private LinearRegression() {

    }

    public static LinearRegression learn(double[] inputs, double[] outputs) {
        setSlope(10);
        return new LinearRegression();
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    public void setResidual(double residual) {
        this.residual = residual;
    }

    private void calcFittedRegressionLine(double[] inputs, double[] outputs) {
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

        slope = sumXY / sumXX;
        intercept = yBar - slope * xBar;

        System.out.println("slope = " + slope);
        System.out.println("intercept = " + intercept);

    }


    private double sum(double[] inputs) {
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
        return slope * input + intercept;
    }
}
