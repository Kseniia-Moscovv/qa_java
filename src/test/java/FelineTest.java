import com.example.Feline;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

@RunWith(Parameterized.class)
public class FelineTest {
    private final int kittensCount;
    private final int expectedKittensCount;
    @Spy
    private Feline feline;

    public FelineTest(int kittensCount,int expectedKittensCount) {
        this.kittensCount = kittensCount;
        this.expectedKittensCount = expectedKittensCount;
    }

    @Parameterized.Parameters(name = "{index}: Actual {0} - Expected {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
            {-1, -1},
            {0, 0},
            {1, 1},
            {5, 5},
            {100, 100},
        };
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void EatMeatTest() throws Exception {
        Feline feline = new Feline();
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = feline.eatMeat();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetFamilyTest() {
        Feline feline = new Feline();
        String expected = "Кошачьи";
        String actual = feline.getFamily();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetKittensWithoutParamsTest() {
        Feline feline = new Feline();
        int expected = 1;
        int actual = feline.getKittens();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetKittensWithParamsTest() {
        Feline feline = new Feline();
        int expected = expectedKittensCount;
        int actual = feline.getKittens(kittensCount);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetKittensNoParamInvokeGetKittensWith1() {
        feline.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens(1);
    }
}

