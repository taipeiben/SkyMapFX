package com.browniebytes.javafx.skymapfx.gui.view;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import com.browniebytes.javafx.skymapfx.data.domain.Constellations;
import com.browniebytes.javafx.skymapfx.data.domain.Constellations.ConstellationSegment;
import com.browniebytes.javafx.skymapfx.data.domain.entities.Star;

/**
 * This Canvas is used to draw the star map using JavaFX's 2D drawing capabilities.
 */
public class SkyMapCanvas extends Canvas {

	/**
	 * Padding
	 */
	private static final int PAD = 10;

	//Store these values in case we need to redraw
	private boolean drawConstellationLines; // Draw the constellation lines?
	private boolean showConstellationNames; // Show the constellation names?
	private boolean drawAltAziGrid; // Draw the grid lines?
	private boolean flipHorizontal; // Flip East and West (looking up at map or looking down)

	public SkyMapCanvas() {
		// Add redraw listeners to redraw the grid (empty graph)
		// when the canvas is being resized.  Celestial elements
		// will be added at the next redraw.
		widthProperty().addListener(event -> draw(null));
		heightProperty().addListener(event -> draw(null));
	}

	/**
	 * Canvas is resizable
	 */
	@Override
	public boolean isResizable() {
		return true;
	}

	/**
	 * Preferred width (to start with)
	 */
	@Override
	public double prefWidth(double width) {
		return 300;
	}

	/**
	 * Preferred height (to start with)
	 */
	@Override
	public double prefHeight(double height) {
		return 300;
	}

	/**
	 * Causes the canvas to be redrawn
	 * @param drawAltAziGrid Draw the altitude and azimuth grid lines
	 * @param flipHorizontal Flip the map horizontally (east -> west)
	 * @param starList Hash map of stars to draw
	 */
	public void redraw(
			final boolean drawConstellationLines,
			final boolean showConstellationNames,
			final boolean drawAltAziGrid,
			final boolean flipHorizontal,
			final Map<Long, Star> starMap) {

		this.drawConstellationLines = drawConstellationLines;
		this.showConstellationNames = showConstellationNames;
		this.drawAltAziGrid = drawAltAziGrid;
		this.flipHorizontal = flipHorizontal;

		try {
			draw(starMap);
		} catch (Exception ex) {
			// TODO: remove
			ex.printStackTrace();
		}
	}

	/**
	 * Clears the canvas, then calls other draw methods to draw the different
	 * elements of the map
	 * @param starList Hash map of stars to draw
	 */
	private void draw(final Map<Long, Star> starMap) {

		final double d = getWidth() - 2 * PAD;

		final GraphicsContext g = getGraphicsContext2D();
		g.setFill(Color.color(244.0/255, 244.0/255, 244.0/255));
		g.fillRect(0, 0, getWidth(), getWidth());

		g.setFill(Color.BLACK);
		g.fillOval(PAD, PAD, d, d);

		if (drawAltAziGrid) {
			drawAltAziGrid(d);
		}

		if (starMap != null) {
			if (drawConstellationLines) {
				drawConstellationLines(d, starMap);
			}
			drawStars(d, starMap);
		}
	}

	private void drawConstellationLines(final double d, final Map<Long, Star> starMap) {
		final GraphicsContext g = getGraphicsContext2D();
		g.setStroke(Color.color(0.0, 0.0, 0.75));
		g.setFill(Color.color(0.6, 0.76, 0.9));

		for (Constellations constellation : Constellations.values()) {
			int sumX = 0;
			int sumY = 0;
			final Set<Long> visitedStars = new HashSet<>();
			for (ConstellationSegment segment : constellation.getConstellationSegments()) {
				final Star start = starMap.get(segment.getStart());
				final Star end = starMap.get(segment.getEnd());

				if (start != null && end != null) {

					final double[] startXy = getXYFromAltAzi(
							d,
							start.getAltitudeInRadians(),
							start.getAzimuthInRadians());

					final double[] endXy = getXYFromAltAzi(
							d,
							end.getAltitudeInRadians(),
							end.getAzimuthInRadians());

					if (!visitedStars.contains(start.getCatalogNumber())) {
						visitedStars.add(start.getCatalogNumber());
						sumX += startXy[0];
						sumY += startXy[1];
					}

					if (!visitedStars.contains(end.getCatalogNumber())) {
						visitedStars.add(end.getCatalogNumber());
						sumX += endXy[0];
						sumY += endXy[1];
					}

					g.strokeLine(startXy[0], startXy[1], endXy[0], endXy[1]);
				}
			}

			if (showConstellationNames) {
				final double nameX = ((double) sumX) / ((double) visitedStars.size());
				final double nameY = ((double) sumY) / ((double) visitedStars.size());
				g.fillText(constellation.getName(), nameX, nameY);
			}
		}
	}

	/**
	 * Draws stars
	 * @param d Diameter of the map circle
	 * @param starList List of stars to draw
	 */
	private void drawStars(final double d, final Map<Long, Star> starMap) {

		final GraphicsContext g = getGraphicsContext2D();
		g.setFill(Color.WHITE);

		for (Entry<Long, Star> entry : starMap.entrySet()) {
			final Star star = entry.getValue();

			final double[] xy = getXYFromAltAzi(d, star.getAltitudeInRadians(), star.getAzimuthInRadians());

			final double size = getStarDrawSize(star.getMagnitude(), d);
			g.fillOval(xy[0]-size/2, xy[1]-size/2, size, size);
		}
	}

