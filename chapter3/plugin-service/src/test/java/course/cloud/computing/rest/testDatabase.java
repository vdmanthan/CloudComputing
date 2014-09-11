package course.cloud.computing.rest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class testDatabase {

	@Test
	public void checkInsertAndCount() {
		ClickDatabase db = ClickDatabase.getDatabase();
		try {
			String URL = "http://www.cnn.com/";
			long count = db.getCount(URL);
			int iterations = (int) (Math.random() * 100) + 5;
			for (int i = 0; i < iterations; i++) {
				boolean value = db.reportLike(URL);
				System.out.println(value);
			}
			assertEquals(db.getCount(URL), count+iterations);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
