package ro.tuc.pt.Assigment1.Views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ro.tuc.pt.Assigment1.Models.Monomial;
import ro.tuc.pt.Assigment1.Models.Polynomial;

public class PolynomialProcessingView extends JFrame implements MouseListener {

	JLabel lblFirstPolynomial, lblSecondPolynomial, lblPol1, lblPol2;
	JLabel lblVariable, lblVariable1, lblPlus, lblPlus1, lblResult;
	JTextField txtCoef1, txtCoef2, txtPow1, txtPow2, txtConst1, txtConst2;
	JTextField txtPoly1, txtPoly2, txtRes;
	JButton btnAdd, btnSub, btnMul, btnDiv, btnInt, btnDer, btnAddMonom1, btnAddMonom2, btnAnulare1, btnAnulare2;
	Container cPane;
	JPanel firstPolyPanel, secondPolyPanel, buttonsPanel, poly1Display, poly2Display, polyResDisplay;

	public PolynomialProcessingView() {
		createGUI();
	}

	public void createGUI() {

		/* Get the frame's contentpane and set layout */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Procesarea polinoamelor");
		this.setLocation(500, 150);

		cPane = this.getContentPane();
		cPane.setLayout(new BoxLayout(this.cPane, BoxLayout.Y_AXIS));

		// Arrange components on contentPane and set Action Listeners to each JButton
		arrangeComponents();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void arrangeComponents() {

		/// -------- Initializare componente ------------ ///

		this.lblFirstPolynomial = new JLabel("Primul polinom:");
		this.lblSecondPolynomial = new JLabel("Al doilea polinom:");
		this.lblPol1 = new JLabel("Primul polinom:");
		this.lblPol2 = new JLabel("Al doilea polinom:");
		this.lblPlus = new JLabel("+");
		this.lblVariable = new JLabel("*x^");
		this.lblPlus1 = new JLabel("+");
		this.lblVariable1 = new JLabel("*x^");
		this.lblResult = new JLabel("Rezultat:");

		this.txtCoef1 = new JTextField(2);
		this.txtCoef2 = new JTextField(2);
		this.txtPow1 = new JTextField(2);
		this.txtPow2 = new JTextField(2);
		this.txtConst1 = new JTextField(2);
		this.txtConst2 = new JTextField(2);
		this.txtPoly1 = new JTextField(20);
		this.txtPoly2 = new JTextField(20);
		this.txtRes = new JTextField(20);

		this.txtRes.addMouseListener(this);

		this.txtPoly1.setEditable(false);
		this.txtPoly2.setEditable(false);
		this.txtPoly2.setText("0");
		this.txtPoly1.setText("0");

		this.btnAdd = new JButton("Adunare");
		this.btnSub = new JButton("Diferenta");
		this.btnDer = new JButton("Derivare");
		this.btnDiv = new JButton("Impartire");
		this.btnInt = new JButton("Integrare");
		this.btnMul = new JButton("Inmultire");
		this.btnAddMonom1 = new JButton("Adaugare");
		this.btnAddMonom2 = new JButton("Adaugare");
		this.btnAnulare1 = new JButton("Anulare");
		this.btnAnulare2 = new JButton("Anulare");

	/// -----------------------------------Layout managers-----------------------------------------------///

		// Layoutul pentru primul polinom
		this.firstPolyPanel = new JPanel();
		this.firstPolyPanel.setOpaque(false);
		firstPolyPanel.setLayout(new BoxLayout(this.firstPolyPanel, BoxLayout.X_AXIS));
		this.firstPolyPanel.add(this.lblFirstPolynomial);
		firstPolyPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		this.firstPolyPanel.add(this.txtCoef1);
		this.firstPolyPanel.add(this.lblVariable);
		this.firstPolyPanel.add(this.txtPow1);
		firstPolyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		this.firstPolyPanel.add(this.btnAddMonom1);
		firstPolyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		this.firstPolyPanel.add(this.btnAnulare1);
		this.firstPolyPanel.setBorder(BorderFactory.createEmptyBorder(12, 10, 0, 10));

		this.poly1Display = new JPanel();
		this.poly1Display.setLayout(new BoxLayout(this.poly1Display, BoxLayout.X_AXIS));
		this.poly1Display.add(this.lblPol1);
		poly1Display.add(Box.createRigidArea(new Dimension(29, 0)));
		this.poly1Display.add(this.txtPoly1);
		this.poly1Display.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		/// ----------------------------------------------------------------------------------///

		// Layout pentru al doilea polinom
		this.secondPolyPanel = new JPanel();
		secondPolyPanel.setLayout(new BoxLayout(this.secondPolyPanel, BoxLayout.X_AXIS));
		this.secondPolyPanel.add(this.lblSecondPolynomial);
		secondPolyPanel.add(Box.createRigidArea(new Dimension(18, 0)));
		this.secondPolyPanel.add(this.txtCoef2);
		this.secondPolyPanel.add(this.lblVariable1);
		this.secondPolyPanel.add(this.txtPow2);
		secondPolyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		this.secondPolyPanel.add(this.btnAddMonom2);
		secondPolyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		this.secondPolyPanel.add(this.btnAnulare2);
		this.secondPolyPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		this.poly2Display = new JPanel();
		this.poly2Display.setLayout(new BoxLayout(this.poly2Display, BoxLayout.X_AXIS));
		this.poly2Display.add(this.lblPol2);
		poly2Display.add(Box.createRigidArea(new Dimension(18, 0)));
		this.poly2Display.add(this.txtPoly2);
		this.poly2Display.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		/// ----------------------------------------------------------------------------------///

		// Layout pentru rezultat

		this.polyResDisplay = new JPanel();
		this.polyResDisplay.setLayout(new BoxLayout(this.polyResDisplay, BoxLayout.X_AXIS));
		this.polyResDisplay.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		this.polyResDisplay.add(this.lblResult);
		polyResDisplay.add(Box.createRigidArea(new Dimension(68, 0)));
		this.polyResDisplay.add(this.txtRes);

		/// ----------------------------------------------------------------------------------///

		// Layout pentru butoane
		this.buttonsPanel = new JPanel();
		this.buttonsPanel.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
		gridBagConstraintsx03.gridx = 0;
		gridBagConstraintsx03.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx03.gridy = 0;
		gridBagConstraintsx03.fill = GridBagConstraints.BOTH;
		this.buttonsPanel.add(this.btnAdd, gridBagConstraintsx03);

		GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
		gridBagConstraintsx04.gridx = 1;
		gridBagConstraintsx04.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx04.gridy = 0;
		gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
		buttonsPanel.add(this.btnSub, gridBagConstraintsx04);

		GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
		gridBagConstraintsx05.gridx = 0;
		gridBagConstraintsx05.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx05.gridy = 1;
		gridBagConstraintsx05.fill = GridBagConstraints.BOTH;
		buttonsPanel.add(this.btnMul, gridBagConstraintsx05);

		GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
		gridBagConstraintsx06.gridx = 1;
		gridBagConstraintsx06.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx06.gridy = 1;
		gridBagConstraintsx06.fill = GridBagConstraints.BOTH;
		buttonsPanel.add(this.btnDiv, gridBagConstraintsx06);

		GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
		gridBagConstraintsx07.gridx = 0;
		gridBagConstraintsx07.insets = new Insets(5, 5, 0, 5);
		gridBagConstraintsx07.gridy = 2;
		gridBagConstraintsx07.fill = GridBagConstraints.BOTH;
		buttonsPanel.add(this.btnDer, gridBagConstraintsx07);

		GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
		gridBagConstraintsx08.gridx = 1;
		gridBagConstraintsx08.insets = new Insets(5, 5, 0, 5);
		gridBagConstraintsx08.gridy = 2;
		gridBagConstraintsx08.fill = GridBagConstraints.BOTH;
		buttonsPanel.add(this.btnInt, gridBagConstraintsx08);
		this.buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 12, 10));

