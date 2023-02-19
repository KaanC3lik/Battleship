abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double getPerimeter() {
        return a + b + c;
    }

    @Override
    double getArea() {
        double s = (a + b + c)/2;
        return Math.sqrt((s * (s - a) * (s - b) * (s - c)));
    }
}

class Rectangle extends Shape {
    double heigth;
    double length;
    public Rectangle(double heigth, double length) {
        this.heigth = heigth;
        this.length = length;
    }


    @Override
    double getPerimeter() {
        return length*2 + heigth*2;
    }

    @Override
    double getArea() {
        return length * heigth;
    }
}

class Circle extends Shape {
    double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    double getPerimeter() {
        return Math.PI * 2 * r;
    }

    @Override
    double getArea() {
        return Math.PI * r * r;
    }
}