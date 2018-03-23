package ro.tuc.pt.Assigment1;

import static org.junit.Assert.*;

import org.junit.Test;

import ro.tuc.pt.Assigment1.Models.Monomial;
import ro.tuc.pt.Assigment1.Models.Polynomial;

public class IntegrareTest {

	@Test
	public void test() {
		Polynomial polinom1 = new Polynomial();
		Polynomial polinom2 = new Polynomial();
		Polynomial polinom3 = new Polynomial();
		Polynomial polinomRez = new Polynomial();
		
		polinom1.addMonomials(new Monomial(2,3));
		polinom1.addMonomials(new Monomial(1,2));
		polinom1.addMonomials(new Monomial(3,0));
		
		polinom2.addMonomials(new Monomial(2,2));
		polinom2.addMonomials(new Monomial(3,1));
		polinom2.addMonomials(new Monomial(2,0));
		
		polinom3.addMonomials(new Monomial((float)0.500,4));
		polinom3.addMonomials(new Monomial((float)0.333,3));
		polinom3.addMonomials(new Monomial(3,1));
		//polinom3.addMonomials(new Monomial(1,0));
		
		polinomRez = polinom1.integrate();
		assertEquals(polinom3.printPolynomial(),polinomRez.printPolynomial());
	}

}