		/// ----------------------------------------------------------------------------------///

		// Layout pentru Containerul mare

		cPane.setLayout(new BoxLayout(this.cPane, BoxLayout.Y_AXIS));
		this.cPane.add(this.firstPolyPanel);
		cPane.add(Box.createRigidArea(new Dimension(0, 10)));
		this.cPane.add(this.poly1Display);
		cPane.add(Box.createRigidArea(new Dimension(0, 10)));
		this.cPane.add(this.secondPolyPanel);
		cPane.add(Box.createRigidArea(new Dimension(0, 10)));
		this.cPane.add(this.poly2Display);
		cPane.add(Box.createRigidArea(new Dimension(0, 10)));
		this.cPane.add(this.polyResDisplay);
		cPane.add(Box.createRigidArea(new Dimension(0, 10)));
		cPane.add(this.buttonsPanel);

	}

	public JTextField getTxtCoef1() {
		return txtCoef1;
	}

	public void setTxtCoef1(JTextField txtCoef1) {
		this.txtCoef1 = txtCoef1;
	}

	public JTextField getTxtPow1() {
		return txtPow1;
	}

	public void setTxtPow1(JTextField txtPow1) {
		this.txtPow1 = txtPow1;
	}

	public JTextField getTxtPow2() {
		return txtPow2;
	}

	public void setTxtPow2(JTextField txtPow2) {
		this.txtPow2 = txtPow2;
	}

	public JTextField getTxtCoef2() {
		return txtCoef2;
	}

	public void setTxtCoef2(JTextField txtCoef2) {
		this.txtCoef2 = txtCoef2;
	}

	public JTextField getTxtConst1() {
		return txtConst1;
	}

	public void setTxtConst1(JTextField txtConst1) {
		this.txtConst1 = txtConst1;
	}

	public JTextField getTxtConst2() {
		return txtConst2;
	}

	public void setTxtConst2(JTextField txtConst2) {
		this.txtConst2 = txtConst2;
	}

	public JTextField getTxtPoly1() {
		return txtPoly1;
	}

	public void setTxtPoly1(JTextField txtPoly1) {
		this.txtPoly1 = txtPoly1;
	}

	public JTextField getTxtPoly2() {
		return txtPoly2;
	}

	public void setTxtPoly2(JTextField txtPoly2) {
		this.txtPoly2 = txtPoly2;
	}

	public JTextField getTxtRes() {
		return txtRes;
	}

	public void setTxtRes(JTextField txtRes) {
		this.txtRes = txtRes;
	}

	public void actionBtnAdaugare1(ActionListener l) {
		this.btnAddMonom1.addActionListener(l);

	}

	public void actionBtnAdaugare2(ActionListener l) {
		this.btnAddMonom2.addActionListener(l);
		;
	}

	public void actionBtnAnulare1(ActionListener l) {
		this.btnAnulare1.addActionListener(l);
		;
	}

	public void actionBtnAnulare2(ActionListener l) {
		this.btnAnulare2.addActionListener(l);
		;
	}

	public void actionBtnAdunare(ActionListener l) {
		this.btnAdd.addActionListener(l);
	}

	public void actionBtnScadere(ActionListener l) {
		this.btnSub.addActionListener(l);
	}

	public void actionBtnDerivare(ActionListener l) {
		this.btnDer.addActionListener(l);
	}

	public void actionBtnImultire(ActionListener l) {
		this.btnMul.addActionListener(l);
	}

	public void actionBtnIntegrare(ActionListener l) {
		this.btnInt.addActionListener(l);
	}

	public void actionBtnImpartire(ActionListener l) {
		this.btnDiv.addActionListener(l);
	}

	public void actionEnter1(KeyAdapter l) {
		this.txtPow1.addKeyListener(l);
		this.txtCoef1.addKeyListener(l);
	}

	public void actionEnter2(KeyAdapter l) {
		this.txtPow2.addKeyListener(l);
		this.txtCoef2.addKeyListener(l);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(txtRes)) {
			StringSelection stringSelection = new StringSelection(txtRes.getText());
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
