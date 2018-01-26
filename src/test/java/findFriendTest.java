import Lesson4.Animal;
import Lesson4.Dog;
import Lesson4.Shelter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class findFriendTest {
    private Shelter animal;
    private Animal dog;
    private Animal emptydog;


    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Before
    public void init(){
        animal = new Shelter();
    }

    @Test
    public void positiveTest(){
        dog = new Dog("собачка");
        animal.addNewAnimal(dog);
        animal.findFriend();
        boolean result = animal.getShelter().isEmpty();
        Assert.assertTrue("Собачка ушла домой", result);
    }

    @Test
    public void shelterIsEmpty1Test(){
        exception.expect(RuntimeException.class);
        exception.expectMessage("В приюте нет животных");
        animal.findFriend();
    }
}
