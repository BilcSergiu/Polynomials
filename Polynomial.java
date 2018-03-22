package ro.tuc.pt.Assigment1.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextField;

public class Polynomial {

	private List<Monomial> monomials;
	private List<Monomial> lastMonomial;

	public Polynomial() {
		this.monomials = new ArrayList();
		this.lastMonomial = new ArrayList();
	}

	public List<Monomial> getMonomials() {
		return this.monomials;
	}

	public Monomial getBiggestMonomial() {
		return this.monomials.get(0);
	}

	// Metoda de adaugare de monoame la lista de monoame
	public void addMonomials(Monomial monomToAdd) {
		int ok = 1;
		if (monomToAdd.getCoefficient() != 0) {
			for (Monomial monom : this.getMonomials()) {
				if (monom.getPower() == monomToAdd.getPower()) {
					monom.setCoeffcient(monom.getCoefficient() + monomToAdd.getCoefficient());
					ok = 0;
				}
			}
			if (ok == 1) {
				this.monomials.add(monomToAdd);
			}
			this.lastMonomial.add(monomToAdd);
		}
		Collections.sort(this.monomials);
	}

	// Metoda de eliminare a ultimului element adaugat la lista de monoame
	public void anulation() {
		Polynomial monomsToRemove = new Polynomial();
		for (Monomial monom : this.getMonomials()) {
			if (monom.getPower() == lastMonomial.get(lastMonomial.size() - 1).getPower()) {
				if (monom.getCoefficient() == lastMonomial.get(lastMonomial.size() - 1).getCoefficient()) {
					monomsToRemove.getMonomials().add(monom);
				} else {
					if (monom.getCoefficient() > lastMonomial.get(lastMonomial.size() - 1).getCoefficient()) {
						monom.setCoeffcient(
								monom.getCoefficient() - lastMonomial.get(lastMonomial.size() - 1).getCoefficient());
					}
				}
			}
		}
		if (lastMonomial.size() > 0) {
			lastMonomial.remove(lastMonomial.size() - 1);
			this.getMonomials().removeAll(monomsToRemove.getMonomials());
		}
	}

	// Metoda de inmultire a unui polinom cu un monom 
	public Polynomial mulWithMonom(Monomial monom2) {
		Polynomial pol = new Polynomial();
		Monomial mon;
		for (Monomial monom : this.getMonomials()) {
			mon = new Monomial(monom2.getCoefficient() * monom.getCoefficient(), monom2.getPower() + monom.getPower());
			pol.addMonomials(mon);
		}
		return pol;
	}

	// Metoda de efectuare a scaderii a doua polinoame
	public Polynomial substract(Polynomial pol) {
		List<Monomial> toAdd = new ArrayList();
		List<Monomial> toRemove = new ArrayList();
		Polynomial polRez = new Polynomial();
		float coeff;
		float pow;
		int ok;

		for (Monomial monom : this.getMonomials()) {
			polRez.addMonomials(new Monomial(monom.getCoefficient(), monom.getPower()));
		}

		for (Monomial monom2 : pol.getMonomials()) {
			ok = 1;
			for (Monomial monomRez : polRez.getMonomials()) {
				if (monomRez.getPower() == monom2.getPower()) {
					coeff = monomRez.getCoefficient();
					pow = monomRez.getPower();
					toRemove.add(monomRez);

					toAdd.add(new Monomial(coeff - monom2.getCoefficient(), pow));
					ok = 0;
				}
			}
			if (ok == 1) {
				polRez.addMonomials(new Monomial(monom2.getCoefficient() * (-1), monom2.getPower()));
			}
		}
		polRez.getMonomials().removeAll(toRemove);
		polRez.getMonomials().addAll(toAdd);

		return polRez;
	}

	//Metoda de inmultire a doua polinoame
	public Polynomial multiply(Polynomial polinom2) {
		Polynomial polinom3 = new Polynomial();
		float coeff, pow;
		for (Monomial monom1 : this.getMonomials()) {
			for (Monomial monom2 : polinom2.getMonomials()) {
				coeff = monom1.getCoefficient();
				coeff = coeff * monom2.getCoefficient();
				pow = monom1.getPower();
				pow = pow + monom2.getPower();
				polinom3.addMonomials(new Monomial(coeff, pow));
			}
		}
		return polinom3;
	}

	// Metoda de integrarea a unui polinom
	public Polynomial integrate() {
		Polynomial polinom3 = new Polynomial();
		for (Monomial monom : this.getMonomials()) {
			polinom3.addMonomials(new Monomial(monom.getCoefficient() / (monom.getPower() + 1), monom.getPower() + 1));
		}

		return polinom3;
	}

	// Metoda de derivare a unui polinom
	public Polynomial derivate() {
		Polynomial polinom3 = new Polynomial();
		for (Monomial monom : this.getMonomials()) {
			polinom3.addMonomials(new Monomial(monom.getCoefficient() * monom.getPower(), monom.getPower() - 1));
		}

		return polinom3;
	}

