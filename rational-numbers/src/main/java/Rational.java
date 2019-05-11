class Rational {

    private final int numerator;
    private final int denominator;

    Rational(int numerator, int denominator) {
        final var gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    public Rational add(final Rational other) {
        final var num = (this.numerator * other.denominator) + (other.numerator * this.denominator);
        final var denom = this.denominator * other.denominator;
        return new Rational(num, denom);
    }

    public Rational subtract(final Rational other) {
        final var num = (this.numerator * other.denominator) - (other.numerator * this.denominator);
        final var denom = this.denominator * other.denominator;
        return new Rational(num, denom);
    }

    public Rational multiply(final Rational other) {
        final var num = this.numerator * other.numerator;
        final var denom = this.denominator * other.denominator;
        return new Rational(num, denom);
    }

    public Rational divide(final Rational other) {
        return multiply(other.reciprocal());
    }

    public Rational abs() {
        return new Rational(Math.abs(numerator), Math.abs(denominator));
    }

    public Rational pow(final int exponent) {
        final var num = (int) Math.pow(this.numerator, exponent);
        final var denom = (int) Math.pow(this.denominator, exponent);
        return new Rational(num, denom);
    }

    public double exp(final double base) {
        // b^u/v = (u:th root of b)^v
        // also, u:th root of b = b^(1/u)
        final var uthRootOfB = Math.pow(base, 1.0 / denominator);
        return Math.pow(uthRootOfB, numerator);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private Rational reciprocal() {
        return new Rational(denominator, numerator);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        Rational other = (Rational) obj;
        return this.getNumerator() == other.getNumerator()
                && this.getDenominator() == other.getDenominator();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + this.getNumerator();
        result = prime * result + this.getDenominator();

        return result;
    }
}
