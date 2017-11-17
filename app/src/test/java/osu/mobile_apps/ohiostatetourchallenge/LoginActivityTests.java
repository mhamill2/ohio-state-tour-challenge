package osu.mobile_apps.ohiostatetourchallenge;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class LoginActivityTests {
    boolean booleanResults[];
    LoginActivity loginActivity;
    @Before
    public void setUp() {
        loginActivity = new LoginActivity();
        booleanResults = new boolean[3];
    }
    @Test
    public void isEmailValid_ValidUserNames_ReturnsTrue() {
        booleanResults[0] = loginActivity.isEmailValid("Jon Jones");
        booleanResults[1] = loginActivity.isEmailValid("user@gmail.com");
        booleanResults[2] = loginActivity.isEmailValid("hamill.33");
        for (boolean result: booleanResults) {
            Assert.assertTrue(result);
        }
    }
    @Test
    public void isEmailValid_InvalidUserNames_ReturnsFalse() {
        booleanResults[0] = !loginActivity.isEmailValid("");
        booleanResults[1] = !loginActivity.isEmailValid("micha");
        booleanResults[2] = !loginActivity.isEmailValid("12345");
        for (boolean result: booleanResults) {
            Assert.assertTrue(result);
        }
    }
    @Test
    public void isEmailValid_InvalidUserNameWithWhiteSpaces_ReturnsFalse() {
        booleanResults[0] = !loginActivity.isEmailValid("      ");
        booleanResults[1] = !loginActivity.isEmailValid("m ic ha ");
        booleanResults[2] = !loginActivity.isEmailValid("1 2 3 4 5");
        for (boolean result: booleanResults) {
            Assert.assertTrue(result);
        }
    }
    @Test
    public void isPasswordValid_ValidPasswords_ReturnsTrue() {
        booleanResults[0] = loginActivity.isPasswordValid("password");
        booleanResults[1] = loginActivity.isPasswordValid("Password123!!");
        booleanResults[2] = loginActivity.isPasswordValid("abc987xyz123");
        for (boolean result: booleanResults) {
            Assert.assertTrue(result);
        }
    }
    @Test
    public void isPasswordValid_InvalidPasswords_ReturnsFalse() {
        booleanResults[0] = !loginActivity.isPasswordValid("pswrd");
        booleanResults[1] = !loginActivity.isPasswordValid("12345");
        booleanResults[2] = !loginActivity.isPasswordValid("");
        for (boolean result: booleanResults) {
            Assert.assertTrue(result);
        }
    }
}
