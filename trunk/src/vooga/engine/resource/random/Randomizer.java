package vooga.engine.resource.random;

import java.util.*;


/**
 * Manages random number generation for the game using a primary random number
 * generator. The majority if not all of the game functions should use the
 * primary random number generator in order to facilitate reproducibility and
 * replayability for game sessions. The available random value are: integers
 * uniformly distributed across the full range integers uniformly distributed
 * across a range of 0 to some maximum value integers uniformly distributed
 * across a range with a minimum and maximum value longs uniformly distributed
 * across the full range longs uniformly distributed across a range of 0 to some
 * maximum value longs uniformly distributed across a range with a minimum and
 * maximum value doubles uniformly distributed across from 0 inclusive to 1
 * exclusive doubles uniformly distributed across a range of 0 inclusive to some
 * maximum value exclusive doubles uniformly distributed across a range with a
 * minimum inclusive and maximum exclusive value floats uniformly distributed
 * across from 0 inclusive to 1 exclusive floats uniformly distributed across a
 * range of 0 inclusive to some maximum value exclusive floats uniformly
 * distributed across a range with a minimum inclusive and maximum exclusive
 * value booleans doubles in a standardized Gaussian distribution
 * 
 * Functionality is included for retrieving use of past game seeds in order to
 * allow for recreation of past random variable usage. Seeds can either be
 * retrieved based on unique string ids or using a chronological list of used
 * seeds which also includes unnamed seeds and the number of times each seed was
 * used. The most basic seed reuse functionality of starting over using the
 * current seed is also available.
 * 
 * If at any point random number generation is required but the use of the
 * primary number generator would compromise the replayability, secondary random
 * number generators are available. For instance, particle effects based on
 * random movement might require random number generation, but the exact
 * replication of the movement of the particles is not required for
 * replayability. Furthermore, using the primary number generator could cause
 * potential problems in off-setting future random number values if somehow the
 * particle requires the use of one more random number in a replay. To avoid
 * this issue, one could create implement a secondary random number generator,
 * called a random path, which is referred to by a unique string id. Then, in
 * this example, particle movement could use that USID to create a sequence of
 * random variables on a the new random path without affecting the primary
 * random number generator. These random paths do not have the same retrieval
 * functionality as the primary generator.
 * 
 * The random number generation is based on the java.util.Random class.
 * 
 * @author Daniel Koverman
 * @date September 26, 2010
 * 
 */
public class Randomizer {

	// The seed value the Randomizer will begin with
	private final static long DEFAULT_SEED = 49234721;

	// Main random number generator used for the game
	private static Random generator = new Random(DEFAULT_SEED);

	// Mapping of used seeds to String identifiers for later look up and reuse
	private static Map<String, Long> seeds = new HashMap<String, Long>();

	// List of old seeds in chronological order, including those without string
	// identifiers, along with the number of times the generators were used, for
	// later look up and use
	private static List<SeedStatus> usedSeeds = new ArrayList<SeedStatus>();

	// The current beginning seed value and number of times the generator has
	// been used
	// since the last time the seed was set
	private static SeedStatus currSeedStat = new SeedStatus(DEFAULT_SEED);

	// Mapping of String identifiers to random paths kept seperate from the main
	// random number generator
	private static Map<String, Random> randPaths = new HashMap<String, Random>();

	/**
	 * Create a new random path/secondary random number generator referenced by
	 * a unique string id and seeded with a value based on the current system
	 * time.
	 * 
	 * @param pathID
	 *            the unique string identification of the random path
	 */
	public static void createRandomPath(String pathID) {
		createRandomPath(pathID, System.currentTimeMillis());
	}

	/**
	 * Create a new random path/secondary random number generator referenced by
	 * a unique string id and seeded with a given value
	 * 
	 * @param pathID
	 *            the unique string identification of the random path
	 * @param seed
	 *            the value to seed the random number generator with
	 */
	public static void createRandomPath(String pathID, Long seed) {
		Random newGen = new Random(seed);
		randPaths.put(pathID, newGen);
	}

	/**
	 * Returns a value to be used for seeding based on the current system time.
	 * Note that this value is not assigned to any random number generator by
	 * this method
	 * 
	 * @return a seed value for use in a random number generator
	 */
	public static long generateSeed() {
		return System.currentTimeMillis();
	}

	/**
	 * Set the seed of the primary random number generator to the given seed
	 * value. This value will not be retrievable by any name.
	 * 
	 * @param seed
	 *            the value to seed the primary number generator
	 */
	public static void setSeed(long seed) {
		setSeed(seed, null, false);
	}

