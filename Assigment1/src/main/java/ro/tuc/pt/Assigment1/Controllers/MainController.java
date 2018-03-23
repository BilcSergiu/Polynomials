package ro.tuc.pt.Assigment1.Controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import ro.tuc.pt.Assigment1.Models.Monomial;
import ro.tuc.pt.Assigment1.Models.Polynomial;
import ro.tuc.pt.Assigment1.Views.PolynomialProcessingView;

public class MainController {

	private Polynomial polinom1;
	private Polynomial polinom2;
	private Polynomial polinom3;

	public MainController() {

		PolynomialProcessingView calculator = new PolynomialProcessingView();

		polinom1 = new Polynomial();
		polinom2 = new Polynomial();
		polinom3 = new Polynomial();

		// Actiunea efectuata la apasarea butonului Adaugare corespunzatoare polinomului 1
	
		calculator.actionBtnAdaugare1(l -> {
			try {
				if (!calculator.getTxtCoef1().getText().equals("") && calculator.getTxtPow1().getText().equals("")) {

					Monomial monom = new Monomial(Float.parseFloat(calculator.getTxtCoef1().getText()), 1);
					polinom1.addMonomials(monom);

				} else {
					if (Float.parseFloat(calculator.getTxtPow1().getText()) >= 0) {
						Monomial monom = new Monomial(Float.parseFloat(calculator.getTxtCoef1().getText()),
								Float.parseFloat(calculator.getTxtPow1().getText()));
						polinom1.addMonomials(monom);
					} else {
						throw new Exception("negativ");
					}
				}
				calculator.getTxtPoly1().setText(polinom1.printPolynomial());
				calculator.getTxtRes().setText("");
				calculator.revalidate();
				calculator.repaint();
				polinom3 = new Polynomial();
			} catch (Exception e) {
				if (e.getMessage().equals("negativ")) {
					JOptionPane.showMessageDialog(calculator, "Puterea trebuie sa fie pozitiva!");
				} else {
					JOptionPane.showMessageDialog(calculator, "Introduceti un numar!");
				}
			}
		});

		// Actiunea efectuata la apasarea butonului Adaugare corespunzatoare polinomului
		// 2
		calculator.actionBtnAdaugare2(l -> {
			try {
				if (!calculator.getTxtCoef2().getText().equals("") && calculator.getTxtPow2().getText().equals("")) {
					Monomial monom = new Monomial(Float.parseFloat(calculator.getTxtCoef2().getText()), 1);
					polinom2.addMonomials(monom);

				} else {
					if (Float.parseFloat(calculator.getTxtPow2().getText()) >= 0) {
						Monomial monom = new Monomial(Float.parseFloat(calculator.getTxtCoef2().getText()),
								Float.parseFloat(calculator.getTxtPow2().getText()));
						polinom2.addMonomials(monom);
					} else {
						throw new Exception("negativ");
					}
				}

				calculator.getTxtPoly2().setText(polinom2.printPolynomial());
				calculator.getTxtRes().setText("");
				calculator.revalidate();
				calculator.repaint();
				polinom3 = new Polynomial();

			} catch (Exception e) {
				if (e.getMessage().equals("negativ")) {
					JOptionPane.showMessageDialog(calculator, "Puterea trebuie sa fie pozitiva!");
				} else {
					JOptionPane.showMessageDialog(calculator, "Introduceti un numar!");
				}
			}
		});

		// Adaugarea monomului corespunzator polinomului 1 la apasarea tastei ENTER
		calculator.actionEnter1(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if (calculator.getTxtCoef1().getText().equals("")
								&& !calculator.getTxtPow1().getText().equals("")) {
							Monomial monom = new Monomial(1, Float.parseFloat(calculator.getTxtPow1().getText()));
							polinom1.addMonomials(monom);
						} else {
							Monomial monom = new Monomial(Float.parseFloat(calculator.getTxtCoef1().getText()),
									Float.parseFloat(calculator.getTxtPow1().getText()));
							polinom1.addMonomials(monom);
						}

						calculator.getTxtPoly1().setText(polinom1.printPolynomial());
						calculator.getTxtRes().setText("");
						calculator.revalidate();
						calculator.repaint();
						polinom3 = new Polynomial();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(calculator, "Introduceti un numar!");
					}
				}
			}
		});

		// Adaugarea monomului corespunzator polinomului 1 la apasarea tastei ENTER
		calculator.actionEnter2(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if (calculator.getTxtCoef2().getText().equals("")
								&& !calculator.getTxtPow2().getText().equals("")) {
							Monomial monom = new Monomial(1, Float.parseFloat(calculator.getTxtPow2().getText()));
							polinom2.addMonomials(monom);
						} else {
							Monomial monom = new Monomial(Float.parseFloat(calculator.getTxtCoef2().getText()),
									Float.parseFloat(calculator.getTxtPow2().getText()));
							polinom2.addMonomials(monom);
						}
						calculator.getTxtPoly2().setText(polinom2.printPolynomial());
						calculator.getTxtRes().setText("");
						calculator.revalidate();
						calculator.repaint();
						polinom3 = new Polynomial();

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(calculator, "Introduceti un numar!");
					}
				}

			}
		});

		// Anularea ultimului monom adaugat la polinomul 1
		calculator.actionBtnAnulare1(l -> {
			polinom1.anulation();
			calculator.getTxtPoly1().setText(polinom1.printPolynomial());

			calculator.getTxtRes().setText("");
			calculator.revalidate();
			calculator.repaint();
			polinom3 = new Polynomial();
		});

		// Anularea ultimului monom adaugat la polinomul 2
		calculator.actionBtnAnulare2(l -> {

			polinom2.anulation();
			calculator.getTxtPoly2().setText(polinom2.printPolynomial());
			calculator.getTxtRes().setText("");
			calculator.revalidate();
			calculator.repaint();
			polinom3 = new Polynomial();
		});

		// Actiunea efectuata la apasarea butonului ADUNARE
		calculator.actionBtnAdunare(l -> {

			polinom3 = polinom1.sum(polinom2);
			calculator.getTxtRes().setText("Adunare: " + polinom3.printPolynomial());
			calculator.revalidate();
			calculator.repaint();
			polinom3 = new Polynomial();

		});

		// Actiunea efectuata la apasarea butonului SCADERE
		calculator.actionBtnScadere(l -> {

			Object[] options = { "Primul polinom - Al doilea polinom", "Al doilea polinom - Primul polinom" };
			Object l1 = JOptionPane.showInputDialog(null, "Alegeti ordinea operatiei!", "Scadere",
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (l1 != null) {
				if (l1.equals("Primul polinom - Al doilea polinom")) {
					polinom3 = polinom1.substract(polinom2);
					calculator.getTxtRes().setText("Scadere: " + polinom3.printPolynomial());
					calculator.revalidate();
					calculator.repaint();
					polinom3 = new Polynomial();
				} else {
					polinom3 = polinom2.substract(polinom1);
					calculator.getTxtRes().setText("Scadere: " + polinom3.printPolynomial());
					calculator.revalidate();
					calculator.repaint();
					polinom3 = new Polynomial();
				}
			}
		});

		// Actiunea efectuata la apasarea butonului DERIVARE
		calculator.actionBtnDerivare(l -> {
			Object[] options = { "Primul polinom", "Al doilea polinom" };
			Object l1 = JOptionPane.showInputDialog(null, "Alegeti polinomul de derivat!", "Derivare",
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (l1 != null) {
				if (l1.equals("Primul polinom")) {

					polinom3 = polinom1.derivate();
					calculator.getTxtRes().setText("Derivare: " + polinom3.printPolynomial());
					calculator.revalidate();
					calculator.repaint();
					polinom3 = new Polynomial();

				} else {
					polinom3 = polinom2.derivate();
					calculator.getTxtRes().setText("Derivare: " + polinom3.printPolynomial());
					calculator.revalidate();
					calculator.repaint();
					polinom3 = new Polynomial();
				}
			}
		});

		// Actiunea efectuata la apasarea butonului Integrare
		calculator.actionBtnIntegrare(l -> {
			Object[] options = { "Primul polinom", "Al doilea polinom" };
			Object l1 = JOptionPane.showInputDialog(null, "Alegeti polinomul de integrat!", "Integrare",
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (l1 != null) {
				if (l1.equals("Primul polinom")) {

					polinom3 = polinom1.integrate();
					calculator.getTxtRes().setText("Integrare: " + polinom3.printPolynomial());
					calculator.revalidate();
					calculator.repaint();
					polinom3 = new Polynomial();

				} else {

					polinom3 = polinom2.integrate();
					calculator.getTxtRes().setText("Integrare: " + polinom3.printPolynomial());
					calculator.revalidate();
					calculator.repaint();
					polinom3 = new Polynomial();
				}
			}
		});

		// Actiunea efectuata la apasarea butonului INMULTIRE
		calculator.actionBtnImultire(l -> {
			polinom3 = polinom1.multiply(polinom2);
			calculator.getTxtRes().setText("Inmultire: " + polinom3.printPolynomial());
			calculator.revalidate();
			calculator.repaint();
			polinom3 = new Polynomial();
		});

		// Actiunea efectuata la apasarea butonului IMPARTIRE
		calculator.actionBtnImpartire(l -> {
			Object[] options = { "Primul polinom / Al doilea polinom", "Al doilea polinom / Primul polinom" };
			Object l1 = JOptionPane.showInputDialog(null, "Alegeti polinomul de integrat!", "Integrare",
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (l1 != null) {
				if (l1.equals("Primul polinom / Al doilea polinom")) {
					calculator.getTxtRes().setText(polinom1.divide(polinom2));
				} else {
					calculator.getTxtRes().setText(polinom2.divide(polinom1));
				}
			}
			calculator.revalidate();
			calculator.repaint();
		});

	}

}
