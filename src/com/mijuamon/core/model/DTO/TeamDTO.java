package com.mijuamon.core.model.DTO;

import com.mijuamon.core.exceptions.ConvertException;

import java.util.ArrayList;
import java.util.List;

public class TeamDTO extends AbstractItemDTO{

    private String name;
    private List<PlayerDTO> players= new ArrayList<>();
    private List<MatchDTO> matches= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<MatchDTO> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDTO> matches) {
        this.matches = matches;
    }

    @Override
    public void convert(String data) throws ConvertException {
        String[] lista = data.split(";");
        if (lista.length == 2) {
            this.setID(lista[0]);
            this.setName(lista[1]);
        } else {
            throw new ConvertException(this.getClass().toString());
        }
    }

}
