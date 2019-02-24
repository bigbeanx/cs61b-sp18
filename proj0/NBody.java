/**
 * NBody is a class that will actually run your simulation.
 */
public class NBody {

	/** Return the universe radius in a txt file. */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	/** Return an array of planet objects based on given txt file. */
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] planets = new Planet[N];
		for (int i = 0; i < N; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return planets;
	}

	/** Main: drawing the initial universe state. */
	public static void main(String[] args) {
		/* Collecting all needed input. */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		Planet[] planets = NBody.readPlanets(filename);

		/* Drawing the background. */
		String bkgImg = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, bkgImg);
		StdDraw.show();

		/* Draw all planets. */	
		for (Planet planet : planets) {
			planet.draw();
		}

		/* Creat an animation. */
		StdDraw.enableDoubleBuffering();
		int nPlanets = planets.length;
		for (double t = 0; t <= T; t += dt) {
			double[] xForces = new double[nPlanets];
			double[] yForces = new double[nPlanets];
			for (int i = 0; i < nPlanets; i += 1) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < nPlanets; i += 1) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, bkgImg);
			for (Planet planet : planets) {
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		/* Print out the final state of the universe. */
		StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
        	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
	}
}
