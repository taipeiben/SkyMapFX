package com.browniebytes.javafx.skymapfx.data.domain;

/**
 * Enumeration of constellations, which will be used to draw constellation lines.
 * Each enumeration will contain an array of Constellation segments.
 */
public enum Constellations {
	ANDROMEDA(
			"Andromeda",
			new ConstellationSegment[] {
				new ConstellationSegment(677, 3092),      // α -> δ
				new ConstellationSegment(3092, 2912),     // δ -> π
				new ConstellationSegment(2912, 5447),     // π -> β
				new ConstellationSegment(3092, 5447),     // δ -> β
				new ConstellationSegment(5447, 4436),     // β -> μ
				new ConstellationSegment(4436, 3881),     // μ -> ν
				new ConstellationSegment(3881, 5434),     // ν -> φ
				new ConstellationSegment(5434, 7607),     // φ -> 51
				new ConstellationSegment(5447, 9640),     // β -> γ
				new ConstellationSegment(2912, 116631),   // π -> ι
				new ConstellationSegment(116631, 116805), // ι -> κ
				new ConstellationSegment(116805, 116584), // κ -> λ
				new ConstellationSegment(116631, 113726), // ι -> ο
				new ConstellationSegment(3092, 3031),     // δ -> ε
				new ConstellationSegment(3031, 3693),     // ε -> ζ
				new ConstellationSegment(3693, 4463),     // ζ -> η
			}),
	ANTLIA(
			"Antlia",
			new ConstellationSegment[] {
				new ConstellationSegment(51172, 46515),
				new ConstellationSegment(51172, 53502),
			}),
	ARIES(
			"Aries",
			new ConstellationSegment[] {
				new ConstellationSegment(9884, 8903),
				new ConstellationSegment(8903, 8832),
				new ConstellationSegment(9884, 13209),
				new ConstellationSegment(13209, 14838),
			}),
	CAELUM(
			"Caelum",
			new ConstellationSegment[] {
				new ConstellationSegment(21770, 21060),
				new ConstellationSegment(21770, 21861),
				new ConstellationSegment(21861, 23595),
			}),
	CANCER(
			"Cancer",
			new ConstellationSegment[] {
				new ConstellationSegment(44066, 42911),
				new ConstellationSegment(42911, 40526),
				new ConstellationSegment(42911, 42806),
				new ConstellationSegment(42806, 40843),
				new ConstellationSegment(42806, 43103),
			}),
	CANES_VENATICI(
			"Canes Venatici",
			new ConstellationSegment[] {
				new ConstellationSegment(63125, 61317),
			}),
	CANIS_MINOR(
			"Canis Minor",
			new ConstellationSegment[] {
				new ConstellationSegment(37279, 36188),
			}),
	CASSIOPEIA(
			"Cassiopeia",
			new ConstellationSegment[] {
				new ConstellationSegment(746, 3179),
				new ConstellationSegment(3179, 4427),
				new ConstellationSegment(4427, 6686),
				new ConstellationSegment(6686, 8886),
			}),
	CORVUS(
			"Corvus",
			new ConstellationSegment[] {
				new ConstellationSegment(60965, 59803),
				new ConstellationSegment(59803, 59316),
				new ConstellationSegment(59316, 59199),
				new ConstellationSegment(59316, 61359),
				new ConstellationSegment(61359, 60965),
				new ConstellationSegment(60965, 61174),
			}),
	DELPHINUS(
			"Delphinus",
			new ConstellationSegment[] {
				new ConstellationSegment(101421, 101769),
				new ConstellationSegment(101769, 101958),
				new ConstellationSegment(101958, 102532),
				new ConstellationSegment(102532, 102281),
				new ConstellationSegment(102281, 101769),
			}),
	EQUULEUS(
			"Equuleus",
			new ConstellationSegment[] {
				new ConstellationSegment(104987, 105570),
				new ConstellationSegment(105570, 104858),
				new ConstellationSegment(104858, 104521),
				new ConstellationSegment(104521, 104987),
			}),
	FORNAX(
			"Fornax",
			new ConstellationSegment[] {
				new ConstellationSegment(14879, 13147),
				new ConstellationSegment(13147, 9677),
			}),
	GEMINI(
			"Gemini",
			new ConstellationSegment[] {
				new ConstellationSegment(36850, 34693),
				new ConstellationSegment(34693, 33018),
				new ConstellationSegment(34693, 36046),
				new ConstellationSegment(34693, 32246),
				new ConstellationSegment(36046, 36962),
				new ConstellationSegment(36962, 37826),
				new ConstellationSegment(36962, 37740),
				new ConstellationSegment(36962, 35550),
				new ConstellationSegment(35550, 34088),
				new ConstellationSegment(35550, 35350),
				new ConstellationSegment(35350, 32362),
				new ConstellationSegment(34088, 31681),
				new ConstellationSegment(32246, 30883),
				new ConstellationSegment(32246, 30343),
				new ConstellationSegment(30343, 29655),
				new ConstellationSegment(29655, 28734),
			}),
	ORION(
			"Orion",
			new ConstellationSegment[] {
				new ConstellationSegment(27989, 25336),
				new ConstellationSegment(27989, 26207),
				new ConstellationSegment(25336, 26207),
				new ConstellationSegment(27989, 26727),
				new ConstellationSegment(26727, 26311),
				new ConstellationSegment(26311, 25930),
				new ConstellationSegment(25930, 25336),
				new ConstellationSegment(25336, 22449),
				new ConstellationSegment(22449, 22509),
				new ConstellationSegment(22509, 22845),
				new ConstellationSegment(22845, 22957),
				new ConstellationSegment(22957, 23607),
				new ConstellationSegment(22449, 22549),
				new ConstellationSegment(22549, 22797),
				new ConstellationSegment(22797, 23123),
				new ConstellationSegment(26727, 27366),
				new ConstellationSegment(25930, 24436),
				new ConstellationSegment(27989, 28614),
				new ConstellationSegment(28614, 29426),
				new ConstellationSegment(28614, 29038),
				new ConstellationSegment(29038, 27913),
				new ConstellationSegment(29426, 28691),
			}),
	TRIANGULUM(
			"Triangulum",
			new ConstellationSegment[] {
				new ConstellationSegment(10064, 8796),
				new ConstellationSegment(8796, 10559),
				new ConstellationSegment(10559, 10064),
			}),
	URSA_MINOR(
			"Ursa Minor",
			new ConstellationSegment[] {
				new ConstellationSegment(11767L, 85822L),
				new ConstellationSegment(85822L, 82080L),
				new ConstellationSegment(82080L, 77055L),
				new ConstellationSegment(77055L, 72607L),
				new ConstellationSegment(72607L, 75097L),
				new ConstellationSegment(75097L, 79822L),
				new ConstellationSegment(79822L, 77055L),
			});

	private final String name;

	/**
	 * Segments
	 */
	private final ConstellationSegment[] segments;

	private Constellations(final String name, final ConstellationSegment[] segments) {
		this.name = name;
		this.segments = segments;
	}

	/**
	 * Gets the constellation's segments
	 * @return The constellation's segments
	 */
	public ConstellationSegment[] getConstellationSegments() {
		return segments;
	}

	/**
	 * Gets the constellation name
	 * @return The constellation name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Constellation segment represents a line between two stars of a constellation.
	 * The start and end point values are the catalog IDs of the stars.
	 */
	public static class ConstellationSegment {
		private long start;
		private long end;

		private ConstellationSegment(final long start, final long end) {
			this.start = start;
			this.end = end;
		}

		public long getStart() {
			return start;
		}

		public long getEnd() {
			return end;
		}
	}
}
