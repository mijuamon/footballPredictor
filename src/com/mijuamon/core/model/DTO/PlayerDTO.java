package com.mijuamon.core.model.DTO;

import com.mijuamon.core.exceptions.ConvertException;

import java.util.ArrayList;
import java.util.List;

public class PlayerDTO extends AbstractItemDTO
{
    private String name;
    private String teamID;
    private List<ScoreDTO> scores= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScoreDTO> getScores() {
        return scores;
    }

    public void setScores(List<ScoreDTO> scores) {
        this.scores = scores;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }


    @Override
    public void convert(String data) throws ConvertException {
        String [] lista=data.split(";");
        if(lista.length==3)
        {
            this.setID(lista[0]);
            this.setTeamID(lista[1]);
            this.setName(lista[2]);

        }
        else
        {
            throw new ConvertException(this.getClass().toString());
        }
    }
}
