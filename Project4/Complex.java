public class Complex {
    
    private double r;
    private double i;

    // Constructor
    public Complex(double r, double i){
        this.r = r;
        this.i = i;

    }

    // adding two complex numbers together
    public Complex add(Complex obj) {
        return new Complex(this.r + obj.r, this.i + obj.i);

    }

    // subtracting two complex numbers
    public Complex subtract(Complex obj) {
        return new Complex(this.r - obj.r - this.i * obj.i, this.r * obj.i + this.i * obj.r);
    
    }
    
    //mulitplying two complex numbers
    public Complex mult(Complex obj) {
        return new Complex(this.r * obj.r - this.i * obj.i, this.r * obj.i + this.i * obj.r);

    }

    // Dividing two complex numbers
    public Complex divide(Complex obj) {
        double denom = obj.r * obj.r + obj.i * obj.i;
        return new Complex((this.r * obj.r + this.i * obj.i) / denom, (this.i * obj.r - this.r * obj.i) / denom);

    }

    // Add real numbers 
    public Complex add(double d) {
        return new Complex(this.r + d, this.i);

    }

    //subtracting real numbers
    public Complex subtract(double d){
        return new Complex(this.r - d, this.i);

    }

    //multiplying real numbers
    public Complex mult(double d) {
        return new Complex(this.r * d, this.i * d);

    }

    //divide real numbers
    public Complex divide(double d) {
        return new Complex(this.r / d, this.i / d);

    }

    //String representation of a complex number
    @Override
    public String toString() {
        return "(" + r + " + " + i + "i)";

    }

    // main method for testing
    public static void main(String[] args){
        Complex c1 = new Complex(2, 3);
        Complex c2 = new Complex(1, -1);
        double x = 4.0;

        System.out.println(c1 + " + " + c2 + " = " + c1.add(c2));
        System.out.println(c1 + " - " + c2 + " = " + c1.subtract(c2));
        System.out.println(c1 + " * " + c2 + " = " + c1.mult(c2));
        System.out.println(c1 + " / " + c2 + " = " + c1.divide(c2));

        System.out.println(c1 + " + " + x + " = " + c1.add(x));
        System.out.println(c1 + " - " + x + " = " + c1.subtract(x));
        System.out.println(c1 + " * " + x + " = " + c1.mult(x));
        System.out.println(c1 + " / " + x + " = " + c1.divide(x));
    }
}
