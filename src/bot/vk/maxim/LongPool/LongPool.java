package bot.vk.maxim.LongPool;

import bot.vk.maxim.Utils.request;
import bot.vk.maxim.Utils.responseFix;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LongPool {

    public static JSONObject InitLongPool(String GroupId, String access_token) throws Exception{
        String get = request.get("https://api.vk.com/method/groups.getLongPollServer?group_id="+GroupId+"&access_token="+access_token+"&v=5.120");
        return responseFix.Do(get);
    }

    public static JSONObject ParseLongPool(JSONObject LpInfoJson) throws Exception {
        String key= LpInfoJson.get("key").toString();
        String ts = LpInfoJson.get("ts").toString();
        String server= LpInfoJson.get("server").toString();
        String LpData = request.get(server+"?act=a_check&key="+key+"&ts="+ts+"&wait=15&version=3");
        return (JSONObject) (new JSONParser().parse(LpData));

    }
    public static JSONObject Listen(JSONObject LpInfo) throws Exception {
        return ParseLongPool(LpInfo);
    }
}
