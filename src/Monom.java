// This class represents monomials of the form a*x^d ("a mal x hoch d"), where
// 'a' is the coefficient of the monomial and 'd' is the degree.
public class Monom {

    public int a, d;

    // A constructor with the coefficient 'coeff' and the 'degree' of this monomial.
    public Monom(int coeff, int degree) {
        a = coeff;
        d = degree;
    }

    // Copy-constructor: uses 'coeff' and 'degree' of 'm' to initialize this monomial.
    public Monom(Monom m) {
        a = m.a;
        d = m.d;
    }

    // Adds the monomial 'm' to this monomial, if both monomials have the same degree.
    // In this case this monomial's coefficient is replaced by the sum of this monomial's
    // coefficient and the coefficient of 'm'. In this case the method returns 'true'.
    // If 'm' has not the same degree as 'this', the method has no effect and
    // returns 'false'.
    public boolean combine(Monom m) {
        if (m.d == d) {
            a += m.a;
            return true;
        }
        return false;
    }

    // Returns 'true' if 'm' has a higher degree than 'this', and 'false' otherwise.
    public boolean lowerDegreeThan(Monom m) {
        return m.d > d;
    }

    // Returns the value of the monomial for a specified value of 'x'
    public int eval(int x) {
        return (int) (a * Math.pow(x, d));
    }


    // Returns a representation in mathematical notation, e.g. of the form "5*x^2".
    public String toString() {
        return a + "*x^" + d;
    }


}




