package kr.ac.kaist.ks.jobmatching.application;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kaist.ks.jobmatching.domain.model.req.Req;
import kr.ac.kaist.ks.jobmatching.domain.model.user.User;
import kr.ac.kaist.ks.jobmatching.infrastructure.repository.MatchingTable;

public class SQLModifyService {
	private User user = null;
	private Req req = null;
	private String originalReq = null;
	private String modifiedReq = null;

	HashMap<String, ArrayList<String>> matchingTableMap = null;

	private HashMap<String, String> jobTableMap = null;

	/*
	 * public String originalQueryGenerate(Req req){
	 * 
	 * originalReq = "select * from test1.comp where busiSize = '" +
	 * req.getBusiSize() + "' and jobsNm like '%" + req.getJobsNm() +"%'";
	 * 
	 * return originalReq; }
	 */

	public String apply(Req req, MatchingTable mt) {


		String prefJobID = req.getJobsNm();
		jobTableMap = mt.getJobTableMap();
		
		System.out.println("User perfered Job: " + prefJobID + jobTableMap.get(prefJobID));

		matchingTableMap = mt.getMatchingTableMap();

		// get matched 5 jobs from Matching Tables
		ArrayList<String> result = matchingTableMap.get(prefJobID);

		System.out.println("5 alternative jobs from the Matching Table");
		for (int i = 0; i < 5; i++) {
			System.out.println(result.get(i));
		}

		String[] ids = new String[result.size()];

		for (int i = 0; i < result.size(); i++) {
			String[] temp = result.get(i).split("\t");
			ids[i] = temp[2];

		}

		modifiedReq = "select * from test1.comp where  (jobsNm like '%"
				+ req.getJobsNm() + "%' or jobsNm like '%" + ids[0] + "%'"
				+ " or jobsNm like '%" + ids[1] + "%'" + " or jobsNm like '%"
				+ ids[2] + "%'" + " or jobsNm like '%" + ids[3] + "%'"
				+ " or jobsNm like '%" + ids[4] + "%')";

		/*
		 * modifiedReq = "select * from test1.comp where busiSize = '" +
		 * req.getBusiSize() + "' and (jobsNm like '%" + req.getJobsNm()
		 * +"%' or jobsNm like '%" + jobsNm1 +"%'" + " or jobsNm like '%" +
		 * jobsNm2 + "%')";
		 */

		/*
		 * if(req.getJobsNm().equals("68205")){ jobsNm1 = "131903"; jobsNm2 =
		 * "156101"; }
		 * 
		 * modifiedReq = "select * from test1.comp where  (jobsNm like '%" +
		 * req.getJobsNm() +"%' or jobsNm like '%" + jobsNm1 +"%'"+
		 * " or jobsNm like '%" + jobsNm2 + "%')";
		 */

		return modifiedReq;
	}
}
