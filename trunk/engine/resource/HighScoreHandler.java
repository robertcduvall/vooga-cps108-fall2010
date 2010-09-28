package com.brackeen.javagamebook.resources;

import java.io.File;
import com.almworks.sqlite4java.*;

/**
 * This class handles storing and retrieving a high score list of arbitrary
 * length in an SQLite database. It supports name, score, and time columns.
 * 
 * @author dgh11
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
		} catch (SQLiteException e) {
			System.out
					.println("Error initializing database or database already exists.");
			e.printStackTrace();
		}

		try {
			populateLists();
		} catch (SQLiteException e) {
			e.printStackTrace();
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
		db.open(false);
		for (int j = 0; j < maxScores; j++)
			addEntry("anonymous", 0l, 0l);
	}

	private void addEntry(String name, Long score, Long time)
			throws SQLiteException {
		String q = "INSERT INTO " + tableName + "(PLAYER,SCORE,TIME) values(\""
				+ name + "\"," + score.toString() + "," + time.toString() + ")";
		System.out.println(q);
		SQLiteStatement st = db.prepare(q);
		st.step();
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
		if (score >= i.columnInt(2)) {
			removeIndex(i.columnInt(0));
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
		updateScores(name, score, null);
	}

	/**
	 * If the input score belongs on list, adds a new score entry and drops the
	 * prior lowest score.
	 * 
	 * @param score
	 *            Long representing entry score.
	 */
	public void updateScores(Long score) throws SQLiteException {
		updateScores(null, score, null);
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
