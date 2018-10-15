package application.utils;

import application.utils.ResultGenerator.IReportGenerator;
import application.utils.ResultGenerator.ReportGenerator;

public class ReportGeneratorProviderFactory {
	
	public static IReportGenerator getReportGenerator(String type) {
		if(type.equals(Constants.NORMAL)) {
			return new ReportGenerator();
		}
		return null;
	}

}
