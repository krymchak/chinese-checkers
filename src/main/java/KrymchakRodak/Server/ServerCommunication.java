package KrymchakRodak.Server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ServerCommunication {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String badLogin() {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "BAD_LOGIN");

        return jsonNode.toString();
    }

    public static String loginSuccess() {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "LOGIN_SUCCESS");

        return jsonNode.toString();
    }
}
