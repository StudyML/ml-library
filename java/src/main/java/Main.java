/**
 * Created by Cheong on 3/24/2017.
 */
public class Main {
    /**
     * dummy main (추후 삭제 예정).
     *
     * @param args
     */
    public static void main(String[] args) {
        double[] inputs = {1,2,3,4};
        double[] outputs = {3,4,8,9};

        LinearRegression model = LinearRegression.learn(inputs, outputs);
        System.out.println(model.transform(5.5));
    }
}
