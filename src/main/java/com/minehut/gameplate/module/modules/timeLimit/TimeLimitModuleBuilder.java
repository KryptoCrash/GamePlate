package com.minehut.gameplate.module.modules.timeLimit;

import com.minehut.gameplate.match.Match;
import com.minehut.gameplate.module.Module;
import com.minehut.gameplate.module.ModuleBuilder;
import com.minehut.gameplate.module.ModuleCollection;
import com.minehut.gameplate.module.modules.team.TeamModule;
import com.minehut.gameplate.module.modules.teamManager.TeamManager;
import com.minehut.gameplate.util.Strings;
import org.jdom2.Element;

/**
 * Created by luke on 12/31/16.
 */
public class TimeLimitModuleBuilder extends ModuleBuilder {

    @Override
    public ModuleCollection<? extends Module> load(Match match) {

        for (Element timeLimitElement : match.getDocument().getRootElement().getChildren("timeLimit")) {
            int time = Strings.timeStringToSeconds(timeLimitElement.getTextNormalize());

            TeamModule teamModule = null;
            if (timeLimitElement.getAttributeValue("team") != null) {
                teamModule = TeamManager.getTeamById(timeLimitElement.getAttributeValue("team"));
            }

            return new ModuleCollection<>(new TimeLimitModule(time, teamModule));
        }

        return null;
    }
}
