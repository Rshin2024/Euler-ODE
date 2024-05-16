package prj;
abstract class Expression {
    public abstract double evaluate(double x, double y);
}

// just for numeric inputs

class NumberExpression extends Expression {
    private final double val;

    public NumberExpression(double val) {
        this.val = val;
    }

    @Override
    public double evaluate(double x, double y) {
        return val;
    }
    // returns val since it's not a variable
}

class VariableExpression extends Expression {
    private final char variable;

    public VariableExpression(char variable) {
        this.variable = variable;
    }

    @Override
    public double evaluate(double x, double y) {
        // ?: conditional operator, either x or y based on variable. Could use if else, but this is cooler
        return variable == 'x' ? x : y;
    }
}
//binary operation is + - / * with two elements, basically 
class BinaryOperation extends Expression {
    private final Expression left;
    private final Expression right;
    private final char operator;

    public BinaryOperation(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double evaluate(double x, double y) {
        if (operator == '+') {
            return left.evaluate(x, y) + right.evaluate(x, y);
        } 
        else if (operator == '-') {
            return left.evaluate(x, y) - right.evaluate(x, y);
        } 
        else if (operator == '*') {
            return left.evaluate(x, y) * right.evaluate(x, y);
        } 
        else if (operator == '/') {
            return left.evaluate(x, y) / right.evaluate(x, y);
        } 
        else if (operator == '^') {
            return Math.pow(left.evaluate(x, y), right.evaluate(x, y));
        } 
        else {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}


