package Lesson1;

public class Crow {
    //    interface Foo{void getA();}
    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void getA() {
//            }
//        };
        int a = 0;
        String b = " ворона сидела";
        String c = " вороны сидели";
        String d = " ворон сидели";
        String e = " На дереве нет ворон";

        if (0 <= a) {
            System.out.print(e);
        } else if (10 < a % 100 && 20 > a % 100 || 4 < a % 10 && a % 10 < 11) {
            System.out.print(a + d + " на дереве");
        } else if (1 == a % 10) {
            System.out.print(a + b + " на дереве");
        } else if (2 == a % 10 || 3 == a % 10 || 4 == a % 10) {
            System.out.print(a + c + " на дереве");
        } else if (4 > a % 10 && 10 > a % 10) {
            System.out.print(a + d + " на дереве");
        } else System.out.print(e);
    }
}
