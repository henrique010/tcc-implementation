package modules.factorial;

public class LocalFactorialOperation {
    public LocalFactorialOperation(){

    }
    public double calculate(double number) {
        double factorial = number;

        while (!(number == 1 || number == 0)) {
            number = number - 1;
            factorial = factorial * number;
        }

        return factorial;
    }
}
