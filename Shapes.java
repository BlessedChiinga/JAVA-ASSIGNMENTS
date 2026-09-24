interface Shapes {
    void calculateArea();
}

class Circle implements Shapes {
    double r;

    Circle(double r) {
        this.r = r;
    }

    public void calculateArea() {
        System.out.println(Math.PI * r * r);
    }
}

class Rectangle implements Shapes {
    double l, b;

    Rectangle(double l, double b) {
        this.l = l;
        this.b = b;
    }

    public void calculateArea() {
        System.out.println(l * b);
    }
}

class Square implements Shapes {
    double l;

    Square(double l) {
        this.l = l;
    }

    public void calculateArea() {
        System.out.println(l * l);
    }
}

class Triangle implements Shapes {
    double b, h;

    Triangle(double b, double h) {
        this.b = b;
        this.h = h;
    }

    public void calculateArea() {
        System.out.println(0.5 * b * h);
    }
}