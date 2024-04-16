import exceptions.MissingElementInJsonException;
import exceptions.SingleAsteriskException;
import exceptions.WrongValueJsonException;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonVerificator {

    boolean hasPolicyName = false;
    boolean hasPolicyDocument = false;
    boolean hasVersion = false;
    boolean hasSid = false;
    boolean hasStatement = false;
    boolean hasEffect = false;
    boolean hasAction = false;
    boolean hasResource = false;
    DataValidator dataValidator = new DataValidator();

    public boolean validateJson(String jsonAsString) throws MissingElementInJsonException, WrongValueJsonException, SingleAsteriskException {

        JSONObject json = new JSONObject(jsonAsString);
        hasPolicyName = json.has("PolicyName");
        if (hasPolicyName) {
            String policyName = json.getString("PolicyName");
            dataValidator.validatePolicyName(policyName);
        } else throw new MissingElementInJsonException("Luck of Policy Name");
        hasPolicyDocument = json.has("PolicyDocument");
        if (hasPolicyDocument) {
            JSONObject policyDocument = json.getJSONObject("PolicyDocument");
            dataValidator.validatePolicyDocument(policyDocument.toString());
            hasVersion = policyDocument.has("Version");
            if (hasVersion) {
                String version = policyDocument.getString("Version");
                dataValidator.validateVersion(version);
            } else throw new MissingElementInJsonException("Missing Vesion");
            hasStatement = policyDocument.has("Statement");
            if (hasStatement) {
                JSONArray statementArray = policyDocument.getJSONArray("Statement");
                if (!statementArray.isEmpty()) {
                    for (int i = 0; i < statementArray.length(); i++) {
                        JSONObject singleStatement = statementArray.getJSONObject(i);
                        hasSid = singleStatement.has("Sid");
                        singleStatement.getString("Sid");
                        hasEffect = singleStatement.has("Effect");
                        if (hasEffect) {
                            String effect = singleStatement.getString("Effect");
                            dataValidator.isValidEffect(effect);
                        } else throw new MissingElementInJsonException("Missing Effect");
                        hasAction = singleStatement.has("Action");
                        if (hasAction) {
                            singleStatement.getJSONArray("Action");
                        } else throw new MissingElementInJsonException("Missing Action");
                        hasResource = singleStatement.has("Resource");
                        if (hasResource) {
                            String resource = singleStatement.getString("Resource");
                            if (resource.equals("*")) {
                                throw new SingleAsteriskException();
                            }
                        } else throw new MissingElementInJsonException("Missing Resource");
                    }
                } else throw new MissingElementInJsonException("missing statement list of Objects");
            }
        } else throw new MissingElementInJsonException("Missing Policy Document");
        return true;
    }
}

