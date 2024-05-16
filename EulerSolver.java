package prj;

import java.util.Scanner;

//This code calculates solutions to simple first order differential equations through Euler's method, a numerical method of solving ODEs. This is not the most accurate, but it does generaly give a good approximation in most cases.
//Users can only input first order differential equations that are composed of polynomials. I have not implemented trig functions or other more complex functions into the calculator. 
//Both homogenous and non-homogenous ODEs can be inputted. 
//Input the ODE, the initial conditions, the desired point of solution x, and the step size. The smaller the step size, the more likely the answer is accurate. 

public class EulerSolver {
    public static double[] solve(Expression ode, double x0, double y0, double xEnd, double stepSize) {
        //stepsize = h 
	int steps = (int) ((xEnd - x0) / stepSize);
        double[] yVal = new double[steps + 1];
        double x = x0;
        double y = y0;
        yVal[0] = y;

        for (int i = 1; i <= steps; i++) {
        	// euler method  y(n+1) = y(n) + h*f(x,y) 
            y += stepSize * ode.evaluate(x, y);
            x += stepSize;
            yVal[i] = y;
        }

        return yVal;
    }

    public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		// potentialy possible functions examples: x+y, x^2+y^2, x*y + 2x, etc
	    
		System.out.print("Input ODE (simple polynomial function dy/dx = f(x, y)): " );
		String odeStr = scanner.nextLine();
		System.out.println();
        Expression ode = ExpressionParser.parse(odeStr);
        
        System.out.print("Input the initial x value (y(x0) =  y0): " );
		double x0 = scanner.nextDouble();
		System.out.println();
		
		System.out.print("Input the initial y value (y(x0) =  y0): " );
		double y0 = scanner.nextDouble();
		System.out.println();
		
		System.out.print("Input the x value for f(x): " );
		double xEnd = scanner.nextDouble();;
		System.out.println();
		
		System.out.print("Input the step size: " );
		double stepSize = scanner.nextDouble();
		System.out.println();


        double[] results = solve(ode, x0, y0, xEnd, stepSize);

        for (int i = 0; i < results.length; i++) {
            System.out.println("y(" + (x0 + i * stepSize) + ") = " + results[i]);
        }
    }
}
