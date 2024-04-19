import exceptions.WrongValueJsonException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataValidator {
    private static final String REGEX_POLICY_NAME = "^[\\w+=,.@-]{1,128}$";
    private static final String REGEX_POLICY_DOCUMENT = "^[\\u0009\\u000A\\u000D\\u0020-\\u00FF]{1,131072}$";
    private static final List<String> VERSION_DATES = List.of("2012-10-17", "2008-10-17");
    private static final List<String> EFFECTS = List.of("Allow", "Deny");

    public void validatePolicyName(String policyValue) throws WrongValueJsonException {
        Pattern pattern = Pattern.compile(REGEX_POLICY_NAME);
        Matcher matcher = pattern.matcher(policyValue);
        if (!matcher.matches()) {
            throw new WrongValueJsonException("Wrong data in PolicyName");
        }
    }


    public void validatePolicyDocument(String policyDocument) throws WrongValueJsonException {
        Pattern pattern = Pattern.compile(REGEX_POLICY_DOCUMENT);
        Matcher matcher = pattern.matcher(policyDocument);
        if (!matcher.matches()) {
            throw new WrongValueJsonException("Out of bound sign used or reached min/max amount of signs");
        }
    }

    public void validateVersion(String version) throws WrongValueJsonException {
        if (!VERSION_DATES.stream().anyMatch((String element) -> element.equals(version))) {
            throw new WrongValueJsonException("Wrong data in Version");
        }
    }


    public void validateEffect(String effect) throws WrongValueJsonException {

        if (!EFFECTS.stream().anyMatch((String element) -> element.equals(effect))) {
            throw new WrongValueJsonException("Wrong data in effect");
        }
    }
}