	// Metoda de adunare a unui polinom
	public Polynomial sum(Polynomial polinom2) {
		Polynomial polinom3 = new Polynomial();
		List<Monomial> toAdd = new ArrayList();

		for (Monomial monom : this.getMonomials()) {
			polinom3.addMonomials(new Monomial(monom.getCoefficient(), monom.getPower()));
		}

		int ok;
		for (Monomial monom2 : polinom2.getMonomials()) {
			ok = 1;
			for (Monomial monomRez : polinom3.getMonomials()) {
				if (monomRez.getPower() == monom2.getPower()) {
					monomRez.setCoeffcient(monomRez.getCoefficient() + monom2.getCoefficient());
					ok = 0;
				}
			}
			if (ok == 1) {
				toAdd.add(monom2);
			}
		}

		polinom3.getMonomials().addAll(toAdd);
		return polinom3;
	}

	
	// Metoda de impartirea a doua polinoame, returneaza stringul cu catul si restul
	public String divide(Polynomial polinom2) {
		Polynomial polAux = new Polynomial();
		Polynomial polCat = new Polynomial();
		Polynomial polAux2;
		Polynomial polRest = new Polynomial();
		Monomial monom, monom2;
		float coeff, pow;

		for (Monomial monom1 : this.getMonomials()) {
			polAux.addMonomials(monom1);
		}
		if (!polinom2.getMonomials().isEmpty()) {
			try {
				while (polAux.getBiggestMonomial().getPower() >= polinom2.getBiggestMonomial().getPower()) {
					monom = polinom2.getBiggestMonomial();
					coeff = polAux.getBiggestMonomial().getCoefficient() / monom.getCoefficient();
					pow = polAux.getBiggestMonomial().getPower() - monom.getPower();
					monom2 = new Monomial(coeff, pow);
					polCat.addMonomials(monom2);
					polAux2 = polinom2.mulWithMonom(monom2);
					polAux = polAux.substract(polAux2);
					Collections.sort(polAux.getMonomials());
				}
				polRest = polAux;
				return ("Catul: " + polCat.printPolynomial() + "   |   Restul: " + polRest.printPolynomial());
			} catch (IndexOutOfBoundsException e) {
				return ("Catul: " + polCat.printPolynomial() + "   |   Restul: " + 0);
			}
		}else {
			return ("Impartitorul trebuie sa fie diferit de 0!");
		}
	}

	// Metda de transpunere a unui polinom intr-un String. 
	// Metoda are atat de multe if-uri, deoarece am incercat am vrut sa elimin 
	// partile zecimale egale cu 0 are coeficientilro si exponentilor
	public String printPolynomial() {
		String polinom = "";
		int ok = 1;
		Collections.sort(this.getMonomials());

		for (Monomial monom : this.getMonomials()) {

			if ((monom.getPower()) != 0) {
				if ((monom.getCoefficient() > 0)) {
					if (monom.getCoefficient() % 1 == 0) {
						if (monom.getPower() % 1 == 0) {
							polinom = polinom + " +" + Integer.toString((int) monom.getCoefficient() / 1) + "*x^"
									+ Integer.toString((int) monom.getPower());
						} else {
							polinom = polinom + " +" + Integer.toString((int) monom.getCoefficient() / 1) + "*x^"
									+ Float.toString(monom.getPower());
						}
					} else {
						if (monom.getPower() % 1 == 0) {
							polinom = polinom + " +" + String.format("%.3g%n", monom.getCoefficient()) + "*x^"
									+ Integer.toString((int) monom.getPower());
						} else {
							polinom = polinom + " +" + String.format("%.3g%n", monom.getCoefficient() / 1) + "*x^"
									+ Float.toString(monom.getPower());
						}
					}
					ok = 0;
				} else {
					if ((monom.getCoefficient() < 0)) {
						if (monom.getCoefficient() % 1 == 0) {
							if (monom.getPower() % 1 == 0) {
								polinom = polinom + Integer.toString((int) monom.getCoefficient()) + "*x^"
										+ Integer.toString((int) monom.getPower());
							} else {
								polinom = polinom + Integer.toString((int) monom.getCoefficient()) + "*x^"
										+ Float.toString(monom.getPower());
							}
						} else {
							if (monom.getPower() % 1 == 0) {
								polinom = polinom + String.format("%.3g%n", monom.getCoefficient()) + "*x^"
										+ Integer.toString((int) monom.getPower());
							} else {
								polinom = polinom + String.format("%.3g%n", monom.getCoefficient()) + "*x^"
										+ Float.toString(monom.getPower());
							}
						}
						ok = 0;
					}
				}
			} else {
				if ((monom.getCoefficient() > 0)) {
					if (monom.getCoefficient() % 1 == 0) {

						polinom = polinom + " +" + Integer.toString((int) monom.getCoefficient());
					} else {
						polinom = polinom + " +" + Float.toString(monom.getCoefficient());
					}
					ok = 0;
				} else {
					if ((monom.getCoefficient() < 0)) {
						if (monom.getCoefficient() % 1 == 0) {
							polinom = polinom + Integer.toString((int) monom.getCoefficient());
						} else {
							polinom = polinom + Float.toString(monom.getCoefficient());
						}
						ok = 0;
					}
				}
			}
		}

		if (ok == 1 || this.getMonomials().isEmpty()) {
			polinom = "0";
		}

		return polinom;
	}

}
