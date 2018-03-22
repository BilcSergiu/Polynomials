package ro.tuc.pt.Assigment1.Models;

public class Monomial implements Comparable {
	private float coeffcient;
	private float power;

	public Monomial(float coeff, float power) {
		this.coeffcient = coeff;
		this.power = power;
	}

	public float getCoefficient() {
		return this.coeffcient;
	}

	public float getPower() {
		return this.power;
	}

	public void setCoeffcient(float coeff) {
		this.coeffcient = coeff;
	}

	public void setPower(float pow) {
		this.power = pow;
	}

	@Override
	public int compareTo(Object arg0) {
		int power = (int) ((Monomial) arg0).power;
		return (int) (power - this.power);
	}
}
