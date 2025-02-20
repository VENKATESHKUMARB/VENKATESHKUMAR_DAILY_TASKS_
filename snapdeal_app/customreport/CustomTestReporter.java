package customreport;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CustomTestReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String reportFileName = "CustomTestNGReport_" + ".html";
        File reportFile = new File(outputDirectory, reportFileName);

        try (FileWriter writer = new FileWriter(reportFile)) {
            // Writing HTML Header
            writer.write("<html><head><title>Custom TestNG Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; }");
            writer.write("table { width: 100%; border-collapse: collapse; margin: 20px 0; border:1px solid black }");
            writer.write("th, td { padding: 12px; text-align: left; border: 1px solid #ddd; }");
            writer.write("th { background-color: #4CAF50; color: white; }");
            writer.write("tr:nth-child(even) { background-color: #f2f2f2; }");
            writer.write("tr:hover { background-color: #f1f1f1; }");
            writer.write(".section-title { background-color: #e7f7e7; padding: 10px; font-size: 1.2em; margin-top: 30px; }");
            writer.write(".summary { font-size: 1.1em; font-weight: bold; color: #4CAF50; margin-top: 20px; text-align:center; }");
            writer.write("</style>");
            writer.write("</head><body>");
            writer.write("<h1>Custom TestNG Report</h1>");

            // Loop through each suite
            for (ISuite suite : suites) {
                writer.write("<h2>Suite: " + suite.getName() + "</h2>");
                Map<String, ISuiteResult> suiteResults = suite.getResults();

                // Initialize counters for passed, failed, and skipped tests
                int passedTests = 0, failedTests = 0, skippedTests = 0;

                for (ISuiteResult suiteResult : suiteResults.values()) {
                    ITestContext testContext = suiteResult.getTestContext();

                    // Start of the summary section
                    writer.write("<div class='summary'>");
                    writer.write("Total Tests: " + testContext.getAllTestMethods().length + "<br>");
                    writer.write("Passed: " + testContext.getPassedTests().size() + "<br>");
                    writer.write("Failed: " + testContext.getFailedTests().size() + "<br>");
                    writer.write("Skipped: " + testContext.getSkippedTests().size() + "<br>");
                    writer.write("</div>");

                    // Generating individual test result tables for Passed, Failed, and Skipped
                    writeTestResults(writer, "Passed Tests", testContext.getPassedTests().getAllResults(), "Passed");
                    writeTestResults(writer, "Failed Tests", testContext.getFailedTests().getAllResults(), "Failed");
                    writeTestResults(writer, "Skipped Tests", testContext.getSkippedTests().getAllResults(), "Skipped");
                }
            }

            writer.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTestResults(FileWriter writer, String sectionTitle, Iterable<ITestResult> results, String status) throws IOException {
        writer.write("<div class='section-title'>" + sectionTitle + "</div>");
        writer.write("<table>");
        writer.write("<tr><th>Test Name</th><th>Status</th><th>Start Time</th><th>End Time</th><th>Logs</th><th>Screenshots</th><th>Class Name</th><th>Method Name</th><th>Groups</th></tr>");

        for (ITestResult result : results) {
            writeTestResult(writer, result, status);
        }

        writer.write("</table>");
    }

    private void writeTestResult(FileWriter writer, ITestResult result, String status) throws IOException {
        writer.write("<tr>");
        writer.write("<td>" + result.getName() + "</td>");
        writer.write("<td>" + status + "</td>");
        writer.write("<td>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(result.getStartMillis())) + "</td>");
        writer.write("<td>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(result.getEndMillis())) + "</td>");
        writer.write("<td>" + getLogs(result) + "</td>");
        writer.write("<td>" + getScreenshots(result) + "</td>");
        writer.write("<td>" + result.getTestClass().getName() + "</td>");
        writer.write("<td>" + result.getMethod().getMethodName() + "</td>");
        writer.write("<td>" + String.join(", ", result.getMethod().getGroups()) + "</td>");
        writer.write("</tr>");
    }

    private String getLogs(ITestResult result) {
        StringBuilder logs = new StringBuilder();
        List<String> logEntries = Reporter.getOutput(result);

        // Check if there are any logs
        if (logEntries != null && !logEntries.isEmpty()) {
            logs.append("<ol>");  // Start an ordered list to make the steps sequential
            for (String log : logEntries) {
                logs.append("<li>").append(log.replaceAll("\n", "<br>")).append("</li>");  // Wrap each log in <li> and replace new lines with <br>
            }
            logs.append("</ol>");  // End the ordered list
        } else {
            logs.append("No Logs");
        }
        return logs.toString();
    }

    private String getScreenshots(ITestResult result) {
        String screenshotPath = "./Screenshots/" + result.getMethod().getMethodName() + ".png";
        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.exists()) {
            return "<a href='\"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\" + screenshotPath + "\' target='_blank'>Screenshot</a>";
        
        
        }
        
        return "No Screenshot";
    }
}
