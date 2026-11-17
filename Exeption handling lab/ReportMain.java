

public class ReportMain{
    public static void main(String[] args){
        SalesReport report = new SalesReport();

        report.printTitle();
        report.generate();
    }
}