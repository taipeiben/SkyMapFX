package com.browniebytes.javafx.skymapfx.gui.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import com.browniebytes.javafx.skymapfx.data.entities.Star;

public class SkyMapCanvas extends Canvas {

	private static final Logger LOGGER = LoggerFactory.getLogger(SkyMapCanvas.class);
	private static final int PAD = 10;

	private boolean drawAltAziGrid;

	public SkyMapCanvas() {
		widthProperty().addListener(event -> draw(null));
		heightProperty().addListener(event -> draw(null));
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public double prefWidth(double height) {
		return 300;
	}

	@Override
	public double prefHeight(double width) {
		return 300;
	}

	public void redraw(final boolean drawAltAziGrid, final List<Star> starList) {
		this.drawAltAziGrid = drawAltAziGrid;
		draw(starList);
	}

	private void draw(final List<Star> starList) {
		final double d = getWidth() - 2 * PAD;

		final GraphicsContext g = getGraphicsContext2D();
		g.setFill(Color.color(244.0/255, 244.0/255, 244.0/255));
		g.fillRect(0, 0, getWidth(), getWidth());

		g.setFill(Color.BLACK);
		g.fillOval(PAD, PAD, d, d);

		if (drawAltAziGrid) {
			drawAltAziGrid(d);
		}

		if (starList != null) {
			drawStars(d, starList);
		}
	}

	private void drawStars(final double d, final List<Star> starList) {
		final double r = d / 2;
		final GraphicsContext g = getGraphicsContext2D();
		g.setFill(Color.WHITE);

		double max = -1;
		long maxId = -1;
		double maxMag = -1;
		for (Star star : starList) {
			final double radius = r * (Math.PI / 2 - star.getAltitudeInRadians()) / (Math.PI / 2);
			final double angle = Math.PI / 2 - star.getAzimuthInRadians();

			final double x = radius * Math.cos(angle);
			final double y = radius * Math.sin(angle);

			final double xcoord = d / 2 + PAD + x;
			final double ycoord = d / 2 + PAD - y;

			final double mag = (d / 600) / star.getMagnitude();
			if (mag > max) {
				max = mag;
				maxId = star.getCatalogNumber();
				maxMag = star.getMagnitude();
			}
			g.fillOval(xcoord, ycoord, mag, mag);
		}

		LOGGER.debug("Largest mag: " + max + " Id: " + maxId + " mag: " + maxMag);
	}

	private void drawAltAziGrid(final double d) {
		final double r = d/2;
		final GraphicsContext g = getGraphicsContext2D();
		g.setStroke(Color.color(0.15, 0.15, 0.15));
		g.strokeOval(PAD, PAD, d, d);

		final double d20 = d * 2/9;
		final double d30 = d / 3;
		final double d40 = d * 4/9;
		final double d50 = d * 5/9;
		final double d60 = d * 2/3;
		final double d70 = d * 7/9;
		g.strokeOval(d20/2+PAD, d20/2+PAD, d70, d70);
		g.strokeOval(d30/2+PAD, d30/2+PAD, d60, d60);
		g.strokeOval(d40/2+PAD, d40/2+PAD, d50, d50);
		g.strokeOval(d50/2+PAD, d50/2+PAD, d40, d40);
		g.strokeOval(d60/2+PAD, d60/2+PAD, d30, d30);
		g.strokeOval(d70/2+PAD, d70/2+PAD, d20, d20);

		for (int i=0; i < 12; i++) {
			drawRadialLine(g, r, i);
		}

		final double d5 = d * 5/90;
		final double d85 = d * 85/90;
		g.setFill(Color.BLACK);
		g.fillOval(d85/2+PAD, d85/2+PAD, d5, d5);
		g.strokeOval(d85/2+PAD, d85/2+PAD, d5, d5);

		g.setTextAlign(TextAlignment.CENTER);
		g.setFont(Font.font("Sans", 10));
		g.setFill(Color.BLACK);

		drawRotatedAndTranslatedText(g, "N", PAD+d/2, PAD-2, 0);
		drawRotatedAndTranslatedText(g, "E", PAD+d+3, PAD+d/2-1, 90);
		drawRotatedAndTranslatedText(g, "S", PAD+d/2, PAD+d+2, 180);
		drawRotatedAndTranslatedText(g, "W", PAD-2, PAD+d/2-1, 270);
	}

	private void drawRadialLine(final GraphicsContext g, final double r, final int k) {
		g.strokeLine(
				r-r*Math.cos(Math.PI*k/12)+PAD,
				r+r*Math.sin(Math.PI*k/12)+PAD,
				r+r*Math.cos(Math.PI*k/12)+PAD,
				r-r*Math.sin(Math.PI*k/12)+PAD);
	}

	private void drawRotatedAndTranslatedText(final GraphicsContext g, final String text, final double x, final double y, final double deg) {
		g.translate(x, y);
		g.rotate(deg);
		g.fillText(text, 0, 0);
		g.rotate(-deg);
		g.translate(-x, -y);
	}
}
