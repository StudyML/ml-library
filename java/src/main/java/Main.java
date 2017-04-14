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
        double[] outputs = {2,4,6,8};

        LinearRegression model = LinearRegression.learn(inputs, outputs);
        
        System.out.println("Hello world");
    }
}
