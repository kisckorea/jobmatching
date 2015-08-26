package kr.ac.kaist.ks.jobmatching.domain.model.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import kr.ac.kaist.ks.jobmatching.infrastructure.repository.JDBCConnection;

public class CompanyRepository extends JDBCConnection {

	public Set<Company> getAllBy(String sql) throws ClassNotFoundException {
		Set<Company> result = new HashSet<Company>();
		try {
			
			Connection con = getConnection();
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
					
			while (rs.next()) {
				String wantedAuthNo = rs.getString("wantedAuthNo");
				String busiSize = rs.getString("busiSize");
				String jobsNm = rs.getString("jobsNm");
				String salTpNm = rs.getString("salTpNm");
				String enterTpNm = rs.getString("enterTpNm");
				String eduNm = rs.getString("eduNm");
				String workRegion = rs.getString("workRegion");
				Company company = new Company(wantedAuthNo, busiSize, jobsNm, salTpNm, enterTpNm, eduNm, workRegion);
				result.add(company);
			}
			rs.close();
			st.close();
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		return result;
	}
}