	/**
	 * Set the seed of the primary random number generator to some seed value
	 * which will be retrievable using a unique string identification for the
	 * seed. If the seed USID is already in use, no change will be made.
	 * 
	 * @param seedUSID
	 *            unique string ID for the seed
	 */
	public static void setSeed(String seedUSID) {
		setSeed(generateSeed(), seedUSID, false);
	}

	/**
	 * Set the seed of the primary random number generator to some seed value
	 * which will be retrievable using a unique string identification for the
	 * seed. If the seed USID is already in use, no change will be made unless
	 * overwrite is set to true in which case the former value will be
	 * overwritten the generator will be set.
	 * 
	 * @param seedUSID
	 *            unique string ID for the seed
	 */
	public static void setSeed(String seedUSID, boolean overwrite) {
		setSeed(generateSeed(), seedUSID, false);
	}

	/**
	 * Set the seed of the primary random number generator to the given seed
	 * value which will be retrievable using a unique string identification for
	 * the seed. If the seed USID is already in use, no change will be made.
	 * 
	 * @param seed
	 *            the value to seed the primary number generator
	 * @param seedUSID
	 *            unique string ID for the seed
	 */
	public static void setSeed(long seed, String seedUSID) {
		setSeed(seed, seedUSID, false);
	}

	/**
	 * Set the seed of the primary random number generator to the given seed
	 * value which will be retrievable using a unique string identification for
	 * the seed. If the seed USID is already in use, no change will be made
	 * unless overwrite is set to true in which case the former value will be
	 * overwritten the generator will be set.
	 * 
	 * @param seed
	 *            the value to seed the primary number generator
	 * @param seedUSID
	 *            unique string ID for the seed
	 */
	public static void setSeed(long seed, String seedUSID, boolean overwrite) {
		if (overwrite || !seeds.containsKey(seedUSID)) {
			if (seedUSID != null) {
				seeds.put(seedUSID, seed);
			}
			currSeedStat = new SeedStatus(seed, seedUSID);
			usedSeeds.add(currSeedStat);
			generator.setSeed(seed);
		}
	}

	/**
	 * Reset the primary random number generator to start over using its current
	 * seed value which allows replication of the sequence of random numbers
	 * used since the last time the seed was changed.
	 */
	public static void resetSeed() {
		resetSeed(null);
	}

	/**
	 * Reset the primary random number generator to start over using its the
	 * seed value corresponding to a unique string id which allows replication
	 * of the sequence of random numbers from that seed.
	 * 
	 * @param seedUSID
	 *            the unique string id of the seed to set the generator to
	 */
	public static void resetSeed(String seedUSID) {
		if (seedUSID == null) {
			generator.setSeed(currSeedStat.getValue());
			currSeedStat.resetTimesUsed();
		}
		if (seeds.containsKey(seedUSID)) {
			long newSeed = seeds.get(seedUSID);
			generator.setSeed(newSeed);
			currSeedStat = new SeedStatus(newSeed, seedUSID);
			usedSeeds.add(currSeedStat);
		}
		usedSeeds.add(currSeedStat);
	}

	/**
	 * Return the USIDs of all the seeds used this game in chronological order.
	 * This list can be analyzed to determine where the seed might be set for a
	 * replay.
	 * 
	 * @return a List of the USID of the seeds used this session in
	 *         chronological order
	 */
	public static List<String> getUsedSeedNames() {
		List<String> names = new ArrayList<String>();
		for (SeedStatus status : usedSeeds) {
			names.add(status.getName());
		}
		return names;
	}

	/**
	 * Return the last used seed values seeded to the primary random number
	 * generator in chronological order. This list can be analyzed to determine
	 * where the seed might be set for a replay.
	 * 
	 * @return a list of the used seed values for this session in chronological
	 *         order
	 */
	public static List<Long> getUsedSeedVals() {
		List<Long> vals = new ArrayList<Long>();
		for (SeedStatus status : usedSeeds) {
			vals.add(status.getValue());
		}
		return vals;
	}

	/**
	 * Returns a comma separated value String of the last used seed values and
	 * the corresponding unique string IDs of those seeds in chronological
	 * order. The first value is the seeds USID, then a comma, then the
	 * corresponding seed value. This pair is then followed by a newline
	 * character.
	 * 
	 * @return
	 */
	public static String getUsedSeedsCSV() {
		StringBuilder csv = new StringBuilder();
		for (SeedStatus status : usedSeeds) {
			csv.append(status.getName() + ',' + status.getValue() + '\n');
		}
		csv.deleteCharAt(csv.length() - 1);
		return csv.toString();
	}

