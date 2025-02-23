package defaultemail;

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

public class defaultemailreport implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String reportFileName = "emailable-report.html";
        File reportFile = new File(outputDirectory, reportFileName);

        try (FileWriter writer = new FileWriter(reportFile)) {
            // HTML Header and Styles
            writer.write("<html><head><title>Emailable Report</title>");
            writer.write("<style>");
            writer.write("title { font-family: Arial, sans-serif;text-align:center}");
            writer.write("body { font-family: Arial, sans-serif; }");
            writer.write("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
            writer.write("th, td { padding: 8px; text-align: left; border: 1px solid #ddd; }");
            writer.write("th { background-color: #4CAF50; color: white; }");
            writer.write("tr:nth-child(even) { background-color: #f2f2f2; }");
            writer.write("tr:hover { background-color: #f1f1f1; }");
            writer.write(".summary { font-size: 1.1em; font-weight: bold; color: #4CAF50; margin-top: 20px; }");
            writer.write("</style>");
            writer.write("</head><body>");
            writer.write("<h1>Custom Emailable Report</h1>");

            // Loop through each suite
            for (ISuite suite : suites) {
                writer.write("<h2>Suite: " + suite.getName() + "</h2>");
                Map<String, ISuiteResult> suiteResults = suite.getResults();

                // Initialize counters for passed, failed, and skipped tests
                int passedTests = 0, failedTests = 0, skippedTests = 0;

                for (ISuiteResult suiteResult : suiteResults.values()) {
                    ITestContext testContext = suiteResult.getTestContext();

                    // Update counters
                    passedTests += testContext.getPassedTests().size();
                    failedTests += testContext.getFailedTests().size();
                    skippedTests += testContext.getSkippedTests().size();
                }

                // Write summary section
                writer.write("<div class='summary'>");
                writer.write("Total Tests: " + (passedTests + failedTests + skippedTests) + "<br>");
                writer.write("Passed: " + passedTests + "<br>");
                writer.write("Failed: " + failedTests + "<br>");
                writer.write("Skipped: " + skippedTests + "<br>");
                writer.write("</div>");

                // Write detailed results table
                writer.write("<table>");
                writer.write("<tr><th>Test Name</th><th>Status</th><th>Start Time</th><th>End Time</th><th>Logs</th><th>Screenshots</th><th>Class Name</th><th>Method Name</th><th>Groups</th></tr>");

                for (ISuiteResult suiteResult : suiteResults.values()) {
                    ITestContext testContext = suiteResult.getTestContext();

                    writeTestResults(writer, testContext.getPassedTests().getAllResults(), "Passed");
                    writeTestResults(writer, testContext.getFailedTests().getAllResults(), "Failed");
                    writeTestResults(writer, testContext.getSkippedTests().getAllResults(), "Skipped");
                }

                writer.write("</table>");
            }

            writer.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTestResults(FileWriter writer, Iterable<ITestResult> results, String status) throws IOException {
        for (ITestResult result : results) {
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
    }

    private String getLogs(ITestResult result) {
        StringBuilder logs = new StringBuilder();
        List<String> logEntries = Reporter.getOutput(result);

        if (logEntries != null && !logEntries.isEmpty()) {
            logs.append("<ol>");
            for (String log : logEntries) {
                logs.append("<li>").append(log.replaceAll("\n", "<br>")).append("</li>");
            }
            logs.append("</ol>");
        } else {
            logs.append("No Logs");
        }
        return logs.toString();
    }

    private String getScreenshots(ITestResult result) {
        String screenshotPath = "./Screenshots/" + result.getMethod().getMethodName() + ".png";
        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.exists()) {
            return "<a href='" + screenshotFile.getAbsolutePath() + "'>Screenshot</a>";
        }
        return "No Screenshot";
    }
}