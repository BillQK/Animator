import org.junit.Test;

import model.utils.ArgumentsCheck;

public class ArgumentsCheckTest {
  @Test
  public void testoverlappingTime() {
    ArgumentsCheck.overlappingTime(30, 40, 10, 20);

    ArgumentsCheck.overlappingTime(30, 40, 10, 38);

  }

}
