package course.cloud.computing.rest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.derby.iapi.sql.Statement;

public class ClickDatabase {

	private static AtomicBoolean init = new AtomicBoolean(false);
	private static Connection conn = null;
	private PreparedStatement statement = null;
	private PreparedStatement lookup;

	private Set<String> getDBTables(Connection targetDBConn)
			throws SQLException {
		Set<String> set = new HashSet<String>();
		DatabaseMetaData dbmeta = targetDBConn.getMetaData();
		readDBTable(set, dbmeta, "TABLE", null);
		return set;
	}

	private void readDBTable(Set<String> set, DatabaseMetaData dbmeta,
			String searchCriteria, String schema) throws SQLException {
		ResultSet rs = dbmeta.getTables(null, schema, null,
				new String[] { searchCriteria });
		while (rs.next()) {
			set.add(rs.getString("TABLE_NAME").toLowerCase());
		}
	}

	public static ClickDatabase getDatabase() {
		ClickDatabase clickdb = new ClickDatabase();
		try {
			clickdb.initialize();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clickdb;
	}

	private void initialize() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		boolean setDB = init.compareAndSet(false, true);
		if (setDB) {
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			Class.forName(driver).newInstance();
			String protocol = "jdbc:derby:";
			Properties props = new Properties();
			conn = DriverManager.getConnection(
					protocol + "clickDB;create=true", props);
			if (conn != null) {
				System.out.println("Created the connection");
			}
			if (!getDBTables(conn).contains("clicks")) {
				boolean results = conn
						.createStatement()
						.execute(
								"Create table clicks (URL VARCHAR(256) NOT NULL, timestamp BIGINT NOT NULL)");

				if (results) {
					System.out
							.println("Tables were created");
				}
			}
		}
	}

	public boolean reportLike(String URL) throws SQLException {
		if (statement == null) {
			statement = conn
					.prepareStatement("insert into clicks values (?, ?)");
		}
		statement.setString(1, URL);
		statement.setLong(2, System.currentTimeMillis());
		statement.execute();
		return true;
	}

	public long getCount(String URL) throws SQLException {
		if (lookup == null) {
			lookup = conn
					.prepareStatement("select count(*) from clicks where URL=?");
		}
		lookup.setString(1, URL);
		ResultSet set = lookup.executeQuery();
		long count = 0;
		if (set.next()) {
			count = set.getLong(1);
		}
		return count;
	}

}
