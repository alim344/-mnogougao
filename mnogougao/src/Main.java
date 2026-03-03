import java.util.*;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        do{
            System.out.print("Unesite broj čvorova mnogougla (n > 2): ");
             num = scanner.nextInt();
        }while(num < 3);


        Set<Point> vertices = getVertices(num,scanner);

        String cont;
        //sortiranje temena ako su uneti slučajnim redosledom
        List<Point> sortedVertices = service.sortVertices(vertices);

        do{
            Point p = getPoint(scanner);

            String res = service.isPointIn(sortedVertices, p);
            System.out.println("---------------RESULT----------------");
            System.out.println(res);

            System.out.println("Do u want to continue with testing points - Y/N: ");
            cont = scanner.next();
        }while(!cont.equalsIgnoreCase("N"));
    }

    private static Point getPoint(Scanner scanner) {
        System.out.println("****Unesite koordinate tacke za proveru: ");
        System.out.print("x: ");
        double x = scanner.nextDouble();

        System.out.print("y: ");
        double y = scanner.nextDouble();
        return new Point(x,y);
    }

    private static Set<Point> getVertices(int num, Scanner scanner) {
        Set<Point> vertices = new HashSet<>();
        System.out.println("Unesite koordinate temena: ");
        for(int i=0 ; i<num ; i++) {
            System.out.println("-----" + (i+1) + "-----");
            System.out.print("x: ");
            double x = scanner.nextDouble();

            System.out.print("y: ");
            double y = scanner.nextDouble();
            Point p = new Point(x,y);

            if(!vertices.add(p)) {
                System.out.println("Ovo teme je vec uneto, probajte neke druge koordinate!");
                i--;

            }

        }

        return vertices;
    }



}