import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class ComplexNumber {
    private final double real;
    private final double imag;

    public ComplexNumber(final double real) {
        this(real, 0);
    }

    public ComplexNumber(final double real, final double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public ComplexNumber times(final ComplexNumber other) {
        final var ac = this.real * other.real;
        final var bd = this.imag * other.imag;
        final var bc = this.imag * other.real;
        final var ad = this.real * other.imag;
        return new ComplexNumber(ac - bd, bc + ad);
    }

    public ComplexNumber add(final ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imag + other.imag);
    }

    public ComplexNumber minus(final ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imag - other.imag);
    }

    public ComplexNumber div(final ComplexNumber other) {
        final var ac = this.real * other.real;
        final var bd = this.imag * other.imag;
        final var bc = this.imag * other.real;
        final var ad = this.real * other.imag;
        final var denom = square(other.real) + square(other.imag);
        return new ComplexNumber((ac + bd) / denom, (bc - ad) / denom);
    }

    public double abs() {
        return sqrt(square(this.real) + square(this.imag));
    }

    public ComplexNumber conjugate() {
        return new ComplexNumber(this.real, this.imag * -1);
    }

    public ComplexNumber exponentialOf() {
        final var expa = exp(real);
        final var expb = new ComplexNumber(cos(imag), sin(imag));
        return new ComplexNumber(expa).times(expb);
    }

    private Double square(double number) {
        return number * number;
    }
}
