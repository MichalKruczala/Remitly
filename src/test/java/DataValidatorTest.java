import exceptions.WrongValueJsonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataValidatorTest {
    // validatePolicyName
    @Test
    public void shouldThrowExceptionIfIfPolicyNameContainsSpecialSign() {
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validatePolicyName("abc!123"));
    }

    @Test
    public void shouldThrowExceptionIfPolicyNameHasEmptyString() {
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validatePolicyName(""));
    }

    @Test
    public void shouldThrowExceptionIfPolicyNameHasIsTooLong() {
        DataValidator dV = new DataValidator();
        String longString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[{]}|;:',<.>/?";
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validatePolicyName(longString));
    }

    @Test
    public void shouldThrowExceptionIfPolicyNameStringIsEmpty() {
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validatePolicyName(""));
    }

    // validatePolicyDocument
    @Test
    public void shouldThrowExceptionIfPolicyDocumenttringIsTooLong() {
        String str = "a".repeat(131073);
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validatePolicyDocument(str));
    }

    @Test
    public void shouldThrowExceptionIfPolicyDocumentStringIsEmpty() {
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validatePolicyDocument(""));
    }

    @Test
    public void shouldThrowExceptionIfPolicyDocumentContainWrongUnicodeSign() {
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validatePolicyName("\uD840\uDC00"));
    }

    @Test
    public void shouldNotThrowExceptionIfCorrectPolicyDocumentString() {
        DataValidator dV = new DataValidator();
        Assertions.assertDoesNotThrow(() -> dV.validatePolicyName("User123="));
    }

    //  validateEffect
    @Test
    public void shouldThrowExceptionIfIncorrectEffectString() {
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validateEffect("dfgewferge"));
    }

    @Test
    public void shouldNotThrowExceptionIfCorrectEffectString() {
        DataValidator dV = new DataValidator();
        Assertions.assertDoesNotThrow(() -> dV.validateEffect("Allow"));
    }

    @Test
    public void shouldThrowExceptionIfStringEffectIsEmpty() {
        DataValidator dV = new DataValidator();
        Assertions.assertThrows(WrongValueJsonException.class, () -> dV.validateEffect(""));
    }

}

