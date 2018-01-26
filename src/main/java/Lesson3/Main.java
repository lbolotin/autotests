package Lesson3;

public class Main {
    public static void main(String[] args) {

        Tester petrov = new Tester();
        petrov.setFio("Han Solo");
        petrov.sayName();

        Tester ivanov = new Tester();
        ivanov.setFio("Luke Skywalker");
        ivanov.sayName();

        Tester zaicev = new Tester();
        zaicev.setFio("Master Yoda");
        zaicev.sayName();

        NoteBook acer = new NoteBook();
        acer.setModel("Acer Aspire V5000");
        petrov.setNotebook(acer);
        petrov.showTestersNotebook();

        Tester sidorov = new Tester("Enekin Skywalker", acer);
        sidorov.showTestersNotebook();

//        BossTester boss = new BossTester();
//        Тестировщик тестировщик=boss;
//        acer.model = "Acer Aspire V5000";
//        acer.price = 200;
//        acer.type = "Office";
//        Ноутбук asus = new Ноутбук();
//        asus.model = "Asus X1";
//        asus.price = 1000;
//        asus.type = "Game";
//        Ноутбук hp = new Ноутбук();
//        hp.model = "HP 101";
//        hp.price = 300;
//        hp.type = "Office";
//        System.out.printf(petrov.setName()+ ", "+ acer.giveTestNotebook() +"\n");
//        System.out.printf(ivanov.setName()+ ", " +"\n");
//        System.out.printf(zaicev.setName()+ ", " +"\n");
    }
}
