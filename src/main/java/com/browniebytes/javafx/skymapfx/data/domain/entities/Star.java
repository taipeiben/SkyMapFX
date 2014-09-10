package com.browniebytes.javafx.skymapfx.data.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * This class encapsulates all the data that is stored for a star.
 */
@Entity(name = "STARS")
@NamedQueries({
		@NamedQuery(
			name = "findStarByCatalogId",
			query = "from com.browniebytes.javafx.skymapfx.data.entities.Star s where s.catalogNumber = :catalogNumber"),
		@NamedQuery(
			name = "findAllStarByPositiveAltitude",
			query = "from com.browniebytes.javafx.skymapfx.data.entities.Star s where s.altitude > 0")
	})
public class Star {

	/**
	 * Primary Key
	 */
	@GeneratedValue
	@Id
	private Long id;

	/**
	 * Catalog Number
	 */
	@Column(nullable = false)
	private Long catalogNumber;

	/**
	 * Right ascension
	 */
	@Column(nullable = false)
	private Double ra;

	/**
	 * Right ascension proper motion
	 */
	@Column(nullable = false)
	private Double raPm;

	/**
	 * Declination
	 */
	@Column(nullable = false)
	private Double dec;

	/**
	 * Declination proper motion
	 */
	@Column(nullable = false)
	private Double decPm;

	/**
	 * Magnitude
	 */
	@Column(nullable = false)
	private Double magnitude;

	/**
	 * Spectral type
	 */
	@Column(nullable = false)
	private String spectralType;

	/**
	 * Altitude
	 */
	@Column(nullable = true)
	private Double altitude;

	/**
	 * Azimuth
	 */
	@Column(nullable = true)
	private Double azimuth;

	/**
	 * Gets the primary key
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the primary key
	 * @param id Primary key to use
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the catalog number
	 * @return The catalog number
	 */
	public Long getCatalogNumber() {
		return catalogNumber;
	}

	/**
	 * Sets the catalog number
	 * @param catalogNumber The catalog number to use
	 */
	public void setCatalogNumber(final Long catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	/**
	 * Gets the right ascension of the star in degrees
	 * @return The right ascension of the star in degrees
	 */
	public Double getRa() {
		return ra;
	}

	/**
	 * Sets the right ascension of the star in degrees
	 * @param ra The right ascension value in degrees to use
	 */
	public void setRa(final Double ra) {
		this.ra = ra;
	}

	/**
	 * Gets the proper motion of right ascension of the star
	 * @return The proper motion of right ascension of the star
	 */
	public Double getRaPm() {
		return raPm;
	}

	/**
	 * Sets the proper motion of right ascension of the star
	 * @param raPm The proper motion of right ascension value to use
	 */
	public void setRaPm(final Double raPm) {
		this.raPm = raPm;
	}

	/**
	 * Gets the declination in degrees
	 * @return The declination in degrees of the star
	 */
	public Double getDec() {
		return dec;
	}

	/**
	 * Sets the declination of the star in degrees
	 * @param dec The declination value in degrees to use
	 */
	public void setDec(final Double dec) {
		this.dec = dec;
	}

	/**
	 * Gets the proper motion of declination of the star
	 * @return The proper motion of declination of the star
	 */
	public Double getDecPm() {
		return decPm;
	}

	/**
	 * Sets the proper motion of declination of the star
	 * @param raPm The proper motion of declination value to use
	 */
	public void setDecPm(final Double decPm) {
		this.decPm = decPm;
	}

	/**
	 * Gets the visual magnitude of the star
	 * @return The magnitude of the star
	 */
	public Double getMagnitude() {
		return magnitude;
	}

	/**
	 * Sets the magnitude of the star
	 * @param magnitude The magnitude value to use
	 */
	public void setMagnitude(final Double magnitude) {
		this.magnitude = magnitude;
	}

	/**
	 * Gets the spectral type of the star
	 * @return The spectral type of the star
	 */
	public String getSpectralType() {
		return spectralType;
	}

	/**
	 * Sets the spectral type of the star
	 * @param spectralType The spectral type value to use
	 */
	public void setSpectralType(final String spectralType) {
		this.spectralType = spectralType;
	}

	/**
	 * Gets the altitude of the star
	 * @return The altitude of the star
	 */
	public Double getAltitude() {
		return altitude;
	}

	@Transient
	public Double getAltitudeInRadians() {
		return Math.toRadians(altitude);
	}

	/**
	 * Sets the altitude of the star
	 * @param altitude The altitude value to use
	 */
	public void setAltitude(final Double altitude) {
		this.altitude = altitude;
	}

	/**
	 * Gets the azimuth value of the star
	 * @return The azimuth value of the star
	 */
	public Double getAzimuth() {
		return azimuth;
	}

	@Transient
	public Double getAzimuthInRadians() {
		return Math.toRadians(azimuth);
	}

	/**
	 * Sets the azimuth value of the star
	 * @param azimuth The azimuth value of the star to use
	 */
	public void setAzimuth(final Double azimuth) {
		this.azimuth = azimuth;
	}
}
