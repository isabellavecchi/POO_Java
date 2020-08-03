public class Book {
    private static String publisher = "Addison-Wesley"; //class variable
                                                        //(static field)
                //pode ser acessada por static & non-static methods

    private String title = "The Mythical Man-Month";  //instance variable
                                                      //(non-static field)
                //só pode ser acessada por non-static methods

    public static String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }
}