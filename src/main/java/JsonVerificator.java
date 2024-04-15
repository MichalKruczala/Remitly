import exceptions.SingleAsteriskException;
import exceptions.WrongValueJsonException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonVerificator {

    boolean hasPolicyName = false;
    boolean hasPolicyDocument = false;
    boolean hasVersion = false;
    boolean hasStatement = false;
    boolean hasEffect = false;
    boolean hasAction = false;
    boolean hasResource = false;
    DataValidator dataValidator = new DataValidator();

    public boolean validateJson(String jsonAsString) {
        try {
            JSONObject json = new JSONObject(jsonAsString);
            hasPolicyName = json.has("PolicyName");
            if (hasPolicyName) {
                String policyName = json.getString("PolicyName");
                verifyPolicyName(policyName);
            }
            hasPolicyDocument = json.has("PolicyDocument");
            if (hasPolicyDocument) {
                JSONObject policyDocument = json.getJSONObject("PolicyDocument");
                hasVersion = policyDocument.has("Version");
                if (hasVersion) {
                    String version = policyDocument.getString("Version");
                    verifyVersion(version);
                }
                hasStatement = policyDocument.has("Statement");
                if (hasStatement) {
                    JSONArray statementArray = policyDocument.getJSONArray("Statement");
                    if (!statementArray.isEmpty()) {
                        for (int i = 0; i < statementArray.length(); i++) {
                            JSONObject singleStatement = statementArray.getJSONObject(i);
                            boolean hasSid = singleStatement.has("Sid");
                            singleStatement.getString("Sid");
                            hasEffect = singleStatement.has("Effect");
                            if (hasEffect) {
                                String effect = singleStatement.getString("Effect");
                                verifyEffect(effect);
                            }
                            hasAction = singleStatement.has("Action");
                            if (hasAction) {
                                singleStatement.getJSONArray("Action");
                            }
                            hasResource = singleStatement.has("Resource");
                            if (hasResource) {
                                String resource = singleStatement.getString("Resource");
                                if (resource.equals("*")) {
                                    throw new SingleAsteriskException();
                                }
                            }
                        }
                    } else throw new WrongValueJsonException("missing statement obcjects list");
                }
            }
            return true;
        } catch (JSONException e) {
            System.out.println("Wrong format JSON data provided");
            return false;
        } catch (WrongValueJsonException w) {
            System.out.println(w.getMessage());
            return false;
        } catch (SingleAsteriskException e) {
            System.out.println(e.getMessage());
            System.out.println("false");
            return false;
        }
    }

    private void verifyVersion(String version) throws WrongValueJsonException {

        if (!dataValidator.isValidVersion(version)) {
            throw new WrongValueJsonException("Wrong data in Version");
        }
    }

    private void verifyPolicyName(String policyName) throws WrongValueJsonException {
        if (!dataValidator.isValidPolicyName(policyName)) {
            throw new WrongValueJsonException("Wrong data in PolicyName");
        }
    }


    private void verifyEffect(String effect) throws WrongValueJsonException {
        if (!dataValidator.isValidEffect(effect)) {
            throw new WrongValueJsonException("Wrong data in Effect");
        }
    }
}

