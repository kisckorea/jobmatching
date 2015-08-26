package kr.ac.kaist.ks.jobmatching.domain.model.req;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.kaist.ks.jobmatching.infrastructure.repository.JDBCConnection;

public class ReqRepository extends JDBCConnection {
	public Req getAllBy(String userID) throws ClassNotFoundException {
		Req req = null;
		try {
			Connection con = getConnection();
			java.sql.Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from test1.req where acceptno='"+userID+"'");
			
			while (rs.next()) {
				String acceptno = rs.getString("acceptno");
				String busiSize = rs.getString("busiSize");
				String jobsNm = rs.getString("jobsNm");
				String workRegion = rs.getString("workRegion");
				
				req = new Req(acceptno, busiSize, jobsNm, workRegion);
			}
			rs.close();
			st.close();
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return req;
	}
}
