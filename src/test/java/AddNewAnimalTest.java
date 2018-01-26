import Lesson4.Animal;
import Lesson4.Dog;
import Lesson4.Shelter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AddNewAnimalTest {

    private Shelter animal;
    private Animal dog;
    private Animal emptydog;
    private Animal emptynamedog;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        animal = new Shelter();
    }

    @Test
    public void positiveTest() {
        dog = new Dog("собачка");
        animal.addNewAnimal(dog);
        boolean result = animal.getShelter().contains(dog);
        Assert.assertTrue("Добавление проходит успешно", result);
    }

    @Test
    public void animalDoesntExist(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Животного не существует");
        animal.addNewAnimal(null);
    }

    @Test
    public void animalNameIsNull(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("У животного нет имени");
        emptydog = new Dog();
        animal.addNewAnimal(emptydog);
    }

    @Test
    public void animalNameIsEmpty(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("У животного нет имени");
        emptynamedog = new Dog("");
        animal.addNewAnimal(emptynamedog);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void animalNameIsEmpty(){
//        emptynamedog = new Dog("");
//        animal.addNewAnimal(emptynamedog);
//    }
}
