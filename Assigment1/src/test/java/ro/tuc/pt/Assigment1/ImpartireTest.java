package ro.tuc.pt.Assigment1;

import static org.junit.Assert.*;

import org.junit.Test;

import ro.tuc.pt.Assigment1.Models.Monomial;
import ro.tuc.pt.Assigment1.Models.Polynomial;

public class ImpartireTest {

	@Test
	public void test() {
		Polynomial polinom1 = new Polynomial();
		Polynomial polinom2 = new Polynomial();
		Polynomial polinomCat = new Polynomial();
		Polynomial polinomRest = new Polynomial();
		Polynomial polinomRez = new Polynomial();
		
		polinom1.addMonomials(new Monomial(2,3));
		polinom1.addMonomials(new Monomial(1,2));
		polinom1.addMonomials(new Monomial(3,0));
		
		polinom2.addMonomials(new Monomial(2,2));
		polinom2.addMonomials(new Monomial(3,1));
		polinom2.addMonomials(new Monomial(2,0));
		
		polinomCat.addMonomials(new Monomial(1,1));
		polinomCat.addMonomials(new Monomial(-1,0));
		
		
		polinomRest.addMonomials(new Monomial(1,1));
		polinomRest.addMonomials(new Monomial(5,0));
		
		
		String polinom = polinom1.divide(polinom2);
		String polinom4 =  "Catul: " + polinomCat.printPolynomial() + "   |   Restul: " + polinomRest.printPolynomial();
		assertEquals(polinom,polinom4);
	}

}
