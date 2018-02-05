//package Lesson4;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Animal akitaNun = new Dog("Акита-нун");
//        Animal bishon = new Dog("Бишон");
//        Animal bobTail = new Dog("Бобтейл");
//        Animal bokser = new Dog("Боксер");
//        Animal dalmatin = new Dog("Далматин");
//        Animal bigl = new Dog("Бигль");
//        Animal dogless = new Dog(null);
//
//        akitaNun.sayName();
//        akitaNun.askForFood();
//        akitaNun.biteMaster();
//        akitaNun.roar();
//
//        Shelter shelter = new Shelter();
//        shelter.addNewAnimal(akitaNun);
//        shelter.addNewAnimal(bishon);
//        shelter.addNewAnimal(bobTail);
//        shelter.addNewAnimal(bokser);
//        shelter.addNewAnimal(dalmatin);
//        shelter.addNewAnimal(bigl);
//        shelter.addNewAnimal(null);
//        shelter.addNewAnimal(dogless);
//        shelter.showShelter();
//        shelter.findFriend();
//
//        int size = shelter.getShelter().size();
//        for (int i = 0; i < size; i++) {
//            System.out.println(shelter.findFriend() + " забрали домой ^^");
//        }
//    }
//}