	/*
	 * This method would be really useful if arrays of primitives were
	 * automatically converted their wrapper classes instead of requiring
	 * wrapping element by element. I'm leaving it here until I am sure that is
	 * useless.
	 */
	// private static Number next(Random gen, Number... numbers) {
	// if (numbers.length == 1) {
	// Number max = numbers[0];
	// if (max instanceof Integer) {
	// return gen.nextInt((Integer) max);
	// } else if (max instanceof Long) {
	// return gen.nextLong() % (Long) max;
	// } else if (max instanceof Double) {
	// return gen.nextDouble() * (Double) max;
	// } else if (max instanceof Float) {
	// return gen.nextFloat() * (Float) max;
	// } else {
	// // Throw Exception
	// }
	// } else if (numbers.length == 2) {
	// Number min = numbers[0];
	// Number max = numbers[1];
	// if (min.getClass() != max.getClass()) {
	// // Throw Exception
	// } else if (max instanceof Integer) {
	// return gen.nextInt((Integer) max - (Integer) min)
	// + (Integer) min;
	// } else if (max instanceof Long) {
	// return gen.nextLong() % ((Long) max - (Long) min) + (Long) min;
	// } else if (max instanceof Double) {
	// return gen.nextDouble() * ((Double) max - (Double) min)
	// + (Double) min;
	// } else if (max instanceof Float) {
	// return gen.nextFloat() * ((Float) max - (Float) min)
	// + (Float) min;
	// } else {
	// // Throw Exception
	// }
	// } else {
	// // Throw Exception
	// }
	// return null;
	// }

	/**
	 * Returns a random integer using the primary number generator. No arguments
	 * will return an integer from the whole range of integers. One int will
	 * return an integer between zero and that value. Two ints will return an
	 * integer between the two values. The minimum must be specified first.
	 * 
	 * @param range
	 *            leave blank for random int, or specify a maximum, or specify a
	 *            range
	 * @return random integer
	 * @throws RandomizerException
	 */
	public static int nextInt(Integer... range) throws RandomizerException {
		currSeedStat.incTimesUsed();
		return genInt(generator, range);
	}

	/**
	 * Returns a random long using the primary number generator. No arguments
	 * will return a long from the whole range of longs. One long will return a
	 * long between zero and that value. Two longs will return a long between
	 * the two values. The minimum must be specified first.
	 * 
	 * @param range
	 *            leave blank for random long, or specify a maximum, or specify
	 *            a range
	 * @return random long
	 * @throws RandomizerException
	 */
	public static long nextLong(long... range) throws RandomizerException {
		currSeedStat.incTimesUsed();
		return genLong(generator, range);
	}

	/**
	 * Returns a random double using the primary number generator. No arguments
	 * will return a double from [0,1]. One double will return a double from [0,
	 * max) . Two doubles will return a double from [min, max). The minimum must
	 * be specified first.
	 * 
	 * @param range
	 *            leave blank for random double, or specify a maximum, or
	 *            specify a range
	 * @return random double
	 * @throws RandomizerException
	 */
	public static double nextDouble(double... range) throws RandomizerException {
		currSeedStat.incTimesUsed();
		return genDouble(generator, range);

	}

	/**
	 * Returns a random float using the primary number generator. No arguments
	 * will return a float from [0,1]. One float will return a float from [0,
	 * max) . Two floats will return a float from [min, max). The minimum must
	 * be specified first.
	 * 
	 * @param range
	 *            leave blank for random float, or specify a maximum, or specify
	 *            a range
	 * @return random float
	 * @throws RandomizerException
	 */
	public static float nextFloat(float... range) throws RandomizerException {
		currSeedStat.incTimesUsed();
		return genFloat(generator, range);
	}

	/**
	 * Returns a random boolean using the primary number generator.
	 * 
	 * @return random true or false value
	 */
	public static boolean nextBoolean() {
		currSeedStat.incTimesUsed();
		return genBoolean(generator);
	}

	/**
	 * Returns a random double from a standardized Gaussian distribution using
	 * the primary random number generator.
	 * 
	 * @return double from Gaussian distribution
	 */
	public static double nextGaussian() {
		currSeedStat.incTimesUsed();
		return genGaussian(generator);
	}

	/**
	 * Returns a random integer using a specified random path. No additional
	 * arguments will return an integer from the whole range of integers. One
	 * int will return an integer between zero and that value. Two ints will
	 * return an integer between the two values. The minimum must be specified
	 * first.
	 * 
	 * @param pathID
	 *            the unique string id of the desired random path
	 * @param range
	 *            leave blank for random int, or specify a maximum, or specify a
	 *            range
	 * @return random integer
	 * @throws RandomizerException
	 */
	public static int nextInt(String pathID, Integer... range)
			throws RandomizerException {
		return genInt(randPaths.get(pathID), range);
	}

