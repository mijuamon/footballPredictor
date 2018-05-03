package com.mijuamon.core.model;

import java.util.List;

public class TeamModel implements AbstractItemModel{

    private String name;
    private String id;
    private List<PlayerModel> players;

    public TeamModel() {
    }

    public TeamModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void convert (String data, AbstractItemModel model)
    {
            String [] lista=data.split(";");
            if(lista.length==2)
            {
                model= new TeamModel(lista[0],lista[1]);
            }
    }
}
