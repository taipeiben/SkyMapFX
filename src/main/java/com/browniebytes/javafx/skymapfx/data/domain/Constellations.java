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
	AQUILA(
			"Aquila",
			new ConstellationSegment[] {
				new ConstellationSegment(95501, 97804),
				new ConstellationSegment(95501, 93747),
				new ConstellationSegment(95501, 93805),
				new ConstellationSegment(95501, 97278),
				new ConstellationSegment(97278, 97649),
				new ConstellationSegment(97649, 98036),
				new ConstellationSegment(97804, 99473),
				new ConstellationSegment(93747, 93244),
				new ConstellationSegment(93747, 93805),
				new ConstellationSegment(93805, 93429),
				new ConstellationSegment(93805, 96468),
				new ConstellationSegment(96468, 99473),
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
	CEPHEUS(
			"Cepheus",
			new ConstellationSegment[] {
				new ConstellationSegment(105199, 107259),
				new ConstellationSegment(107259, 109857),
				new ConstellationSegment(109857, 109492),
				new ConstellationSegment(109492, 110991),
				new ConstellationSegment(110991, 112724),
				new ConstellationSegment(112724, 106032),
				new ConstellationSegment(106032, 116727),
				new ConstellationSegment(116727, 112724),
				new ConstellationSegment(105199, 102422),
				new ConstellationSegment(105199, 106032),
				new ConstellationSegment(102422, 101093),
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
	CYGNUS(
			"Cygnus",
			new ConstellationSegment[] {
				new ConstellationSegment(100453, 102098),
				new ConstellationSegment(100453, 102488),
				new ConstellationSegment(100453, 98110),
				new ConstellationSegment(100453, 97165),
				new ConstellationSegment(97165, 95853),
				new ConstellationSegment(95853, 94779),
				new ConstellationSegment(95853, 99848),
				new ConstellationSegment(99848, 102098),
				new ConstellationSegment(102098, 103413),
				new ConstellationSegment(103413, 104732),
				new ConstellationSegment(104732, 102488),
				new ConstellationSegment(98110, 95947),
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
	DRACO(
			"Draco",
			new ConstellationSegment[] {
				new ConstellationSegment(87833, 85670),
				new ConstellationSegment(85670, 85829), 
				new ConstellationSegment(85829, 87585),
				new ConstellationSegment(87585, 87833),
				new ConstellationSegment(87585, 94376),
				new ConstellationSegment(94376, 97433),
				new ConstellationSegment(97433, 89937),
				new ConstellationSegment(89937, 83895),
				new ConstellationSegment(83895, 80331),
				new ConstellationSegment(80331, 78527),
				new ConstellationSegment(78527, 75458),
				new ConstellationSegment(75458, 68756),
				new ConstellationSegment(68756, 61281),
				new ConstellationSegment(61281, 56211),
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
	HERCULES(
			"Hercules",
			new ConstellationSegment[] {
				new ConstellationSegment(84345, 80816),
				new ConstellationSegment(84345, 84379),
				new ConstellationSegment(80816, 80170),
				new ConstellationSegment(80816, 81693),
				new ConstellationSegment(80170, 80463),
				new ConstellationSegment(80463, 81008),
				new ConstellationSegment(84379, 85693),
				new ConstellationSegment(85693, 86974),
				new ConstellationSegment(86974, 87933),
				new ConstellationSegment(87933, 88794),
				new ConstellationSegment(84379, 83207),
				new ConstellationSegment(83207, 81693),
				new ConstellationSegment(83207, 84380),
				new ConstellationSegment(84380, 85112),
				new ConstellationSegment(85112, 87808),
				new ConstellationSegment(87808, 86414),
				new ConstellationSegment(84380, 81833),
				new ConstellationSegment(81833, 81693),
				new ConstellationSegment(81833, 81126),
				new ConstellationSegment(81126, 79992),
				new ConstellationSegment(79992, 79101),
				new ConstellationSegment(79101, 77760),
			}),
	LEO(
			"Leo",
			new ConstellationSegment[] {
				new ConstellationSegment(47908, 48455),
				new ConstellationSegment(48455, 50335),
				new ConstellationSegment(50335, 50583),
				new ConstellationSegment(50583, 49583),
				new ConstellationSegment(50583, 54872),
				new ConstellationSegment(54872, 57632),
				new ConstellationSegment(54872, 54879),
				new ConstellationSegment(57632, 54879),
				new ConstellationSegment(54879, 49669),
				new ConstellationSegment(49669, 49583),
			}),
	LYRA(
			"Lyra",
			new ConstellationSegment[] {
				new ConstellationSegment(91262, 91971),
				new ConstellationSegment(91971, 91926),
				new ConstellationSegment(91971, 92420),
				new ConstellationSegment(91971, 92791),
				new ConstellationSegment(92791, 93194),
				new ConstellationSegment(93194, 92420),
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
	SAGITTA(
			"Sagitta",
			new ConstellationSegment[] {
				new ConstellationSegment(98920, 98337),
				new ConstellationSegment(98337, 97365),
				new ConstellationSegment(97365, 96757),
				new ConstellationSegment(97365, 96837),
			}),
	SCORPIUS(
			"Scorpius",
			new ConstellationSegment[] {
				new ConstellationSegment(80763, 78820),
				new ConstellationSegment(80763, 78401),
				new ConstellationSegment(80763, 78265),
				new ConstellationSegment(80763, 81266),
				new ConstellationSegment(81266, 82396),
				new ConstellationSegment(82396, 82514),
				new ConstellationSegment(82514, 82671),
				new ConstellationSegment(82671, 84143),
				new ConstellationSegment(84143, 86228),
				new ConstellationSegment(86228, 87073),
				new ConstellationSegment(87073, 86670),
				new ConstellationSegment(86670, 85927),
			}),
	SCUTUM(
			"Scutum",
			new ConstellationSegment[] {
				new ConstellationSegment(91117, 92175),
				new ConstellationSegment(92175, 91726),
				new ConstellationSegment(91726, 90595),
				new ConstellationSegment(90595, 91117),
			}),
	TRIANGULUM(
			"Triangulum",
			new ConstellationSegment[] {
				new ConstellationSegment(10064, 8796),
				new ConstellationSegment(8796, 10559),
				new ConstellationSegment(10559, 10064),
			}),
	URSA_MAJOR(
			"Ursa Major",
			new ConstellationSegment[] {
				new ConstellationSegment(41704, 46733),
				new ConstellationSegment(41704, 48319),
				new ConstellationSegment(46733, 48319),
				new ConstellationSegment(46733, 54061),
				new ConstellationSegment(54061, 59774),
				new ConstellationSegment(59774, 62956),
				new ConstellationSegment(62956, 65378),
				new ConstellationSegment(65378, 67301),
				new ConstellationSegment(59774, 58001),
				new ConstellationSegment(58001, 53910),
				new ConstellationSegment(53910, 54061),
				new ConstellationSegment(53910, 48402),
				new ConstellationSegment(48402, 48319),
				new ConstellationSegment(48402, 46853),
				new ConstellationSegment(46853, 44127),
				new ConstellationSegment(46853, 44471),
				new ConstellationSegment(58001, 57399),
				new ConstellationSegment(57399, 54539),
				new ConstellationSegment(54539, 50801),
				new ConstellationSegment(54539, 50372),
			}),
	URSA_MINOR(
			"Ursa Minor",
			new ConstellationSegment[] {
				new ConstellationSegment(11767, 85822),
				new ConstellationSegment(85822, 82080),
				new ConstellationSegment(82080, 77055),
				new ConstellationSegment(77055, 72607),
				new ConstellationSegment(72607, 75097),
				new ConstellationSegment(75097, 79822),
				new ConstellationSegment(79822, 77055),
			}),
	VIRGO(
			"Virgo",
			new ConstellationSegment[] {
				new ConstellationSegment(57757, 57380),
				new ConstellationSegment(57380, 58948),
				new ConstellationSegment(58948, 60129),
				new ConstellationSegment(60129, 57757),
				new ConstellationSegment(60129, 61941),
				new ConstellationSegment(61941, 63090),
				new ConstellationSegment(63090, 63608),
				new ConstellationSegment(61941, 66249),
				new ConstellationSegment(66249, 68520),
				new ConstellationSegment(68520, 72220),
				new ConstellationSegment(66249, 69701),
				new ConstellationSegment(69701, 71957),
				new ConstellationSegment(61941, 64238),
				new ConstellationSegment(64238, 65474),
			}),
	VULPECULA(
			"Vulpecula",
			new ConstellationSegment[] {
				new ConstellationSegment(98543, 95771),
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
