package com.mijuamon.core.constants;

import com.mijuamon.core.model.DTO.MatchDTO;
import com.mijuamon.core.model.DTO.PlayerDTO;
import com.mijuamon.core.model.DTO.ScoreDTO;
import com.mijuamon.core.model.DTO.TeamDTO;
import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

public class Converters {

    public static PlayerModel convert(PlayerDTO source)
    {
        return new PlayerModel(source.getName());
    }

    public static TeamModel convert (TeamDTO source)
    {
        return new TeamModel(source.getName());
    }
    public static ScoreModel convert (ScoreDTO source)
    {
        return new ScoreModel(source.getScore());
    }
    public static MatchModel convert (MatchDTO source)
    {
        return new MatchModel(source.getYear(), source.getJourney(), source.getResult());
    }
}
