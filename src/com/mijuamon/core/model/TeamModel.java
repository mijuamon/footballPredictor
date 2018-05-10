package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

import java.util.List;

public class TeamModel extends AbstractItemModel{

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
    public void convert (String data)
    {
            String [] lista=data.split(";");
            if(lista.length==2)
            {
                this.setId(lista[0]);
                this.setName(lista[1]);
            }
            else
            {
                throw new ConvertException();
            }
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof TeamModel)) {
            return false;
        }
        TeamModel team = (TeamModel) o;
        return team.name.equals(name);
    }
}
