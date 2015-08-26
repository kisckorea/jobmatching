package kr.ac.kaist.ks.jobmatching.domain.model.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.kaist.ks.jobmatching.infrastructure.repository.JDBCConnection;

public class UserRepository extends JDBCConnection {
	
	public User getAllBy(String userID) throws ClassNotFoundException {
		User user = null;
		try {
			
			Connection con = getConnection();
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from test1.user where acceptno='"+userID+"'");
					
			while (rs.next()) {
				String acceptno = rs.getString("acceptno");
				String birthday = rs.getString("birthday");
				String sex = rs.getString("sex");
				String zipcode = rs.getString("zipcode");
				String addr = rs.getString("addr");
				user = new User(acceptno, birthday, sex, zipcode, addr);
			}
			rs.close();
			st.close();
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return user;
	}
}
