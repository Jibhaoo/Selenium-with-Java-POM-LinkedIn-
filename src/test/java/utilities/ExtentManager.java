package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentManager {
    private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
        	ExtentHtmlReporter html=new ExtentHtmlReporter(filePath);
        	html.config().setDocumentTitle("Test Report");
        	html.config().setReportName("Test Report");
        	html.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(html);
        }
        
        return extent;
    }
    
    public synchronized static ExtentReports getReporter() {
        return extent;
    }

}