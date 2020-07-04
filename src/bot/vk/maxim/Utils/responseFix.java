package bot.vk.maxim.Utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class responseFix {

    public static JSONObject Do(String LpInfo) throws Exception {
        Object obj = new JSONParser().parse(String.valueOf(LpInfo));
        JSONObject responseFix = (JSONObject) obj;
        return (JSONObject) responseFix.get("response");
    }
}
