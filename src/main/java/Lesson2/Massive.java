package Lesson2;

public class Massive {
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String[] animals = new String[10];
        animals[0] = "заяц";
        animals[1] = "лиса";
        animals[2] = "волк";
        animals[3] = "медведь";
        animals[4] = "кролик";
        animals[5] = "сова";
        animals[6] = "хрюша";
        animals[7] = "крот";
        animals[8] = "кот";
        animals[9] = "дичь";

        System.out.println("+" + array[6] + " (" + array[0] + array[1] + array[2] + ") " + array[3] + array[4] + array[5] + "-" + array[6] + array[7] + "-" + array[8] + array[9]);
        for (String animalstring : animals)
            System.out.println(animalstring);
    }
}
