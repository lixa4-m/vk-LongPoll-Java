package bot.vk.maxim;

import bot.vk.maxim.Utils.request;

import bot.vk.maxim.LongPool.LongPool;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Bot {

    private static String GroupId = "--";
    private static String access_token = "--";

    public static void main(String[] args) throws Exception {
        LongPoolHandler(null, null);
    }

    public static void LongPoolHandler(String GroupId_, String access_token_) throws Exception {
        if (access_token_ == null) {
            access_token_ = access_token;
        }
        if (GroupId_ == null) {
            GroupId_ = GroupId;
        }
        String ts = "0";
        while (true) {

            JSONObject LongPoolJson = LongPool.Listen(LongPool.InitLongPool(GroupId_, access_token_));
            if (!ts.equals(LongPoolJson.get("ts"))) {

                try {
                    JSONObject updates = (JSONObject) ((JSONArray) LongPoolJson.get("updates")).get(0);
                    if (updates.get("type").toString().equals("message_new")){
                        JSONObject MessageInfo = (JSONObject) ((JSONObject) updates.get("object")).get("message");

                        String UserId = MessageInfo.get("from_id").toString();
                        String PeerId = MessageInfo.get("peer_id").toString();
                        String text = MessageInfo.get("text").toString().toLowerCase();
                        String NotLowerText = MessageInfo.get("text").toString();
                        String MessageID = MessageInfo.get("id").toString();

                        System.out.println("\nMessage from: "+UserId+"\nPeer_id: "+PeerId+"\nText: "+NotLowerText+"\nMessageID: "+MessageID);
                        MessageHandler(UserId, PeerId, text, NotLowerText, MessageID);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            ts = LongPoolJson.get("ts").toString();
        }
    }

    public static void MessageHandler(String UserId, String PeerId,
                                      String text, String NotLowerText, String MessageID) throws Exception {
        if ("bot".equals(text)) {
            MessageSend(PeerId, "I'm here!");
        }
    }

    public static void MessageSend(String PeerId, String text) throws Exception {
        text = text.replace(" ", "%20");
        request.get("https://api.vk.com/method/messages.send?peer_id="+PeerId+"&message="+text
                +"&access_token="+access_token+"&random_id=0&v=5.151");
    }
}

//    JSONArray updates = (JSONArray) ((JSONArray) LongPoolJson.get("updates")).get(0);
//    String event = updates.get(0).toString();
//                    if (event.equals("4")){
//                            String text = updates.get(5).toString();
//                            String UserId = updates.get(3).toString();
//                            System.out.println("\nMessage from: "+UserId+"\nPeer_id: --\nText: "+text);
//                            }
//                            System.out.println(updates);