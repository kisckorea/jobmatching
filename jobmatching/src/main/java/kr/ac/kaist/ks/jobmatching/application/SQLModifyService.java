package kr.ac.kaist.ks.jobmatching.application;

import kr.ac.kaist.ks.jobmatching.domain.model.req.Req;
import kr.ac.kaist.ks.jobmatching.domain.model.user.User;

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
		
		if(req.getJobsNm().equals("68205")){
			jobsNm1 = "131903";
			jobsNm2 = "156101";
		}
		
		
		/*modifiedReq = "select * from test1.comp where busiSize = '" + req.getBusiSize() + "' and (jobsNm like '%" + req.getJobsNm() +"%' or jobsNm like '%" + jobsNm1 +"%'"
				+ " or jobsNm like '%" + jobsNm2 + "%')";*/
		
		
		modifiedReq = "select * from test1.comp where  (jobsNm like '%" + req.getJobsNm() +"%' or jobsNm like '%" + jobsNm1 +"%'"
				+ " or jobsNm like '%" + jobsNm2 + "%')";
		
		
		return modifiedReq;	
	}
}
