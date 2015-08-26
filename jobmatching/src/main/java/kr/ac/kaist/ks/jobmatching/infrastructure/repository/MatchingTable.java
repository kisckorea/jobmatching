package kr.ac.kaist.ks.jobmatching.infrastructure.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MatchingTable {

	String joblist[][] = new String[889][10];

	public static void main(String args[]) {

		MatchingTable mt = new MatchingTable();

		mt.init();
		mt.test();

	}

	private void init() {
		int cnt = 0;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(
							"resource\\Matching table V2.5_eng_processing_test_v5.0.csv"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// print(line);
				String[] columns = line.split(",");
				// println(columns);
				// float [] nums = float (columns);
				// print(nums);

				for (int i = 0; i < 10; i++)
					joblist[cnt][i] = columns[i];

				cnt++;
			}// while
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

	void test() {

		// size(100, 100);
		// smooth();
		

		// Read the job data from database//

		System.out
				.println(" SMART CITY  ---Job Type Matching Program Prototype:");
		System.out.println();
		System.out.println();

		System.out
				.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		// for (int i=0; i<889; i++) {
		// for (int j=0; j<10; j++)
		// System.out.print(joblist[i][j]+", ");
		// System.out.println();
		// }

		// String targetID = "235100";

		/* Read Searching ID from the Keyboard */

		int testID = 100;
		int threshold = 35;

		String targetJobPosition = joblist[testID][6];
		String searchJob = joblist[testID][8];
		String searchJobID = joblist[testID][7];

		// Print out the Title and relevance Information

		System.out
				.println("  Reference for Closeness Between Target Job and Other Jobs: (Subject to change)");
		System.out
				.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out
				.println("  0  = Distance   == 0 ------>   Perfectly_matched                  0 < Distance <= 5  ------> very Good_matched");
		System.out
				.println("  05 < Distance  <= 10 ------>   Good___matched                    10 < Distance <= 15 ------> Farely__matched  ");
		System.out
				.println("  15 < Distance  <= 20 ------>   SignificantMatch                  20 < Distance <= 25 ------> Ok__matched      ");
		System.out
				.println("  25 < Distance  <= 35 ------>   ConsiderableMatch                 35 < Distance <= 45 ------> YES and NO_match ");
		System.out
				.println("  45 < Distance  <= 55 ------>   Hard so Say_match                 55 < Distance <= 60 ------> Not_relavant_Jobs");
		System.out
				.println("  60 < Distance   > 70 ------>   Rejected_Job_List");
		System.out.println();
		System.out
				.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out
				.println("  NAME OF THE JOB Going TO BE MATCHED:                "
						+ " [ "
						+ searchJob
						+ " ]"
						+ "  "
						+ "Target Job Position: "
						+ "["
						+ targetJobPosition
						+ "]" + "  " + "TargetJob_ID: " + searchJobID);
		System.out
				.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println();
		System.out
				.println(" <Distance> --------------  <Senior_favored_weights>   [Name of related jobs Listed regarding their distance] -------------------------------------------------------------------------------");
		System.out.println();

		int targetJobskillNeeded = Integer.valueOf(joblist[testID][0]);
		String targetJobDomain = joblist[testID][1];
		String targetJobField = joblist[testID][2];
		String targetJobSkill_General = joblist[testID][3];
		String targetJobSkill = joblist[testID][4];
		String targetPosition_General = joblist[testID][5];

		//
		// System.out.println("-------------------------");
		// System.out.println("targetJobskillNeeded="+ targetJobskillNeeded);
		// System.out.println("targetJobDomain="+ targetJobDomain);
		// System.out.println("targetJobField="+ targetJobField);
		// System.out.println("targetJobSkill="+ targetJobSkill);
		// System.out.println("targetJobPosition="+ targetJobPosition);
		//

		// Initialize the Total distance arraylist to store the distance value
		// from the for loop//

		ArrayList<String> distanceArrayList = new ArrayList<String>();

		for (int i = 0; i < 889; i++) {

			String jobID = joblist[i][7];
			String jobTitileCode = joblist[i][8];
			int skillNeeded = Integer.valueOf(joblist[i][0]);
			String jobDomain = joblist[i][1];
			String jobField = joblist[i][2];
			String jobSkill_General = joblist[i][3];
			String jobSkill = joblist[i][4];
			String jobPosition_General = joblist[i][5];
			String jobPosition = joblist[i][6];
			float senior_Favored_weight = Float.parseFloat(joblist[i][9]);

			int distanceWhetherSkillNeeded;
			int distanceDomain;
			int distanceField;
			int distanceSkill_General;
			int distanceSkill;
			int distancePosition_General;
			int distancePosition;
			String similarity;

			// measure the distance with respect to each job attributes and
			// dimensions//

			if (skillNeeded == targetJobskillNeeded) {
				distanceWhetherSkillNeeded = 0;
			} else {
				distanceWhetherSkillNeeded = 9000;
			}

			// System.out.println("distanceWhetherSkillNeeded = " +
			// distanceWhetherSkillNeeded );

			if (jobDomain.equals(targetJobDomain)) {
				distanceDomain = 0;
			} else {
				distanceDomain = 10;
			}

			// System.out.println("distanceDomain = " + distanceDomain );

			if (jobField.equals(targetJobField)) {
				distanceField = 0;
			} else {
				distanceField = 5;
			}

			// System.out.println("distanceField = " + distanceField );

			if (jobSkill_General.equals(targetJobSkill_General)) {
				distanceSkill_General = 0;
			} else {
				distanceSkill_General = 15;
			}

			if (jobSkill.equals(targetJobSkill)) {
				distanceSkill = 0;
			} else {
				distanceSkill = 10;
			}

			// System.out.println("distanceSkill  = " + distanceSkill );

			if (jobPosition_General.equals(targetPosition_General)) {
				distancePosition_General = 0;
			} else {
				distancePosition_General = 10;
			}

			if (jobPosition.equals(targetJobPosition)) {
				distancePosition = 0;
			} else {
				distancePosition = 5;
			}

			/*
			 * System.out.println("distancePosition  = " + distancePosition );
			 * 
			 * /* Calculate the total distance for given job using all the
			 * parameters from each job attributes
			 */

			int totalDistanceBetweenTarget = distanceWhetherSkillNeeded
					+ distanceDomain + distanceField + distanceSkill_General
					+ distanceSkill + distancePosition_General
					+ distancePosition;
			// System.out.println(jobID + " = " + totalDistanceBetweenTarget);

			if (totalDistanceBetweenTarget <= 0) {
				similarity = "Perfectly_matched ";
			} else if (totalDistanceBetweenTarget <= 5) {
				similarity = "Very Good_matched ";
			} else if (totalDistanceBetweenTarget <= 10) {
				similarity = "Good___matched   ";
			} else if (totalDistanceBetweenTarget <= 15) {
				similarity = "Farely__matched  ";
			} else if (totalDistanceBetweenTarget <= 20) {
				similarity = "SignificantMatch ";
			} else if (totalDistanceBetweenTarget <= 25) {
				similarity = "Ok__matched      ";
			} else if (totalDistanceBetweenTarget <= 35) {
				similarity = "Considerable Match";
			} else if (totalDistanceBetweenTarget <= 40) {
				similarity = "YES and NO_match ";
			} else if (totalDistanceBetweenTarget <= 45) {
				similarity = "Little   Relavent";
			} else if (totalDistanceBetweenTarget <= 50) {
				similarity = "Not_relavant_Jobs";
			} else {
				similarity = "Rejected_Job_List";
			}

			/*
			 * Appeach the distance with its title + code and job postiion for
			 * reading purpose
			 */

			String jobID_DistanceBetweenTarget = new String();
			jobID_DistanceBetweenTarget = totalDistanceBetweenTarget + "   "
					+ "[ " + similarity + " ]" + "      " + " ("
					+ senior_Favored_weight + " %" + ")" + ":" + "          "
					+ jobTitileCode + "  " + jobID + "       " + "("
					+ jobPosition + ")";

			// System.out.println(jobID_DistanceBetweenTarget);

			//
			// int [] distanceList;
			// distanceList = new int [889];
			// distanceList [i] =totalDistanceBetweenTarget ;
			//
			// Arrays.sort(distanceList);
			// System.out.println(Arrays.toString(distanceList));
			// //
			if (totalDistanceBetweenTarget <= threshold) {
				distanceArrayList.add(jobID_DistanceBetweenTarget);
			}

		}

		Collections.sort(distanceArrayList);
		// System.out.println(distanceArrayList);

		for (String counter : distanceArrayList) {
			System.out.println("    " + counter);
		}
	}

}