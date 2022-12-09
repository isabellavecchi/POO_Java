public class Classroom {
    public static void main(String[] args) {
        //getMascot() //ilegal
                    //não existe este método nesta classe
    
        System.out.println(Student.getMascot());
        Student.setMascot("Duke");
        System.out.println(Student.getMascot());

        Student jennifer = new Student();
        Student james = new Student();

        jennifer.setMascot("New Duke");
        System.out.println(james.getMascot());
        //se muda um static field, ele muda para todos
    }
}