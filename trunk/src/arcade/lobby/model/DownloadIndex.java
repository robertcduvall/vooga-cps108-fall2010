package arcade.lobby.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DownloadIndex {

	private static Connection myDBConnection;

	public static void main(String[] args) throws FileNotFoundException, IOException, JSchException, SftpException, SQLException, InterruptedException,
			ExecutionException {
		Properties props = new Properties();
		props.load(new FileInputStream("info.properties"));
		String hostName = props.getProperty("Index_Host_Name");
		String id = props.getProperty("Index_Id");
		String password = props.getProperty("Index_Password");
		String type = props.getProperty("Index_Type");
		String indexPath = props.getProperty("Index_Root");
		String homePath = props.getProperty("Home_Root_Index");
		String homeCurPath = props.getProperty("Home_Root_Currency");
		String ICLFileLoc = props.getProperty("SANDP_INDEX_ICL_FILE_LOC");
		String SPLFileLoc = props.getProperty("SANDP_INDEX_SPL_FILE_LOC");
		String curFileLoc = props.getProperty("Currency_FILE_LOC");


		ExecutorService myThreadExecutor = Executors.newFixedThreadPool(15);

		Download d = DownloadFiles.createDownload(hostName, id, password, type);
		d.cd(indexPath);
		ArrayList<ThisFile> list = d.listFiles();
		DownloadIndex.makeDBConnection();
		Calendar[] mark = DownloadIndex.getPostDateIndex();
		Calendar now = Calendar.getInstance();
		List<DownloadThread> threadList = new ArrayList<DownloadThread>();
		List<DownloadThread> curThreadList = new ArrayList<DownloadThread>();

		for (ThisFile t : list) {
			String name = t.getName().toUpperCase();
			Calendar time = t.getModified();
			if (name.endsWith(".SPL") || name.endsWith(".ICL")) {
				if (time.after(mark[0])) {
					DownloadThread dt = new DownloadThread(hostName, id, password, type, indexPath, homePath, name);
					threadList.add(dt);
				}
			}
			
			if (name.endsWith("_SPBMI_GL_BMI_USD_C_CLS.SPC")) {
				if (time.after(mark[1])) {
					DownloadThread dt = new DownloadThread(hostName, id, password, type, indexPath, homeCurPath, name);
					curThreadList.add(dt);
				}
			}		
			
			
			
		}

		List<Future<DownloadReturn>> futures = myThreadExecutor.invokeAll(threadList);
		List<Future<DownloadReturn>> futuresCur = myThreadExecutor.invokeAll(curThreadList);

		File file = new File(SPLFileLoc);
		file.delete();
		file = new File(ICLFileLoc);
		file.delete();
		
		
		FileWriter fstreamSPL = new FileWriter(SPLFileLoc, true);
		BufferedWriter outSPL = new BufferedWriter(fstreamSPL);
		
		FileWriter fstreamICL = new FileWriter(ICLFileLoc, true);
		BufferedWriter outICL = new BufferedWriter(fstreamICL);
		

		boolean temp;
		String tempName;
		boolean finalTempI = false;
		boolean finalTempF = true;
		for (Future<DownloadReturn> future : futures) {
			temp = future.get().isLoaded();
			tempName = future.get().getName();
			if (!temp) {
				finalTempI = true;
			}
			if (temp) {
				if(tempName.endsWith(".SPL")){
					outSPL.write("SANDP_INDEX/" + tempName);
					outSPL.newLine();
				}else{
					outICL.write("SANDP_INDEX/" + tempName);
					outICL.newLine();
				}
				
				
				
				
				finalTempF = false;
			}
		}
		if (futures.size() == 0){
			finalTempF = false;
		}
		
		
		
		
		file = new File(curFileLoc);
		file.delete();
		
		
		FileWriter fstreamCur = new FileWriter(curFileLoc, true);
		BufferedWriter outCur = new BufferedWriter(fstreamCur);
		
		
		boolean finalTempCurI = false;
		boolean finalTempCurF = true;
		for (Future<DownloadReturn> future : futuresCur) {
			temp = future.get().isLoaded();
			tempName = future.get().getName();
			if (!temp) {
				finalTempCurI = true;
			}
			if (temp) {
				outCur.write(tempName);
				outCur.newLine();
				
				finalTempCurF = false;
			}
		}
		outCur.close();
		outICL.close();
		outSPL.close();
		
		if (futuresCur.size() == 0){
			finalTempF = false;
		}
		
		
		myThreadExecutor.shutdown();
		d.disconect();

		DownloadIndex.updateDB(finalTempI, finalTempF, finalTempCurI, finalTempCurF, mark, now);
		myDBConnection.commit();
		myDBConnection.close();
		// System.exit(0);

	}

	private static void makeDBConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // Or any other driver
		} catch (Exception x) {
			System.out.println("Unable to load the driver class!");
		}

		String url = "jdbc:oracle:thin:@//10.200.146.64:1526/sppwd";
		String id = "LEGIO_ETL";
		String password = "LEGIO_ETL";

		myDBConnection = DriverManager.getConnection(url, id, password);
	}

	private static Calendar[] getPostDateIndex() throws SQLException {
		Calendar[] finalCal = new Calendar[2];
		String sql = "SELECT * FROM ERS_FEED_PULL where FEED_CODE = 'IS_IX' ";
		PreparedStatement ps = myDBConnection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		String status = rs.getString("STATUS");
		if (status.equals("S")) {
			Date date = rs.getTimestamp("END_DATE");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			finalCal[0] = cal;
		} else {
			Date date = rs.getTimestamp("START_DATE");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			finalCal[0] = cal;
		}
		
		
		
		
		sql = "SELECT * FROM ERS_FEED_PULL where FEED_CODE = 'IS_CU' ";
		ps = myDBConnection.prepareStatement(sql);
		rs = ps.executeQuery();
		rs.next();
		
		status = rs.getString("STATUS");
		if (status.equals("S")) {
			Date date = rs.getTimestamp("END_DATE");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			finalCal[1] = cal;
		} else {
			Date date = rs.getTimestamp("START_DATE");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			finalCal[1] = cal;
		}

		
		
		
		return finalCal;
	}


	private static void updateDB(boolean i, boolean f, boolean ci, boolean cf, Calendar[] start, Calendar end) throws SQLException {
		Date date = start[0].getTime();
		Timestamp startDate = new java.sql.Timestamp(date.getTime());
		date = start[1].getTime();
		Timestamp curStartDate = new java.sql.Timestamp(date.getTime());
		date = end.getTime();
		Timestamp endDate = new java.sql.Timestamp(date.getTime());
		Calendar now = Calendar.getInstance();
		date = now.getTime();
		Timestamp nowDate = new java.sql.Timestamp(date.getTime());
		

		String sql = "UPDATE ERS_FEED_PULL set START_DATE =?, END_DATE=?, STATUS=?, JOB_RUN_TIME =? where FEED_CODE = 'IS_IX' ";
		PreparedStatement ps = myDBConnection.prepareStatement(sql);

		ps.setTimestamp(1, startDate);
		ps.setTimestamp(2, endDate);
		ps.setTimestamp(4, nowDate);

		if (f) {
			ps.setString(3, "F");
		} else if (i) {
			ps.setString(3, "I");

		} else {
			ps.setString(3, "S");
		}

		ps.executeUpdate();
		ps.close();
		
		
		
		
		
		sql = "UPDATE ERS_FEED_PULL set START_DATE =?, END_DATE=?, STATUS=?, JOB_RUN_TIME =? where FEED_CODE = 'IS_CU' ";
		ps = myDBConnection.prepareStatement(sql);

		ps.setTimestamp(1, curStartDate);
		ps.setTimestamp(2, endDate);
		ps.setTimestamp(4, nowDate);

		if (f) {
			ps.setString(3, "F");
		} else if (i) {
			ps.setString(3, "I");

		} else {
			ps.setString(3, "S");
		}

		ps.executeUpdate();
		ps.close();
		
		
		sql = "INSERT INTO ERS_FEED_PULL_LOG SELECT * FROM ERS_FEED_PULL";
		ps = myDBConnection.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();

		
		
	}

}
