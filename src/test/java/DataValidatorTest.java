import exceptions.WrongValueJsonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataValidatorTest {

    private final DataValidator dataValidator;

    public DataValidatorTest() {
        dataValidator = new DataValidator();
    }

    @Test
    public void shouldThrowExceptionIfIfPolicyNameContainsSpecialSign() {
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validatePolicyName("abc!123"));
    }

    @Test
    public void shouldThrowExceptionIfPolicyNameHasEmptyString() {
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validatePolicyName(""));
    }

    @Test
    public void shouldThrowExceptionIfPolicyNameHasIsTooLong() {
        String longString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[{]}|;:',<.>/?";
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validatePolicyName(longString));
    }

    @Test
    public void shouldThrowExceptionIfPolicyNameStringIsEmpty() {
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validatePolicyName(""));
    }

    @Test
    public void shouldThrowExceptionIfPolicyDocumentStringIsTooLong() {
        String str = "a".repeat(131073);
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validatePolicyDocument(str));
    }

    @Test
    public void shouldThrowExceptionIfPolicyDocumentStringIsEmpty() {
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validatePolicyDocument(""));
    }

    @Test
    public void shouldThrowExceptionIfPolicyDocumentContainWrongUnicodeSign() {
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validatePolicyName("\uD840\uDC00"));
    }

    @Test
    public void shouldNotThrowExceptionIfCorrectPolicyDocumentString() {
        Assertions.assertDoesNotThrow(() -> dataValidator.validatePolicyName("User123="));
    }

    @Test
    public void shouldThrowExceptionIfIncorrectEffectString() {
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validateEffect("dfgewferge"));
    }

    @Test
    public void shouldNotThrowExceptionIfCorrectEffectString() {
        Assertions.assertDoesNotThrow(() -> dataValidator.validateEffect("Allow"));
    }

    @Test
    public void shouldThrowExceptionIfStringEffectIsEmpty() {
        Assertions.assertThrows(WrongValueJsonException.class, () -> dataValidator.validateEffect(""));
    }

}

