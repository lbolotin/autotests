package Lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shelter {

    private List<Animal> arrayList = new ArrayList<>();

    public List getShelter() {
//        Dog dog = new Dog;
//        map.put(new Dog());
//        map.put("Аффенпинчер");
        return arrayList;
    }

    public void addNewAnimal(Animal dog) {
        if (dog == null) {
            throw new IllegalArgumentException("Животного не существует");
        }
        if (dog.name == null||dog.name.isEmpty()) {
            throw new IllegalArgumentException("У животного нет имени");
        }
        arrayList.add(dog);
    }

    public void showShelter() {
        System.out.println(arrayList);
    }

    public Animal findFriend() {
        Random random = new Random();
        try {
            Animal animal = arrayList.get(random.nextInt(arrayList.size()));
            arrayList.remove(animal);
            return animal;
        } catch (IllegalArgumentException exc) {
            throw new RuntimeException("В приюте нет животных");
        }
    }
}

