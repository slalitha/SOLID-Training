package com.trimble.report;

public class ReportGenerator {
	
	public enum ReportType{
		PASS_FAIL_STATUS,
		PASS_STATUS,
		FAIL_STATUS
	}
	public static IReportGenerator getInstance(ReportType reportType) {
		
		IReportGenerator mIReportGenerator = null;
		
		if(reportType == ReportType.PASS_FAIL_STATUS) {
			mIReportGenerator = new StudentReport();
		}else if(reportType == ReportType.PASS_STATUS) {
			mIReportGenerator = new StudentPassReport();
		}else if (reportType == ReportType.FAIL_STATUS){
			mIReportGenerator = new StudentFailReport();
		}
		
		return mIReportGenerator;
	}

}
