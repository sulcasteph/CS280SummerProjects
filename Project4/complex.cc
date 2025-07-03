#include <iostream>

// defining the complex class
class Complex {
    public:
        // constructor with default values
        Complex(double r = 0, double i = 0);

        // operator overloads for complex numbers
        Complex operator+(const Complex&) const;
        Complex operator-(const Complex&) const;
        Complex operator*(const Complex&) const;
        Complex operator/(const Complex&) const;

        Complex operator+(double) const;
        Complex operator-(double) const;
        Complex operator*(double) const;
        Complex operator/(double) const;

        // friend functions for double operattions with Complex
        friend Complex operator+(double, const Complex&);
        friend Complex operator-(double, const Complex&);
        friend Complex operator*(double, const Complex&);
        friend Complex operator/(double, const Complex&);

        // output stream overload
        friend std::ostream& operator<<(std::ostream&, const Complex&);

    private:
        double r, i;

};

// constructor 
Complex::Complex(double r, double i) : r(r), i(i) {}

// Complex + Complex
Complex Complex::operator+(const Complex& obj) const {
    return Complex(r + obj.r, i + obj.i);
}

// Complex - Complex
Complex Complex::operator-(const Complex& obj) const {
    return Complex(r - obj.r, i - obj.i);
}

// Complex * Complex
Complex Complex::operator*(const Complex& obj) const {
    return Complex(r * obj.r - i * obj.i, r * obj.i + i * obj.r);
}

// Complex / Complex
Complex Complex::operator/(const Complex& obj) const {
    double denom = obj.r * obj.r + obj.i * obj.i;
    return Complex((r * obj.r + i * obj.i) / denom, (i * obj.r - r * obj.i) / denom);
}


// Complex + double
Complex Complex::operator+(double d) const {
    return Complex(r + d, i);
}

// Complex - double
Complex Complex::operator-(double d) const {
    return Complex(r - d, i);
}

// Complex * double
Complex Complex::operator*(double d) const {
    return Complex(r * d, i * d);
}

// Complex / double
Complex Complex::operator/(double d) const {
    return Complex(r / d, i / d);
}


// double + Complex
Complex operator+(double d, const Complex& obj) {
    return Complex(obj.r + d, obj.i);
}

// double - Complex
Complex operator-(double d, const Complex& obj) {
    return Complex(obj.r + d, obj.i);
}

// double * Complex
Complex operator*(double d, const Complex& obj){
    return Complex(obj.r * d, obj.i * d);
}

// double / Complex
Complex operator/(double d, const Complex& obj) {
    double denom = obj.r * obj.r + obj.i * obj.i;
    return Complex((d * obj.r) / denom, (-d * obj.i) / denom);
}

// output stream
std::ostream& operator<<(std::ostream& out, const Complex& obj) {
    out << "(" << obj.r;
    if (obj.i >= 0)
        out << " + " << obj.i << "i)";
    else
        out << " - " << -obj.i << "i)";

    return out;
}

// main function to test the Complex class
int main() {
    Complex c1(3, 4);
    Complex c2(1, 2);
    double x = 4.0;

    std::cout << c1 << " + " << c2 << " = " << c1 + c2 << std::endl;
    std::cout << c1 << " - " << c2 << " = " << c1 - c2 << std::endl;
    std::cout << c1 << " * " << c2 << " = " << c1 * c2 << std::endl;
    std::cout << c1 << " / " << c2 << " = " << c1 / c2 << std::endl;

    std::cout << c1 << " + " << x << " = " << c1 + x << std::endl;
    std::cout << c1 << " - " << x << " = " << c1 - x << std::endl;
    std::cout << c1 << " * " << x << " = " << c1 * x << std::endl;
    std::cout << c1 << " / " << x << " = " << c1 / x << std::endl;

    std::cout << x << " + " << c1 << " = " << x + c1 << std::endl;
    std::cout << x << " - " << c1 << " = " << x - c1 << std::endl;
    std::cout << x << " * " << c1 << " = " << x * c1 << std::endl;
    std::cout << x << " / " << c1 << " = " << x / c1 << std::endl;

    return 0;
}