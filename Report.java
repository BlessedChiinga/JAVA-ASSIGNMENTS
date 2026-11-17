abstract class Report {
    protected String title;

    public Report(String title) {
        this.title = title;
    }

    void printTitle() {
        System.out.println(title);
        generate();
    }

    abstract void generate();
}

class SalesReport extends Report {
    public SalesReport() { super("Sales"); }
    @Override
    void generate() { System.out.println("Generating sales report"); }

    void printtitle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
