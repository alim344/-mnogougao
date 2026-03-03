import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Service {

    public Service(){}


    public List<Point> sortVertices(Set<Point> vertices){

        //računamo centralnu tačku
        int size = vertices.size();
        double centerX = 0;
        double centerY = 0;

        for(Point point : vertices){
            centerX += point.getX();
            centerY += point.getY();
        }


        final double finalX = centerX / size;
        final double finalY = centerY / size;

        //set se prebacuje u listu kako bi mogla da se koristi funkcija sort koju nudi lista
        List<Point> sortedVertices = new ArrayList<>(vertices);

        sortedVertices.sort((p1,p2) ->{
            double angle1 = Math.atan2(p1.getY() - finalY, p1.getX() - finalX);
            double angle2 = Math.atan2(p2.getY() - finalY, p2.getX() - finalX);
            return Double.compare(angle1, angle2);
        });



        return sortedVertices;
    }



    public String isPointIn(List<Point> vertices, Point point){

        int size = vertices.size();
        double temp = 0;

        //prolazimo kroz ivice
        for(int i = 0; i < size; i++){

            Point p1 = vertices.get(i);

            //ako je pocetno teme onda je drugo teme te ivice poslednje
            int j;
            if(i == 0){
                j = size-1;
            }else{
                j = i-1;
            }

            Point p2 = vertices.get(j);

            //računamo vektorski proizvod ivice i tačke
            double res = crossProduct(p1,p2,point);

            if(res == 0){
                //proveravamo da li je tačka zapravo na ivici ili je samo na njenom "beskonačnom pravcu"
               if(checkIsOnTheLine(p1,p2,point)){
                   return "The point is on the line";
               } else{
                   return "The point is not inside the polygon";
               }
            }

            if(temp == 0){
                temp = res;
            }else if( (res > 0) != (temp > 0)){ // ako je znak nekog vektorskog proizvoda drugačiji od svih ostalih, znači da tačka nije u ravni
                return "The point is not inside the polygon";
            }

        }
        return "The point is inside the polygon";
    }

    public double crossProduct(Point p1, Point p2, Point thep){
        //vekotr ivice
        double v1x = p2.getX() - p1.getX();
        double v1y = p2.getY() - p1.getY();
        //vektor tačke i jednog temena
        double v2x = thep.getX() - p1.getX();
        double v2y = thep.getY() - p1.getY();

        return (v1x * v2y) - (v1y * v2x);

    }

    public boolean checkIsOnTheLine(Point p1, Point p2, Point point){
         if(point.getX() >= Math.min(p1.getX(), p2.getX()) && point.getY() >= Math.min(p1.getY(), p2.getY())
         && point.getX() <= Math.max(p1.getX(), p2.getX()) && point.getY() <= Math.max(p1.getY(), p2.getY())){
             return true;
         }
         return false;
    }


}
