package kr.ac.kaist.ks.jobmatching.application;

import java.util.ArrayList;

import kr.ac.kaist.ks.jobmatching.domain.model.req.Req;
import kr.ac.kaist.ks.jobmatching.domain.model.user.User;
import kr.ac.kaist.ks.jobmatching.infrastructure.repository.MatchingTable;

public class SQLModifyService {
	private User user = null;
	private Req req = null;
	private String originalReq = null;
	private String modifiedReq = null;
	
/*	public String originalQueryGenerate(Req req){
		
		originalReq = "select * from test1.comp where busiSize = '" + req.getBusiSize() + "' and jobsNm like '%" + req.getJobsNm() +"%'";
		
		return originalReq;
	}*/
	
	public String apply(Req req){
		
		String jobsNm1 = null;
		String jobsNm2 = null;
		
		

		
		String prefJobID= req.getJobsNm();
		
		MatchingTable mt = new  MatchingTable();
		
		int threshold =35;
		ArrayList<String> result = mt.getSimilarJob(prefJobID, threshold);
		
		for (int i = 0 ;i<5; i++) {
			System.out.println(result.get(i));
		}
		
		
		String[] ids = new String[result.size()];
		
		for(int i=0;i<result.size();i++){
			String[] temp = result.get(i).split("\t");
			ids[i] = temp[2];
			
		}
					
		modifiedReq = "select * from test1.comp where  (jobsNm like '%" + req.getJobsNm() +"%' or jobsNm like '%" + ids[0] +"%'"+
		" or jobsNm like '%" + ids[1] +"%'"+" or jobsNm like '%" + ids[2] +"%'"+ " or jobsNm like '%" + ids[3] +"%'"+ " or jobsNm like '%" +  ids[4] + "%')";

		
		/*modifiedReq = "select * from test1.comp where busiSize = '" + req.getBusiSize() + "' and (jobsNm like '%" + req.getJobsNm() +"%' or jobsNm like '%" + jobsNm1 +"%'"
				+ " or jobsNm like '%" + jobsNm2 + "%')";*/
		
		
 /*
		 if(req.getJobsNm().equals("68205")){
			jobsNm1 = "131903";
			jobsNm2 = "156101";
		}

		modifiedReq = "select * from test1.comp where  (jobsNm like '%" + req.getJobsNm() +"%' or jobsNm like '%" + jobsNm1 +"%'"+ " or jobsNm like '%" + jobsNm2 + "%')";
	*/			
				
		
		
		return modifiedReq;	
	}
}
