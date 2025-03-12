package dex.sdk.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SdkUtil {
	public static Connection getConnection() {
		final String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		final String userName = "KH14_SDK";
		final String password = "KH1234";	
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;		
	}
}
