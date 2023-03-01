import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

@RunWith(Parameterized.class)
public class LionTest {
    private final String sex;
    private final boolean hasMane;

    @Mock
    Feline feline;

    public LionTest(String sex, boolean hasMane) {
        this.sex = sex;
        this.hasMane = hasMane;
    }

    @Parameterized.Parameters(name = "{index}: Sex {0} - Expected {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Самец", true},
                {"Самка", false},
        };
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = Exception.class)
    public void LionConstructorWrongSexTest() throws Exception {
        Lion lion = new Lion("Wrong", feline);
    }

    @Test
    public void LionManeTest() throws Exception {
        Lion lion = new Lion(sex, feline);
        boolean expected = hasMane;
        boolean actual = lion.doesHaveMane();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetKittensTest() throws Exception {
        Feline feline = new Feline();
        Lion lion = new Lion(sex, feline);
        int expected = 1;
        int actual = lion.getKittens();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetFoodTest() throws Exception {
        Feline feline = new Feline();
        Lion lion = new Lion(sex, feline);
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = lion.getFood();
        Assert.assertEquals(expected, actual);
    }
}
