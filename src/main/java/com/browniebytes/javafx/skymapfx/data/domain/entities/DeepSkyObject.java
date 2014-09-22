package com.browniebytes.javafx.skymapfx.data.domain.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity(name = "DEEP_SKY_OBJECTS")
@NamedQueries({
	@NamedQuery(
		name = "findDsoByCatalogId",
		query = "from com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject d where d.catalogNumber = :catalogNumber"),
	@NamedQuery(
		name = "findAllDsoByPositiveAltitude",
		query = "from com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject d where d.altitude > 0")
})
public class DeepSkyObject {

	/**
	 * Primary Key
	 */
	@GeneratedValue
	@Id
	private Long id;

	/**
	 * DSO catalog type
	 */
	@Enumerated(EnumType.ORDINAL)
	private DsoCatalog catalog;

	/**
	 * Catalog Number
	 */
	@Column(nullable = false)
	private Long catalogNumber;

	/**
	 * Deep Sky Object type
	 */
	@Enumerated(EnumType.ORDINAL)
	private DsoType type;

	/**
	 * Right ascension
	 */
	@Column(nullable = false)
	private Double ra;

	/**
	 * Declination
	 */
	@Column(nullable = false)
	private Double dec;

	/**
	 * Magnitude
	 */
	@Column(nullable = false)
	private Double magnitude;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DsoCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(DsoCatalog catalog) {
		this.catalog = catalog;
	}

	public Long getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(Long catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public DsoType getType() {
		return type;
	}

	public void setType(DsoType type) {
		this.type = type;
	}

	public Double getRa() {
		return ra;
	}

	public void setRa(Double ra) {
		this.ra = ra;
	}

	public Double getDec() {
		return dec;
	}

	public void setDec(Double dec) {
		this.dec = dec;
	}

	public Double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(Double magnitude) {
		this.magnitude = magnitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	@Transient
	public Double getAltitudeInRadians() {
		return Math.toRadians(altitude);
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getAzimuth() {
		return azimuth;
	}

	@Transient
	public Double getAzimuthInRadians() {
		return Math.toRadians(azimuth);
	}

	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}

	public enum DsoType {
		CLUSTER_AND_NEBULA("C+N"),
		GALAXY("Gx"),
		GLOBULAR_CLUSTER("Gb"),
		NEBULA("Nb"),
		OPEN_CLUSTER("OC");

		private static final Map<String, DsoType> lookup = new HashMap<>();
		private final String value;

		private DsoType(final String value) {
			this.value = value;
		}

		static {
			for (DsoType dsoType : values()) {
				lookup.put(dsoType.value, dsoType);
			}
			lookup.put("Pl", NEBULA);
		}

		public static DsoType getValueOf(final String str) {
			return lookup.get(str);
		}
	}

	public enum DsoCatalog {
		INDEX('I'),
		MESSIER('M'),
		NEW_GENERAL('N');

		private static final Map<Character, DsoCatalog> lookup = new HashMap<>();
		private final char value;

		private DsoCatalog(final Character value) {
			this.value = value;
		}

		static {
			for (DsoCatalog dsoCat : values()) {
				lookup.put(dsoCat.value, dsoCat);
			}
		}

		public static DsoCatalog getValueOf(char c) {
			return lookup.get(c);
		}
	}
}
