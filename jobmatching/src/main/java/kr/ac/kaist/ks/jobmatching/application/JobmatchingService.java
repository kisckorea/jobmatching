package kr.ac.kaist.ks.jobmatching.application;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kr.ac.kaist.ks.jobmatching.domain.model.company.Company;
import kr.ac.kaist.ks.jobmatching.domain.model.company.CompanyRepository;
import kr.ac.kaist.ks.jobmatching.domain.model.req.Req;
import kr.ac.kaist.ks.jobmatching.domain.model.req.ReqRepository;
import kr.ac.kaist.ks.jobmatching.domain.model.user.User;
import kr.ac.kaist.ks.jobmatching.domain.model.user.UserRepository;
import kr.ac.kaist.ks.jobmatching.infrastructure.repository.JDBCConnection;
import kr.ac.kaist.ks.jobmatching.infrastructure.repository.MatchingTable;

public class JobmatchingService {

	private SQLModifyService sqlModifyService = new SQLModifyService();
	private CompanyRepository companyRepository = new CompanyRepository();
	private ReqRepository reqRepository = new ReqRepository();
	private UserRepository userRepository = new UserRepository();

	private HashMap<String, String> MatchingTableMap;

	public Set<Company> getMatchedCompanies(Req req, MatchingTable mt)
			throws ClassNotFoundException {
		Set<Company> company = new HashSet<Company>();
		// company = companyRepository.getAllBy(sql);
		company.addAll(companyRepository.getAllBy(sqlModifyService.apply(req,mt)));

		return company;
	}

	public Req getUserReq(User user) throws ClassNotFoundException {
		Req req = null;

		req = reqRepository.getAllBy(user.getAcceptno());

		return req;
	}

	public User getUserInfo(String userID) throws ClassNotFoundException {
		User user = null;

		user = userRepository.getAllBy(userID);

		return user;
	}

	public static void main(String[] args) throws ClassNotFoundException {

		// start
		String userID = "E140800268";

		Set<Company> company = new HashSet<Company>();

		try {
			
			MatchingTable mt = new MatchingTable();

			JobmatchingService service = new JobmatchingService();

			
			System.out.println("Job Recommendataion for user: "+userID);
			User user = service.getUserInfo(userID);
			Req req = service.getUserReq(user);
			company = service.getMatchedCompanies(req,mt);

			System.out.println("Final Recommendation Resuts (Company) : ");
			System.out.println(company.toString());

			Iterator<Company> iter = company.iterator();
			while (iter.hasNext()) {

				System.out.println("    " + iter.next());
			}

			JDBCConnection.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
