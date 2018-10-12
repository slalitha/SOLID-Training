package application.utils;

public class ReportGeneratorProviderFactory {
	
	public static IReportGenerator getReportGenerator(String type) {
		if(type.equals(Constants.NORMAL)) {
			return new ReportGenerator();
		}
		return null;
	}

}
