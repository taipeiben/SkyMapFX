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
	APUS(
			"Apus",
			new ConstellationSegment[] {
				new ConstellationSegment(72370, 80047),
				new ConstellationSegment(80047, 81852),
				new ConstellationSegment(81852, 81065),
				new ConstellationSegment(81065, 80047),
			}),
	AQUARIUS(
			"Aquarius",
			new ConstellationSegment[] {
				new ConstellationSegment(109074, 110395),
				new ConstellationSegment(109074, 110003),
				new ConstellationSegment(109074, 106278),
				new ConstellationSegment(106278, 109139),
				new ConstellationSegment(106278, 102618),
				new ConstellationSegment(110003, 112961),
				new ConstellationSegment(112961, 114724),
				new ConstellationSegment(114724, 115033),
				new ConstellationSegment(115033, 113136),
				new ConstellationSegment(113136, 112716),
				new ConstellationSegment(112716, 112961),
				new ConstellationSegment(115033, 114341),
				new ConstellationSegment(115033, 115438),
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
	ARA(
			"Ara",
			new ConstellationSegment[] {
				new ConstellationSegment(85792, 85258),
				new ConstellationSegment(85792, 88714),
				new ConstellationSegment(85258, 85267),
				new ConstellationSegment(85267, 85727),
				new ConstellationSegment(85727, 82363),
				new ConstellationSegment(82363, 83081),
				new ConstellationSegment(83081, 83153),
				new ConstellationSegment(83153, 85792),
			}),
	ARIES(
			"Aries",
			new ConstellationSegment[] {
				new ConstellationSegment(9884, 8903),
				new ConstellationSegment(8903, 8832),
				new ConstellationSegment(9884, 13209),
				new ConstellationSegment(13209, 14838),
			}),
	AURIGA(
			"Auriga",
			new ConstellationSegment[] {
				new ConstellationSegment(24608, 23767),
				new ConstellationSegment(23767, 23015),
				new ConstellationSegment(23015, 25428),
				new ConstellationSegment(25428, 28380),
				new ConstellationSegment(28380, 28360),
				new ConstellationSegment(28360, 24608),
				new ConstellationSegment(28360, 28358),
				new ConstellationSegment(28358, 24608),
				new ConstellationSegment(24608, 23416),
				new ConstellationSegment(23416, 23453),
			}),
	BOOTES(
			"Boötes",
			new ConstellationSegment[] {
				new ConstellationSegment(69673, 71795),
				new ConstellationSegment(69673, 67927),
				new ConstellationSegment(67927, 67459),
				new ConstellationSegment(69673, 72105),
				new ConstellationSegment(69673, 71053),
				new ConstellationSegment(72105, 74666),
				new ConstellationSegment(74666, 73555),
				new ConstellationSegment(73555, 71075),
				new ConstellationSegment(71075, 71053),
			}),
	CAELUM(
			"Caelum",
			new ConstellationSegment[] {
				new ConstellationSegment(21770, 21060),
				new ConstellationSegment(21770, 21861),
				new ConstellationSegment(21861, 23595),
			}),
	CAMELOPARDALIS(
			"Camelopardalis",
			new ConstellationSegment[] {
				new ConstellationSegment(22783, 23522),
				new ConstellationSegment(23522, 23040),
				new ConstellationSegment(22783, 17959),
				new ConstellationSegment(17959, 17884),
				new ConstellationSegment(17884, 16228),
				new ConstellationSegment(22783, 24254),
				new ConstellationSegment(24254, 25110),
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
	CANIS_MAJOR(
			"Canis Major",
			new ConstellationSegment[] {
				new ConstellationSegment(33160, 34045),
				new ConstellationSegment(34045, 33347),
				new ConstellationSegment(33347, 33160),
				new ConstellationSegment(33347, 32349),
				new ConstellationSegment(32349, 30324),
				new ConstellationSegment(30324, 31592),
				new ConstellationSegment(31592, 33152),
				new ConstellationSegment(33152, 33579),
				new ConstellationSegment(33579, 34444),
				new ConstellationSegment(34444, 35904),
				new ConstellationSegment(34444, 33977),
				new ConstellationSegment(33977, 32349),
			}),
	CANIS_MINOR(
			"Canis Minor",
			new ConstellationSegment[] {
				new ConstellationSegment(37279, 36188),
			}),
	CAPRICORNUS(
			"Capricornus",
			new ConstellationSegment[] {
				new ConstellationSegment(100027, 100345),
				new ConstellationSegment(100345, 102485),
				new ConstellationSegment(102485, 102978),
				new ConstellationSegment(102978, 105881),
				new ConstellationSegment(105881, 107556),
				new ConstellationSegment(107556, 106985),
				new ConstellationSegment(106985, 104139),
				new ConstellationSegment(104139, 100027),
			}),
	CARINA(
			"Carina",
			new ConstellationSegment[] {
				new ConstellationSegment(45238, 50099),
				new ConstellationSegment(50099, 52419),
				new ConstellationSegment(52419, 51576),
				new ConstellationSegment(51576, 50371),
				new ConstellationSegment(50371, 46974),
				new ConstellationSegment(46974, 41037),
				new ConstellationSegment(41037, 38827),
				new ConstellationSegment(38827, 30438),
				new ConstellationSegment(46974, 47854),
				new ConstellationSegment(47854, 48002),
				new ConstellationSegment(48002, 45238),
			}),
	CASSIOPEIA(
			"Cassiopeia",
			new ConstellationSegment[] {
				new ConstellationSegment(746, 3179),
				new ConstellationSegment(3179, 4427),
				new ConstellationSegment(4427, 6686),
				new ConstellationSegment(6686, 8886),
			}),
	CENTAURUS(
			"Centaurus",
			new ConstellationSegment[] {
				new ConstellationSegment(73334, 71352),
				new ConstellationSegment(71352, 68245),
				new ConstellationSegment(68245, 68862),
				new ConstellationSegment(68862, 70090),
				new ConstellationSegment(70090, 68933),
				new ConstellationSegment(68933, 67464),
				new ConstellationSegment(67464, 67472),
				new ConstellationSegment(67464, 65936),
				new ConstellationSegment(65936, 65109),
				new ConstellationSegment(67472, 68002),
				new ConstellationSegment(68002, 68282),
				new ConstellationSegment(68282, 68245),
				new ConstellationSegment(68002, 66657),
				new ConstellationSegment(66657, 61932),
				new ConstellationSegment(61932, 68002),
				new ConstellationSegment(66657, 68702),
				new ConstellationSegment(68702, 71683),
				new ConstellationSegment(61932, 60823),
				new ConstellationSegment(60823, 59196),
				new ConstellationSegment(60823, 59449),
				new ConstellationSegment(59196, 55425),
				new ConstellationSegment(59449, 56243),
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
	CETUS(
			"Cetus",
			new ConstellationSegment[] {
				new ConstellationSegment(14135, 13954),
				new ConstellationSegment(13954, 12828),
				new ConstellationSegment(12828, 11484),
				new ConstellationSegment(11484, 10324),
				new ConstellationSegment(11484, 12093),
				new ConstellationSegment(12093, 12706),
				new ConstellationSegment(12706, 14135),
				new ConstellationSegment(12706, 12387),
				new ConstellationSegment(12387, 10826),
				new ConstellationSegment(10826, 8645),
				new ConstellationSegment(8645, 6537),
				new ConstellationSegment(6537, 5364),
				new ConstellationSegment(5364, 1562),
				new ConstellationSegment(1562, 3419),
				new ConstellationSegment(3419, 8102),
				new ConstellationSegment(8102, 8645),
			}),
	CHAMAELEON(
			"Chamaeleon",
			new ConstellationSegment[] {
				new ConstellationSegment(40702, 51839),
				new ConstellationSegment(51839, 58484),
				new ConstellationSegment(58484, 60000),
				new ConstellationSegment(60000, 52595),
				new ConstellationSegment(52595, 51839),
			}),
	CIRCINUS(
			"Circinus",
			new ConstellationSegment[] {
				new ConstellationSegment(71908, 74824),
				new ConstellationSegment(71908, 75323),
			}),
	COLUMBA(
			"Columba",
			new ConstellationSegment[] {
				new ConstellationSegment(25859, 26634),
				new ConstellationSegment(26634, 27628),
				new ConstellationSegment(27628, 28328),
				new ConstellationSegment(27628, 28199),
				new ConstellationSegment(28199, 29807),
				new ConstellationSegment(29807, 30277),
			}),
	COMA_BERENICES(
			"Coma Berenices",
			new ConstellationSegment[] {
				new ConstellationSegment(64241, 64394),
				new ConstellationSegment(64394, 60742),
			}),
	CORONA_AUSTRALIS(
			"Corona Australis",
			new ConstellationSegment[] {
				new ConstellationSegment(91875, 93174),
				new ConstellationSegment(93174, 93825),
				new ConstellationSegment(93825, 94114),
				new ConstellationSegment(94114, 94160),
				new ConstellationSegment(94160, 94005),
				new ConstellationSegment(94005, 93542),
			}),
	CORONA_BOREALIS(
			"Corona Borealis",
			new ConstellationSegment[] {
				new ConstellationSegment(76127, 75695),
				new ConstellationSegment(75695, 76267),
				new ConstellationSegment(76267, 76952),
				new ConstellationSegment(76952, 77512),
				new ConstellationSegment(77512, 78159),
				new ConstellationSegment(78159, 78493),
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
	CRATER(
			"Crater",
			new ConstellationSegment[] {
				new ConstellationSegment(53740, 54682),
				new ConstellationSegment(54682, 55705),
				new ConstellationSegment(55705, 55282),
				new ConstellationSegment(55282, 53740),
				new ConstellationSegment(55282, 55687),
				new ConstellationSegment(55687, 56633),
				new ConstellationSegment(56633, 58188),
				new ConstellationSegment(58188, 57283),
				new ConstellationSegment(57283, 55705),
			}),
	CRUX(
			"Crux",
			new ConstellationSegment[] {
				new ConstellationSegment(60718, 61084),
				new ConstellationSegment(59747, 62434),
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
	DORADO(
			"Dorado",
			new ConstellationSegment[] {
				new ConstellationSegment(26069, 27890),
				new ConstellationSegment(27890, 27100),
				new ConstellationSegment(27100, 26069),
				new ConstellationSegment(26069, 21281),
				new ConstellationSegment(21281, 19893),
				new ConstellationSegment(21281, 23693),
				new ConstellationSegment(23693, 26069),
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
	ERIDANUS(
			"Eridanus",
			new ConstellationSegment[] {
				new ConstellationSegment(7588, 9007),
				new ConstellationSegment(9007, 10602),
				new ConstellationSegment(10602, 11407),
				new ConstellationSegment(11407, 12413),
				new ConstellationSegment(12413, 12486),
				new ConstellationSegment(12486, 13847),
				new ConstellationSegment(13847, 15510),
				new ConstellationSegment(15510, 17797),
				new ConstellationSegment(17797, 17874),
				new ConstellationSegment(17874, 20042),
				new ConstellationSegment(20042, 20535),
				new ConstellationSegment(20535, 21393),
				new ConstellationSegment(21393, 17651),
				new ConstellationSegment(17651, 16611),
				new ConstellationSegment(16611, 15474),
				new ConstellationSegment(15474, 14146),
				new ConstellationSegment(14146, 12843),
				new ConstellationSegment(12843, 13701),
				new ConstellationSegment(13701, 15197),
				new ConstellationSegment(15197, 16537),
				new ConstellationSegment(16537, 17378),
				new ConstellationSegment(17378, 21444),
				new ConstellationSegment(21444, 22109),
				new ConstellationSegment(22109, 22701),
				new ConstellationSegment(22701, 23875),
				new ConstellationSegment(23875, 23972),
				new ConstellationSegment(23972, 21594),
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
	GRUS(
			"Grus",
			new ConstellationSegment[] {
				new ConstellationSegment(112122, 113638),
				new ConstellationSegment(112122, 112623),
				new ConstellationSegment(112122, 114421),
				new ConstellationSegment(112122, 109268),
				new ConstellationSegment(114421, 114131),
				new ConstellationSegment(114131, 110997),
				new ConstellationSegment(110997, 109268),
				new ConstellationSegment(109268, 109111),
				new ConstellationSegment(109111, 108085),
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
	HOROLOGIUM(
			"Horologium",
			new ConstellationSegment[] {
				new ConstellationSegment(19747, 12653),
				new ConstellationSegment(12653, 12225),
				new ConstellationSegment(12225, 12484),
				new ConstellationSegment(12484, 14240),
				new ConstellationSegment(14240, 13884),
			}),
	HYDRA(
			"Hydra",
			new ConstellationSegment[] {
				new ConstellationSegment(64962, 64166),
				new ConstellationSegment(64166, 57936),
				new ConstellationSegment(57936, 56343),
				new ConstellationSegment(56343, 52943),
				new ConstellationSegment(52943, 51069),
				new ConstellationSegment(51069, 49841),
				new ConstellationSegment(49841, 48356),
				new ConstellationSegment(48356, 46390),
				new ConstellationSegment(46390, 46509),
				new ConstellationSegment(46509, 46776),
				new ConstellationSegment(46776, 45336),
				new ConstellationSegment(45336, 43813),
				new ConstellationSegment(43813, 43234),
				new ConstellationSegment(43234, 43109),
				new ConstellationSegment(43109, 42313),
				new ConstellationSegment(42313, 42402),
				new ConstellationSegment(42402, 42799),
				new ConstellationSegment(42799, 43234),
			}),
	HYDRUS(
			"Hydrus",
			new ConstellationSegment[] {
				new ConstellationSegment(9236, 17678),
				new ConstellationSegment(17678, 2021),
				new ConstellationSegment(2021, 9236),
			}),
	INDUS(
			"Indus",
			new ConstellationSegment[] {
				new ConstellationSegment(101772, 105319),
				new ConstellationSegment(105319, 108431),
				new ConstellationSegment(108431, 103227),
				new ConstellationSegment(103227, 101772),
			}),
	LACERTA(
			"Lacerta",
			new ConstellationSegment[] {
				new ConstellationSegment(111169, 110538),
				new ConstellationSegment(110538, 110609),
				new ConstellationSegment(110609, 111022),
				new ConstellationSegment(111022, 111169),
				new ConstellationSegment(111022, 110351),
				new ConstellationSegment(110351, 111104),
				new ConstellationSegment(111104, 111944),
				new ConstellationSegment(111944, 111022),
				new ConstellationSegment(111104, 109754),
				new ConstellationSegment(109754, 109937),
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
	LEO_MINOR(
			"Leo Minor",
			new ConstellationSegment[] {
				new ConstellationSegment(53229, 51233),
				new ConstellationSegment(51233, 49593),
				new ConstellationSegment(49593, 53229),
				new ConstellationSegment(49593, 46952),
			}),
	LEPUS(
			"Lepus",
			new ConstellationSegment[] {
				new ConstellationSegment(25985, 25606),
				new ConstellationSegment(25985, 24305),
				new ConstellationSegment(24305, 23685),
				new ConstellationSegment(23685, 25606),
				new ConstellationSegment(24305, 24845),
				new ConstellationSegment(24305, 24327),
				new ConstellationSegment(25985, 27288),
				new ConstellationSegment(27288, 28103),
				new ConstellationSegment(28103, 28910),
				new ConstellationSegment(28910, 27654),
				new ConstellationSegment(27654, 27072),
			}),
	LIBRA(
			"Libra",
			new ConstellationSegment[] {
				new ConstellationSegment(74785, 76333),
				new ConstellationSegment(76333, 72622),
				new ConstellationSegment(72622, 74785),
				new ConstellationSegment(72622, 73714),
				new ConstellationSegment(76333, 76470),
				new ConstellationSegment(76470, 76600),
			}),
	LUPUS(
			"Lupus",
			new ConstellationSegment[] {
				new ConstellationSegment(75177, 77634),
				new ConstellationSegment(77634, 78384),
				new ConstellationSegment(78384, 75177),
				new ConstellationSegment(78384, 76297),
				new ConstellationSegment(76297, 75141),
				new ConstellationSegment(75141, 73273),
				new ConstellationSegment(76297, 75264),
				new ConstellationSegment(75264, 74395),
				new ConstellationSegment(74395, 78384),
				new ConstellationSegment(74395, 71860),
			}),
	LYNX(
			"Lynx",
			new ConstellationSegment[] {
				new ConstellationSegment(45860, 45688),
				new ConstellationSegment(45688, 44700),
				new ConstellationSegment(44700, 44248),
				new ConstellationSegment(44248, 41075),
				new ConstellationSegment(41075, 36145),
				new ConstellationSegment(36145, 33449),
				new ConstellationSegment(33449, 30060),
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
	MENSA(
			"Mensa",
			new ConstellationSegment[] {
				new ConstellationSegment(29271, 25918),
				new ConstellationSegment(25918, 22871),
				new ConstellationSegment(22871, 23467),
			}),
	MICROSCOPIUM(
			"Microscopium",
			new ConstellationSegment[] {
				new ConstellationSegment(102831, 103738),
				new ConstellationSegment(103738, 105140),
			}),
	MONOCEROS(
			"Monoceros",
			new ConstellationSegment[] {
				new ConstellationSegment(37447, 39863),
				new ConstellationSegment(39863, 34769),
				new ConstellationSegment(34769, 30867),
				new ConstellationSegment(30867, 29651),
				new ConstellationSegment(34769, 32578),
				new ConstellationSegment(32578, 31216),
				new ConstellationSegment(31216, 30419),
				new ConstellationSegment(31216, 31978),
				new ConstellationSegment(30419, 32578),
			}),
	MUSCA(
			"Musca",
			new ConstellationSegment[] {
				new ConstellationSegment(61585, 62322),
				new ConstellationSegment(62322, 63613),
				new ConstellationSegment(63613, 61199),
				new ConstellationSegment(61199, 61585),
				new ConstellationSegment(61585, 59929),
				new ConstellationSegment(59929, 57363),
			}),
	NORMA(
			"Norma",
			new ConstellationSegment[] {
				new ConstellationSegment(78914, 80582),
				new ConstellationSegment(80582, 80000),
				new ConstellationSegment(80000, 78639),
				new ConstellationSegment(78639, 78914),
			}),
	OCTANS(
			"Octans",
			new ConstellationSegment[] {
				new ConstellationSegment(70638, 107089),
				new ConstellationSegment(107089, 112405),
				new ConstellationSegment(112405, 70638),
			}),
	OPHIUCHUS(
			"Ophiuchus",
			new ConstellationSegment[] {
				new ConstellationSegment(86032, 83000),
				new ConstellationSegment(83000, 79593),
				new ConstellationSegment(83000, 81377),
				new ConstellationSegment(81377, 84012),
				new ConstellationSegment(84012, 86742),
				new ConstellationSegment(86742, 86032),
				new ConstellationSegment(81377, 80628),
				new ConstellationSegment(80628, 79882),
				new ConstellationSegment(79882, 79593),
				new ConstellationSegment(81377, 80894),
				new ConstellationSegment(80894, 80569),
				new ConstellationSegment(80569, 80473),
				new ConstellationSegment(84012, 84970),
				new ConstellationSegment(84970, 85423),
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
	PAVO(
			"Pavo",
			new ConstellationSegment[] {
				new ConstellationSegment(100751, 105858),
				new ConstellationSegment(105858, 102395),
				new ConstellationSegment(102395, 99240),
				new ConstellationSegment(99240, 100751),
				new ConstellationSegment(99240, 98495),
				new ConstellationSegment(99240, 91792),
				new ConstellationSegment(99240, 93015),
				new ConstellationSegment(99240, 92609),
				new ConstellationSegment(92609, 90098),
				new ConstellationSegment(90098, 88866),
				new ConstellationSegment(88866, 93015),
				new ConstellationSegment(88866, 86929),
			}),
	PEGASUS(
			"Pegasus",
			new ConstellationSegment[] {
				new ConstellationSegment(677, 113881),
				new ConstellationSegment(113881, 112158),
				new ConstellationSegment(112158, 109410),
				new ConstellationSegment(113881, 112748),
				new ConstellationSegment(112748, 112440),
				new ConstellationSegment(112440, 109176),
				new ConstellationSegment(109176, 107354),
				new ConstellationSegment(113881, 113963),
				new ConstellationSegment(113963, 1067),
				new ConstellationSegment(1067, 677),
				new ConstellationSegment(113963, 112447),
				new ConstellationSegment(112447, 112029),
				new ConstellationSegment(112029, 109427),
				new ConstellationSegment(109427, 107315),
			}),
	PERSEUS(
			"Perseus",
			new ConstellationSegment[] {
				new ConstellationSegment(15863, 17358),
				new ConstellationSegment(17358, 18532),
				new ConstellationSegment(18532, 14576),
				new ConstellationSegment(14576, 14668),
				new ConstellationSegment(14668, 14632),
				new ConstellationSegment(14632, 15863),
				new ConstellationSegment(15863, 14328),
				new ConstellationSegment(14328, 13268),
				new ConstellationSegment(13268, 13531),
				new ConstellationSegment(13531, 14328),
				new ConstellationSegment(13531, 14632),
				new ConstellationSegment(17358, 19343),
				new ConstellationSegment(19343, 19812),
				new ConstellationSegment(19812, 20070),
				new ConstellationSegment(20070, 19167),
				new ConstellationSegment(14632, 12777),
				new ConstellationSegment(12777, 8068),
				new ConstellationSegment(18532, 18614),
				new ConstellationSegment(18614, 18246),
				new ConstellationSegment(18246, 17448),
				new ConstellationSegment(14576, 14354),
				new ConstellationSegment(14354, 13254),
			}),
	PHOENIX(
			"Phoenix",
			new ConstellationSegment[] {
				new ConstellationSegment(2081, 765),
				new ConstellationSegment(765, 2072),
				new ConstellationSegment(2081, 2072),
				new ConstellationSegment(2072, 6867),
				new ConstellationSegment(2072, 5165),
				new ConstellationSegment(2072, 5348),
				new ConstellationSegment(6867, 5165),
				new ConstellationSegment(5165, 5348),
				new ConstellationSegment(5165, 8837),
				new ConstellationSegment(5165, 7083),
				new ConstellationSegment(8837, 7083),
			}),
	PICTOR(
			"Pictor",
			new ConstellationSegment[] {
				new ConstellationSegment(32607, 27530),
				new ConstellationSegment(27530, 27321),
			}),
	PISCES(
			"Pisces",
			new ConstellationSegment[] {
				new ConstellationSegment(9487, 8198),
				new ConstellationSegment(8198, 7097),
				new ConstellationSegment(7097, 5742),
				new ConstellationSegment(5742, 5586),
				new ConstellationSegment(5586, 6193),
				new ConstellationSegment(6193, 5742),
				new ConstellationSegment(9487, 8833),
				new ConstellationSegment(8833, 7884),
				new ConstellationSegment(7884, 7007),
				new ConstellationSegment(7007, 4906),
				new ConstellationSegment(4906, 3786),
				new ConstellationSegment(3786, 118268),
				new ConstellationSegment(118268, 116771),
				new ConstellationSegment(116771, 115830),
				new ConstellationSegment(115830, 114971),
				new ConstellationSegment(114971, 115738),
				new ConstellationSegment(115738, 116928),
				new ConstellationSegment(116928, 116771),
			}),
	PISCIS_AUSTRINUS(
			"Piscis Austrinus",
			new ConstellationSegment[] {
				new ConstellationSegment(113368, 111954),
				new ConstellationSegment(111954, 109285),
				new ConstellationSegment(109285, 107380),
				new ConstellationSegment(109285, 107608),
				new ConstellationSegment(107380, 107608),
				new ConstellationSegment(109285, 111188),
				new ConstellationSegment(111188, 113246),
				new ConstellationSegment(113246, 113368),
			}),
	PUPPIS(
			"Puppis",
			new ConstellationSegment[] {
				new ConstellationSegment(39953, 39429),
				new ConstellationSegment(39429, 39757),
				new ConstellationSegment(39757, 38170),
				new ConstellationSegment(38170, 38070),
				new ConstellationSegment(38070, 37677),
				new ConstellationSegment(37677, 36917),
				new ConstellationSegment(36917, 37229),
				new ConstellationSegment(37229, 38170),
				new ConstellationSegment(36917, 35264),
				new ConstellationSegment(35264, 31685),
				new ConstellationSegment(31685, 30438),
			}),
	PYXIS(
			"Pyxis",
			new ConstellationSegment[] {
				new ConstellationSegment(43409, 42828),
				new ConstellationSegment(42828, 42515),
			}),
	RETICULUM(
			"Reticulum",
			new ConstellationSegment[] {
				new ConstellationSegment(19780, 17440),
				new ConstellationSegment(17440, 18597),
				new ConstellationSegment(18597, 19921),
				new ConstellationSegment(19921, 19780),
			}),
	SAGITTA(
			"Sagitta",
			new ConstellationSegment[] {
				new ConstellationSegment(98920, 98337),
				new ConstellationSegment(98337, 97365),
				new ConstellationSegment(97365, 96757),
				new ConstellationSegment(97365, 96837),
			}),
	SAGITTARIUS(
			"Sagittarius",
			new ConstellationSegment[] {
				new ConstellationSegment(88635, 90185),
				new ConstellationSegment(90185, 89931),
				new ConstellationSegment(89931, 88635),
				new ConstellationSegment(89931, 90496),
				new ConstellationSegment(90496, 92041),
				new ConstellationSegment(92041, 89931),
				new ConstellationSegment(92041, 93506),
				new ConstellationSegment(92041, 92855),
				new ConstellationSegment(93506, 90185),
				new ConstellationSegment(93506, 93864),
				new ConstellationSegment(93864, 92855),
				new ConstellationSegment(92855, 93085),
				new ConstellationSegment(93085, 93683),
				new ConstellationSegment(93683, 94820),
				new ConstellationSegment(94820, 95168),
				new ConstellationSegment(93864, 96406),
				new ConstellationSegment(96406, 98688),
				new ConstellationSegment(98688, 98412),
				new ConstellationSegment(98412, 98032),
				new ConstellationSegment(98032, 95347),
				new ConstellationSegment(98032, 95294),
				new ConstellationSegment(90185, 89642),
				new ConstellationSegment(88635, 87072),
				new ConstellationSegment(90496, 89341),
			}),
	SERPENS(
			"Serpens",
			new ConstellationSegment[] {
				new ConstellationSegment(76852, 77450),
				new ConstellationSegment(77450, 78072),
				new ConstellationSegment(78072, 77233),
				new ConstellationSegment(77233, 76852),
				new ConstellationSegment(77233, 76276),
				new ConstellationSegment(76276, 77070),
				new ConstellationSegment(77070, 77622),
				new ConstellationSegment(77622, 77516),
				new ConstellationSegment(77516, 79195),
				new ConstellationSegment(84880, 86263),
				new ConstellationSegment(86263, 86565),
				new ConstellationSegment(86565, 89962),
				new ConstellationSegment(89962, 92946),
			}),
	SEXTANS(
			"Sextans",
			new ConstellationSegment[] {
				new ConstellationSegment(51362, 51437),
				new ConstellationSegment(51437, 49641),
				new ConstellationSegment(49641, 48437),
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
	SCULPTOR(
			"Sculptor",
			new ConstellationSegment[] {
				new ConstellationSegment(4577, 117452),
				new ConstellationSegment(117452, 115102),
				new ConstellationSegment(115102, 116231),
			}),
	SCUTUM(
			"Scutum",
			new ConstellationSegment[] {
				new ConstellationSegment(91117, 92175),
				new ConstellationSegment(92175, 91726),
				new ConstellationSegment(91726, 90595),
				new ConstellationSegment(90595, 91117),
			}),
	TAURUS(
			"Taurus",
			new ConstellationSegment[] {
				new ConstellationSegment(21421, 26451),
				new ConstellationSegment(21421, 20889),
				new ConstellationSegment(20889, 21881),
				new ConstellationSegment(21881, 25428),
				new ConstellationSegment(20889, 20455),
				new ConstellationSegment(20455, 20205),
				new ConstellationSegment(20205, 20894),
				new ConstellationSegment(20894, 21421),
				new ConstellationSegment(20205, 18724),
				new ConstellationSegment(18724, 16083),
				new ConstellationSegment(16083, 15900),
				new ConstellationSegment(16083, 18907),
				new ConstellationSegment(15900, 16852),
			}),
	TELESCOPIUM(
			"Telescopium",
			new ConstellationSegment[] {
				new ConstellationSegment(90422, 90568)
			}),
	TRIANGULUM(
			"Triangulum",
			new ConstellationSegment[] {
				new ConstellationSegment(10064, 8796),
				new ConstellationSegment(8796, 10559),
				new ConstellationSegment(10559, 10064),
			}),
	TRIANGULUM_AUSTRALE(
			"Triangulum Australe",
			new ConstellationSegment[] {
				new ConstellationSegment(82273, 77952),
				new ConstellationSegment(77952, 74946),
				new ConstellationSegment(74946, 82273),
			}),
	TUCANA(
			"Tucana",
			new ConstellationSegment[] {
				new ConstellationSegment(2484, 1599),
				new ConstellationSegment(1599, 118322),
				new ConstellationSegment(118322, 110838),
				new ConstellationSegment(110838, 110130),
				new ConstellationSegment(110130, 114996),
				new ConstellationSegment(114996, 2484),
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
	VELA(
			"Vela",
			new ConstellationSegment[] {
				new ConstellationSegment(39953, 44816),
				new ConstellationSegment(44816, 46651),
				new ConstellationSegment(46651, 50191),
				new ConstellationSegment(50191, 52727),
				new ConstellationSegment(52727, 48774),
				new ConstellationSegment(48774, 45941),
				new ConstellationSegment(45941, 42913),
				new ConstellationSegment(42913, 39953),
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
	VOLANS(
			"Volans",
			new ConstellationSegment[] {
				new ConstellationSegment(39794, 41312),
				new ConstellationSegment(39794, 44382),
				new ConstellationSegment(41312, 44382),
				new ConstellationSegment(39794, 34481),
				new ConstellationSegment(39794, 37504),
				new ConstellationSegment(34481, 37504),
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
