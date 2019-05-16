// This class represents a polynomial. A polynomial is a sum of multiple monomials (class 'Monom'),
// such as 3*x^1 + -1*x^2 + 1*x^5.
// 'Polynom' uses a binary search tree to store its monomials. The degree of the monomial is the key.
// A specific degree exists at most once in the polynomial.
// TODO: define further classes for the implementation of the binary search tree, if needed

public class Polynom {

    private class TreeNode {
        public TreeNode l, r;
        public Monom m;

        public TreeNode (Monom m) {
            this.m = m;
        }

        public void add (Monom m) {
            if (!this.m.combine(m)) {
                if (this.m.lowerDegreeThan(m)) {
                    if (r == null) {
                        r = new TreeNode(m);
                    } else {
                        r.add(m);
                    }
                } else {
                    if (l == null) {
                        l = new TreeNode(m);
                    } else {
                        l.add(m);
                    }
                }
            }
        }

        public void applyTo(TreeNode tree) {
            tree.add(m);
            if (l != null) {
                l.applyTo(tree);
            }
            if (r != null) {
                r.applyTo(tree);
            }
        }

        public int eval(int x) {
            int sum = m.eval(x);
            if (l != null) {
                sum += l.eval(x);
            }
            if (r != null) {
                sum += r.eval(x);
            }
            return sum;
        }

        public String toString() {
            String sum = "";
            if (l != null) {
                sum += " + " + l;
            }
            if (m.a != 0) {
                sum += " + " + m;
            }
            if (r != null) {
                sum += r;
            }
            return sum;
        }
    }

    private TreeNode root = new TreeNode(new Monom(0, 0));

    // Initializes this polynomial with multiple monomials. The coefficients of the monomials are
    // specified by 'coeffs', where coeffs[i] is the coefficient of the monomial of degree i.
    // Entries with value 0 are ignored, i.e. corresponding monomials are not stored in the polynomial.
    public Polynom(int[] coeffs) {
        for (int i = 0; i < coeffs.length; i++) {
            add(coeffs[i], i);
        }
    }

    // Adds the monomial specified by 'coeff' and 'degree' to this polynomial, if coeff != 0,
    // otherwise 'add' has no effect.
    // If this polynomial already has a monomial of the same degree, no new monomial is added, instead
    // the coefficient of the monomial is modified accordingly ('combine' is called).
    public void add(int coeff, int degree) {
        if (coeff != 0) {
            root.add(new Monom(coeff, degree));
        }
    }

    // Adds all monomials of 'p' to this polynomial.
    // (The rules of 'add(int,int)' apply for each monomial to be added.)
    public void add(Polynom p) {
        p.root.applyTo(root);
    }

    // Returns the value of the polynomial for a specified value of 'x'
    public int eval(int x) {
        return root.eval(x);
    }

    // Returns a polynomial representation in mathematical notation such as
    // "2*x^0 + 6*x^2 + -2*x^3", where monomials are ordered ascending according to
    // their degree. Note that a positive sign of the leftmost coefficient is
    // not shown.
    // Returns the string "0" if the polynomial has no monomials (is empty).
    public String toString() {
        String s = root.toString();
        if (s.isEmpty()) {
            return "0";
        }
        return s.substring(3);
    }

}

