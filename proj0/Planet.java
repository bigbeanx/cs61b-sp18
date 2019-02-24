/**
 *  Planet
 */
public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double G = 6.67e-11;

	/** Construct planet object with specified variable values. */
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Copy a planet object. */
	public Planet(Planet b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	/** Calculate distance between two planet objects. */
	public double calcDistance(Planet b) {
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		double rsquare = dx * dx + dy * dy;
		double r = Math.sqrt(rsquare);
		return r;
	}

	/** Calculate force extered on this planet by another planet. */
	public double calcForceExertedBy(Planet b) {
		double r = this.calcDistance(b);
		double force = G * this.mass * b.mass / (r * r);
		return force;
	}

	/** Calculate force x-component. */
	public double calcForceExertedByX(Planet b) {
		double r = this.calcDistance(b);
		double force = this.calcForceExertedBy(b);
		double dx = b.xxPos - this.xxPos;
		double forceX = force * dx / r;
		return forceX;
	}

	/** Calculate force y-component. */
	public double calcForceExertedByY(Planet b) {
		double r = this.calcDistance(b);
		double force = this.calcForceExertedBy(b);
		double dy = b.yyPos - this.yyPos;
		double forceY = force * dy / r;
		return forceY;
	}

	/** Calculate net force in x direction exterted by all planets. */
	public double calcNetForceExertedByX(Planet[] bs) {
		double netForceX = 0.0;
		double forceX = 0.0;
		for (Planet b : bs) {
			if (this.equals(b)) {
				continue;
			} else {
				forceX = this.calcForceExertedByX(b);
				netForceX += forceX;
			}
		}
		return netForceX;
	}

	/** Calculate net force in y direction exterted by all planets. */
	public double calcNetForceExertedByY(Planet[] bs) {
		double netForceY = 0.0;
		double forceY = 0.0;
		for (Planet b : bs) {
			if (this.equals(b)) {
				continue;
			} else {
				forceY = this.calcForceExertedByY(b);
				netForceY += forceY;
			}
		}
		return netForceY;
	}

	/** Update the planet's position and velocity. */
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	/** Draw a planet at its position on the canvas. */
	public void draw() {
		String imgFileName = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imgFileName);
	}
}
