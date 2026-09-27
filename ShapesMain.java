import java.util.Scanner;
public class ShapesMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter radius of Circle: ");
        double radius = scanner.nextDouble();
        Shapes circle = new Circle(radius);

        System.out.println("Circle area:");
        circle.calculateArea();

        System.out.print("Enter length of Rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width of Rectangle: ");
        double width = scanner.nextDouble();
        Shapes rectangle = new Rectangle(length, width);

        System.out.println("Rectangle area:");
        rectangle.calculateArea();


        System.out.print("Enter side of Square: ");
        double side = scanner.nextDouble();
        Shapes square = new Square(side);

        System.out.println("Square area:");
        square.calculateArea();

        System.out.print("Enter base of Triangle: ");
        double base = scanner.nextDouble();
        System.out.print("Enter height of Triangle: ");
        double height = scanner.nextDouble();
        Shapes triangle = new Triangle(base, height);

        System.out.println("Triangle area:");
        triangle.calculateArea();
    }
}
