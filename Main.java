import java.util.Scanner;

interface CalculatorOperation<T> {
    T calculate(T operand1, T operand2);
}

public class Calculator<T> {
    private final T operand1;
    private final T operand2;

    public Calculator(T operand1, T operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public T performOperation(CalculatorOperation<T> operation) {
        return operation.calculate(operand1, operand2);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        int num1 = in.nextInt();
        System.out.print("Введите второе число: ");
        int num2 = in.nextInt();
        System.out.print("Введите операцию (+, -, *, /): ");
        char operator = in.next().charAt(0);

        if (operator == '+' || operator == '-' || operator == '*') {
            Calculator<Integer> calculator = new Calculator<>(num1, num2);
            int result = 0;

            switch (operator) {
                case '+' -> result = calculator.performOperation((a, b) -> a + b);
                case '-' -> result = calculator.performOperation((a, b) -> a - b);
                case '*' -> result = calculator.performOperation((a, b) -> a * b);
            }

            System.out.println("Результат: " + result);
        } else if (operator == '/') {
            Calculator<Double> calculator = new Calculator<>((double) num1, (double) num2);
            double result = 0;

            result = calculator.performOperation((a, b) -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Делить на 0 нельзя");
                }
                return a / b;
            });

            System.out.println("Результат: " + result);
        } else {
            System.out.println("Недопустимая операция");
        }
    }
}
