package vooga.engine.resource;

import java.io.File;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/**
 * This class handles storing and retrieving a high score list of arbitrary
 * length in an SQLite database. It supports name, score, and time columns.
 * 
 * @author David G. Herzka
 * 
 */
public class HighScoreHandler {
	private SQLiteConnection db;
	private int maxScores;
	private Long[] scores;
	private String[] names;
	private Long[] times;
	private String tableName;
	private File dbFile;

	/**
	 * @param maxScores
	 *            The maximum number of high scores stored.
	 * @param tableName
	 *            Name of the SQLite table in which values will be stored.
	 * @param dbFile
	 *            A File object of the SQLite database file.
	 */
	public HighScoreHandler(int maxScores, String tableName, File dbFile) {
		this.maxScores = maxScores;
		this.tableName = tableName;
		this.dbFile = dbFile;
		scores = new Long[maxScores];
		names = new String[maxScores];
		times = new Long[maxScores];
		try {
			initSQL();
			populateLists();
		} catch (SQLiteException e) {
		}
	}

	/**
	 * @param maxScores
	 *            The maximum number of high scores stored.
	 * @param tableName
	 *            Name of the SQLite table in which values will be stored.
	 * @param dbFileName
	 *            The file path of the SQLite database file.
	 */
	public HighScoreHandler(int maxScores, String tableName, String dbFileName) {
		this(maxScores, tableName, new File(dbFileName));
	}

	private void removeIndex(int index) throws SQLiteException {
		String q = String.format("DELETE FROM %s WHERE ind = %d", tableName,
				index);
		SQLiteStatement st = db.prepare(q);
		st.step();
	}

	private void initSQL() throws SQLiteException {
		db = new SQLiteConnection(dbFile);
		db.open(true);
		SQLiteStatement st = db.prepare("CREATE TABLE " + tableName
				+ "(ind INTEGER PRIMARY KEY, PLAYER, SCORE, TIME)");
		st.step();
		st.dispose();
		for (int j = 0; j < maxScores; j++)
			addEntry("anon", 0l, 0l);

	}

	private void addEntry(String name, Long score, Long time)
			throws SQLiteException {
		String q = "INSERT INTO " + tableName + "(PLAYER,SCORE,TIME) values(\""
				+ name + "\"," + score.toString() + "," + time.toString() + ")";
		SQLiteStatement st = db.prepare(q);
		st.step();
		st.dispose();
	}

	private SQLiteStatement getLowestScore() throws SQLiteException {
		SQLiteStatement st = db.prepare("SELECT * FROM " + tableName
				+ " ORDER BY score ASC LIMIT 1");
		st.step();
		return st;
	}

	/**
	 * If the input score belongs on list, adds a new score entry and drops the
	 * prior lowest score.
	 * 
	 * @param name
	 *            Player name for high score list.
	 * @param score
	 *            Long representing entry score.
	 * @param time
	 *            Time at which score was set (typically milliseconds since
	 *            midnight, January 1, 1970 UTC).
	 */
	public void updateScores(String name, Long score, Long time)
			throws SQLiteException {
		SQLiteStatement i = getLowestScore();
		long lowScore = i.columnLong(2);
		int lowScoreKey = i.columnInt(0);
		i.dispose();
		if (score >= lowScore) {
			removeIndex(lowScoreKey);
			addEntry(name, score, time);
		}
		populateLists();
	}

	/**
	 * If the input score belongs on list, adds a new score entry and drops the
	 * prior lowest score.
	 * 
	 * @param name
	 *            Player name for high score list.
	 * @param score
	 *            Long representing entry score.
	 */
	public void updateScores(String name, Long score) throws SQLiteException {
		updateScores(name, score, 0l);
	}

	/**
	 * If the input score belongs on list, adds a new score entry and drops the
	 * prior lowest score.
	 * 
	 * @param score
	 *            Long representing entry score.
	 */
	public void updateScores(Long score) throws SQLiteException {
		updateScores("anon", score, 0l);
	}

	/**
	 * If the input score belongs on list, adds a new score entry and drops the
	 * prior lowest score.
	 * 
	 * @param score
	 *            Long representing entry score.
	 * @param time
	 *            Time at which score was set (typically milliseconds since
	 *            midnight, January 1, 1970 UTC).
	 */
	public void updateScores(long score, long time) throws SQLiteException {
		updateScores("anon", score, time);
	}

	private void populateLists() throws SQLiteException {
		SQLiteStatement st = db.prepare("SELECT * FROM " + tableName
				+ " ORDER BY score DESC");
		for (int j = 0; j < maxScores; j++) {
			st.step();
			times[j] = st.columnLong(3);
			scores[j] = st.columnLong(2);
			names[j] = st.columnString(1);
		}
		st.dispose();
	}

	/**
	 * Returns the score column of the high score list in order from high to
	 * low.
	 */
	public Long[] getScores() {
		return scores;
	}

	/**
	 * Returns the name column of the high score list in order from highest to
	 * lowest score.
	 */
	public String[] getNames() {
		return names;
	}

	/**
	 * Returns the time column of the high score list in order from highest to
	 * lowest score.
	 */
	public Long[] getTimes() {
		return times;
	}

}