	private double[] getXYFromAltAzi(final double d, final double alt, final double azi) {
		final double r = d / 2;
		final double radius = r * (Math.PI / 2 - alt) / (Math.PI / 2);
		final double angle = Math.PI / 2 - azi;

		double x = radius * Math.cos(angle);
		final double y = radius * Math.sin(angle);

		if (flipHorizontal) {
			x = x * -1;
		}

		final double xcoord = d / 2 + PAD + x;
		final double ycoord = d / 2 + PAD - y;

		return new double[] { xcoord, ycoord };
	}

	/**
	 * Computes star size based on magnitude and size of the map
	 * @param magnitude Magnitude
	 * @param d Diameter of the map circle in pixels
	 * @return Star size in pixels
	 */
	private double getStarDrawSize(final double magnitude, final double d) {
		final double scale = d / 280.0;
		if (magnitude < 0) {
			return 2.5 * scale;
		} else if (magnitude >= 0 && magnitude < 1) {
			return 2.0 * scale;
		} else if (magnitude >= 1 && magnitude < 2) {
			return 1.3 * scale;
		} else if (magnitude >= 2 && magnitude < 3) {
			return 0.9 * scale;
		} else if (magnitude >= 3 && magnitude < 4) {
			return 0.5 * scale;
		} else if (magnitude >= 4 && magnitude < 5) {
			return 0.3 * scale;
		} else if (magnitude >= 5 && magnitude < 6) {
			return 0.15 * scale;
		} else if (magnitude >= 6 && magnitude < 7) {
			return 0.1 * scale;
		} else if (magnitude >= 7 && magnitude < 8) {
			return 0.0 * scale;
		}
		return 0;
	}

	/**
	 * Draws the altitude azimuth grid lines
	 * @param d Diameter of the map circle
	 */
	private void drawAltAziGrid(final double d) {
		final double r = d/2;
		final GraphicsContext g = getGraphicsContext2D();
		g.setStroke(Color.color(0.15, 0.15, 0.15));
		g.strokeOval(PAD, PAD, d, d);

		// Draw circles around the zenith
		final double d20 = d * 2 / 9;
		final double d30 = d / 3;
		final double d40 = d * 4 / 9;
		final double d50 = d * 5 / 9;
		final double d60 = d * 2 / 3;
		final double d70 = d * 7 / 9;
		g.strokeOval(d20 / 2 + PAD, d20 / 2 + PAD, d70, d70);
		g.strokeOval(d30 / 2 + PAD, d30 / 2 + PAD, d60, d60);
		g.strokeOval(d40 / 2 + PAD, d40 / 2 + PAD, d50, d50);
		g.strokeOval(d50 / 2 + PAD, d50 / 2 + PAD, d40, d40);
		g.strokeOval(d60 / 2 + PAD, d60 / 2 + PAD, d30, d30);
		g.strokeOval(d70 / 2 + PAD, d70 / 2 + PAD, d20, d20);

		for (int i=0; i < 12; i++) {
			drawRadialLine(g, r, i);
		}

		// But don't clutter the center with converging lines,
		// draw a filled black circle in center to keep it clear.
		final double d5 = d * 5/90;
		final double d85 = d * 85/90;
		g.setFill(Color.BLACK);
		g.fillOval(d85 / 2 + PAD, d85 / 2 + PAD, d5, d5);
		g.strokeOval(d85 / 2 + PAD, d85 / 2 + PAD, d5, d5);

		// Draw N/S/E/W
		g.setTextAlign(TextAlignment.CENTER);
		g.setFont(Font.font("Sans", 10));
		g.setFill(Color.BLACK);
		drawRotatedAndTranslatedText(g, "N", PAD+d/2, PAD-2, 0);
		drawRotatedAndTranslatedText(g, (flipHorizontal) ? "W" : "E", PAD+d+3, PAD+d/2-1, 90);
		drawRotatedAndTranslatedText(g, "S", PAD+d/2, PAD+d+2, 180);
		drawRotatedAndTranslatedText(g, (flipHorizontal) ? "E" : "W", PAD-2, PAD+d/2-1, 270);
	}

	/**
	 * Draws a line from the center of the circle
	 * @param g Graphics context
	 * @param r Radius
	 * @param k Angle
	 */
	private void drawRadialLine(final GraphicsContext g, final double r, final int k) {
		g.strokeLine(
				r - r * Math.cos(Math.PI * k / 12) + PAD,
				r + r * Math.sin(Math.PI * k / 12) + PAD,
				r + r * Math.cos(Math.PI * k / 12) + PAD,
				r - r * Math.sin(Math.PI * k / 12) + PAD);
	}

	/**
	 * Draws text and rotates it
	 * @param g Graphics context
	 * @param text Text to draw
	 * @param x X location
	 * @param y Y location
	 * @param deg rotation
	 */
	private void drawRotatedAndTranslatedText(
			final GraphicsContext g,
			final String text,
			final double x,
			final double y,
			final double deg) {

		g.translate(x, y);
		g.rotate(deg);
		g.fillText(text, 0, 0);
		g.rotate(-deg);
		g.translate(-x, -y);
	}
}