	/**
	 * Returns a random long using a specified random path. No additional
	 * arguments will return a long from the whole range of longs. One long will
	 * return a long between zero and that value. Two longs will return a long
	 * between the two values. The minimum must be specified first.
	 * 
	 * @param pathID
	 *            the unique string id of the desired random path
	 * @param range
	 *            leave blank for random long, or specify a maximum, or specify
	 *            a range
	 * @return random long
	 * @throws RandomizerException
	 */
	public static long nextLong(String pathID, long... range)
			throws RandomizerException {
		return genLong(randPaths.get(pathID), range);
	}

	/**
	 * Returns a random double using a specified random path. No additional
	 * arguments will return a double from [0,1]. One double will return a
	 * double from [0, max) . Two doubles will return a double from [min, max).
	 * The minimum must be specified first.
	 * 
	 * @param pathID
	 *            the unique string id of the desired random path
	 * @param range
	 *            leave blank for random double, or specify a maximum, or
	 *            specify a range
	 * @return random double
	 * @throws RandomizerException
	 */
	public static double nextDouble(String pathID, double... range)
			throws RandomizerException {
		return genDouble(randPaths.get(pathID), range);
	}

	/**
	 * Returns a random float using a specified random path. No additional
	 * arguments will return a float from [0,1]. One float will return a float
	 * from [0, max) . Two floats will return a float from [min, max). The
	 * minimum must be specified first.
	 * 
	 * @param pathID
	 *            the unique string id of the desired random path
	 * @param range
	 *            leave blank for random float, or specify a maximum, or specify
	 *            a range
	 * @return random float
	 * @throws RandomizerException
	 */
	public static float nextFloat(String pathID, float... range)
			throws RandomizerException {
		return genFloat(randPaths.get(pathID), range);
	}

	/**
	 * Returns a random boolean using the a specified random path
	 * 
	 * @param pathID
	 *            the unique string id of the desired random path
	 * @return random true or false value
	 */
	public static boolean nextBoolean(String pathID) {
		return genBoolean(randPaths.get(pathID));
	}

	/**
	 * Returns a random double from a standardized Gaussian distribution using a
	 * specified random path.
	 * 
	 * @param pathID
	 *            the unique string id of the desired random path
	 * @return double from Gaussian distribution
	 */
	public static double nextGaussian(String pathID) {
		return genGaussian(randPaths.get(pathID));
	}

	/*
	 * The following methods are used internally to parse the variable
	 * parameters of the public random number generator methods and return the
	 * random value from the desired range
	 */
	private static int genInt(Random gen, Integer... range)
			throws RandomizerException {
		currSeedStat.incTimesUsed();
		if (range.length == 0) {
			return gen.nextInt();
		} else if (range.length == 1) {
			return gen.nextInt(range[0]);
		} else if (range.length == 2) {
			return gen.nextInt(range[1] - range[0]) + range[0];
		} else {
			throw new RandomizerException("Range length error");
		}
	}

	private static long genLong(Random gen, long... range)
			throws RandomizerException {
		currSeedStat.incTimesUsed();
		if (range.length == 0) {
			return gen.nextLong();
		} else if (range.length == 1) {
			return gen.nextLong() % range[0];
		} else if (range.length == 2) {
			return gen.nextLong() % (range[1] - range[0]) + range[0];
		} else {
			throw new RandomizerException("Range length error");
		}
	}

	private static double genDouble(Random gen, double... range)
			throws RandomizerException {
		currSeedStat.incTimesUsed();
		if (range.length == 0) {
			return gen.nextDouble();
		} else if (range.length == 1) {
			return gen.nextDouble() * range[0];
		} else if (range.length == 2) {
			return gen.nextDouble() * (range[1] - range[0]) + range[0];
		} else {
			throw new RandomizerException("Range length error");
		}
	}

	private static float genFloat(Random gen, float... range)
			throws RandomizerException {
		currSeedStat.incTimesUsed();
		if (range.length == 0) {
			return gen.nextFloat();
		} else if (range.length == 1) {
			return gen.nextFloat() * range[0];
		} else if (range.length == 2) {
			return gen.nextFloat() * (range[1] - range[0]) + range[0];
		} else {
			throw new RandomizerException("Range length error");
		}
	}

	private static boolean genBoolean(Random gen) {
		return gen.nextBoolean();
	}

	private static double genGaussian(Random gen) {
		return gen.nextGaussian();
	}

}