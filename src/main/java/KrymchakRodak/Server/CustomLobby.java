package KrymchakRodak.Server;

import java.util.ArrayList;

class CustomLobby extends GameLobby {
    private String name = null;
    private Client owner = null;
    private Integer botCount = 1;

    CustomLobby(int numOfPlayers, int lobbyID, String name, Client client) {
        super(numOfPlayers, lobbyID);
        this.name = name;
        this.owner = client;
        super.getPlayers().add(client);
    }


    String getName() {
        return this.name;
    }

    @Override
    void addPlayer(Client player) {
        super.getPlayers().add(player);

        ServerCommunication.updateLobby(getPlayers(), getUsernames());
    }

    ArrayList<String> getUsernames() {
        ArrayList<String> names = new ArrayList<>();
        for (Client player : getPlayers()) {
            names.add(player.getUsername());
        }

        return names;
    }

    void addBot() {
        super.getPlayers().add(new BotPlayer("Bot #".concat(botCount.toString())));
        botCount++;
        ServerCommunication.updateLobby(super.getPlayers(), getUsernames());
    }

    void removePlayer(String name) {
        for (Client client : super.getPlayers()) {
            if (client.getUsername().equals(name) && client.getUsername().equals(owner.getUsername())) {
                 deleteLobby();
                 break;
            } else  if (client.getUsername().equals(name)){
                getPlayers().remove(client);
                if (client instanceof BotPlayer) {
                    this.botCount--;
                } else {
                    ServerCommunication.removeFromLobby(client);
                }
                
                ServerCommunication.updateLobby(super.getPlayers(), getUsernames());
            }
        }
    }

    private void deleteLobby() {
        for (Client client : super.getPlayers()) {
            if (!(client instanceof BotPlayer))
            ServerCommunication.lobbyDisbanded(client);
        }
        Lobby.getInstance().removeLobby(super.getLobbyID());
    }

}
