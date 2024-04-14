import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DataValidator {
    String regexPolicyName = "^[\\w+=,.@-]{1,128}$";
    List<String> versionDatesList = List.of("2012-10-17","2012-10-17","2008-10-17");
    List<String> effectList = List.of("Allow","Deny");

    public boolean isValidPolicyName(String policyName){
        Pattern pattern = Pattern.compile(regexPolicyName);
        Matcher matcher = pattern.matcher(policyName);
        return matcher.matches();
    }
    public boolean isValidVersion(String version){
        for (String versionDate: versionDatesList){
            if(versionDate.equals(version)){
                return true;
            }
        }
        return false;
    }
    public boolean isValidEffect(String effect){
        for (String singleEffect: effectList){
            if(singleEffect.equals(effect)){
                return true;
            }
        }
        return false;
    }
}
