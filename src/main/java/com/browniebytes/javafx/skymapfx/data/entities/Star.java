package com.browniebytes.javafx.skymapfx.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class encapsulates all the data that is stored for a star.
 */
@Entity(name = "STARS")
public class Star {

	/**
	 * Primary Key
	 */
	@GeneratedValue
	@Id
	private long id;

	/**
	 * Catalog Number
	 */
	private long catalogNumber;

	/**
	 * Right ascension
	 */
	private double ra;

	/**
	 * Right ascension proper motion
	 */
	private double raPm;

	/**
	 * Declination
	 */
	private double dec;

	/**
	 * Declination proper motion
	 */
	private double decPm;

	/**
	 * Magnitude
	 */
	private double magnitude;

	/**
	 * Spectral type
	 */
	private String spectralType;

	/**
	 * Gets the primary key
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the primary key
	 * @param id Primary key to use
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the catalog number
	 * @return The catalog number
	 */
	public long getCatalogNumber() {
		return catalogNumber;
	}

	/**
	 * Sets the catalog number
	 * @param catalogNumber The catalog number to use
	 */
	public void setCatalogNumber(long catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	/**
	 * Gets the right ascension of the star
	 * @return The right ascension of the star
	 */
	public double getRa() {
		return ra;
	}

	/**
	 * Sets the right ascension of the star
	 * @param ra The right ascension value to use
	 */
	public void setRa(double ra) {
		this.ra = ra;
	}

	/**
	 * Gets the proper motion of right ascension of the star
	 * @return The proper motion of right ascension of the star
	 */
	public double getRaPm() {
		return raPm;
	}

	/**
	 * Sets the proper motion of right ascension of the star
	 * @param raPm The proper motion of right ascension value to use
	 */
	public void setRaPm(double raPm) {
		this.raPm = raPm;
	}

	/**
	 * Gets the declination
	 * @return The declination of the star
	 */
	public double getDec() {
		return dec;
	}

	/**
	 * Sets the declination of the star
	 * @param dec The declination value to use
	 */
	public void setDec(double dec) {
		this.dec = dec;
	}

	/**
	 * Gets the proper motion of declination of the star
	 * @return The proper motion of declination of the star
	 */
	public double getDecPm() {
		return decPm;
	}

	/**
	 * Sets the proper motion of declination of the star
	 * @param raPm The proper motion of declination value to use
	 */
	public void setDecPm(double decPm) {
		this.decPm = decPm;
	}

	/**
	 * Gets the visual magnitude of the star
	 * @return The magnitude of the star
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * Sets the magnitude of the star
	 * @param magnitude The magnitude value to use
	 */
	public void setMagnitude(double magnitude) {
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
	public void setSpectralType(String spectralType) {
		this.spectralType = spectralType;
	}
}
