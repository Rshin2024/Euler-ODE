package prj;
import java.util.Stack;

public class ExpressionParser {
    public static Expression parse(String expression) 
    {
        Stack<Expression> vals = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }


            
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                i--;
                vals.push(new NumberExpression(Double.parseDouble(sb.toString())));
            } 
                
            else if (c == 'x' || c == 'y') {
                vals.push(new VariableExpression(c));
            } 
            else if (c == '(') {
                operators.push(c);
            } 
            else if (c == ')') {
                while (operators.peek() != '(') {
                    vals.push(applyOpp(operators.pop(), vals.pop(), vals.pop()));
                }
                operators.pop();
            } 
            else if (isOp(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    vals.push(applyOpp(operators.pop(), vals.pop(), vals.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            vals.push(applyOpp(operators.pop(), vals.pop(), vals.pop()));
        }

        return vals.pop();
    }

    private static boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /
   private static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } 
        else if (operator == '*' || operator == '/') {
            return 2;
        } 
        else if (operator == '^') {
            return 3;
        } 
        else {
            return -1;
        }
    }

    private static Expression applyOpp(char operator, Expression right, Expression left) {
        return new BinaryOperation(left, right, operator);
    }
}
