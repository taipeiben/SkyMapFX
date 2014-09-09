package com.browniebytes.javafx.skymapfx.data.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeComputations {

	private static final ZoneId GMT_ZONE = ZoneId.of("GMT");

	private final LocalDateTime localDateTime;
	private final double longitude;
	private final ZonedDateTime gmtTime;
	private final double jd;
	private final double mjd;
	private final double gmstDeg;
	private final LocalTime gmst;
	private final LocalTime gast;
	private final LocalTime lmst;

	public TimeComputations(final LocalDateTime localDateTime, final double longitude) {
		this.localDateTime = localDateTime;
		this.longitude = longitude;

		// Compute GMT Time
		final ZoneId localZone = ZoneId.systemDefault();
		final ZonedDateTime zonedLocalTime = ZonedDateTime.of(localDateTime, localZone);
		this.gmtTime = zonedLocalTime.withZoneSameInstant(GMT_ZONE);

		// Compute Julian Day
		this.jd = convertGregorianDateTimeToJulianDay(
				gmtTime.getYear(),
				gmtTime.getMonth().getValue(),
				gmtTime.getDayOfMonth(),
				gmtTime.getHour(),
				gmtTime.getMinute(),
				gmtTime.getSecond());

		// Compute Modified Julian Day
		this.mjd = convertJulianDayToModifiedJulianDay();

		// Compute GMST
		this.gmstDeg = normalizeDegrees(convertJulianDayToGMST());
		this.gmst = LocalTime.ofNanoOfDay(
				(long) (86_400_000_000_000L * (gmstDeg/360.0)));

		// Compute GAST
		this.gast = LocalTime.ofNanoOfDay(
				(long) (86_400_000_000_000L * (normalizeDegrees(convertJulianDayToGAST())/360.0)));

		// Compute LMST
		this.lmst = LocalTime.ofNanoOfDay(
				(long) (86_400_000_000_000L * (normalizeDegrees(convertJulianDayToLMST())/360.0)));
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public double getLongitude() {
		return longitude;
	}

	public ZonedDateTime getGmtTime() {
		return gmtTime;
	}

	public double getJd() {
		return jd;
	}

	public double getMjd() {
		return mjd;
	}

	public LocalTime getGmst() {
		return gmst;
	}

	public LocalTime getGast() {
		return gast;
	}

	public LocalTime getLmst() {
		return lmst;
	}

	private double normalizeDegrees(double degrees) {
		while (degrees > 360) {
			degrees -= 360;
		}

		while (degrees < 0) {
			degrees += 360;
		}

		return degrees;
	}

	private double convertGregorianDateTimeToJulianDay(
			final double year,
			final double month,
			final double day,
			final double hour,
			final double minute,
			final double second) {

		final double a = Math.floor((14 - month) / 12.0);
		final double y = year + 4800.0 - a;
		final double m = month + 12*a - 3;

		final double jdn =
				day + Math.floor((153*m+2)/5) + 365.0*y + Math.floor(y/4)-Math.floor(y/100)+Math.floor(y/400)-32045.0;
		final double jd =
				jdn + (hour - 12.0)/24.0 + minute/1440.0 + second/86400.0;

		return jd;
	}

	private double convertJulianDayToModifiedJulianDay() {
		return jd - 2400000.5;
	}

	private double convertJulianDayToGMST() {
		final double d = jd - 2451545.0;
		final double T = d/36525;

		double sidereal = 280.46061837 + (360.98564736629 * (jd - 2451545.0)) + (0.000387933 * T * T) - (T * T * T / 38710000.0);
		sidereal %= 360;
		if (sidereal < 0) {
			sidereal += 360;
		}
		return sidereal;
	}

	private double convertJulianDayToGAST() {
		final double d = jd - 2451545.0;
		final double omega = 125.04 - 0.052954 * d;
		final double l = 280.47 + 0.98565 * d;
		final double e = 23.4393 - 0.0000004 * d;

		final double w = -0.000319 * Math.sin(omega * Math.PI/180) - 0.000024 * Math.sin((2 * l) * Math.PI/180);
		final double correction = w * Math.cos(e * Math.PI/180);

		return gmstDeg + correction * 15;
	}

	private double convertJulianDayToLMST() {
		final double lmst = gmstDeg + longitude;
		return lmst;
	}

}
