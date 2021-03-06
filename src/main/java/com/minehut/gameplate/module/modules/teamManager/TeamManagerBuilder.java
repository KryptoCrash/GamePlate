package com.minehut.gameplate.module.modules.teamManager;

import com.minehut.gameplate.match.Match;
import com.minehut.gameplate.module.*;
import com.minehut.gameplate.module.modules.team.TeamModule;
import com.minehut.gameplate.util.ChatUtil;
import com.minehut.gameplate.util.Numbers;
import com.minehut.gameplate.util.Parser;
import org.bukkit.ChatColor;
import org.jdom2.Element;

/**
 * Created by luke on 12/19/16.
 */

@BuilderData(load = ModuleLoadTime.EARLIER)
public class TeamManagerBuilder extends ModuleBuilder {

    @Override
    public ModuleCollection<? extends Module> load(Match match) {

        ModuleCollection results = new ModuleCollection();

        TeamManager.TeamType teamType = TeamManager.TeamType.STATIC;

        for (Element teamsElement : match.getDocument().getRootElement().getChildren("teams")) {
            if (teamsElement.getAttributeValue("mode") != null) {
                teamType = TeamManager.TeamType.valueOf(teamsElement.getAttributeValue("mode").toUpperCase().replace(" ", "_"));
            }

            results.add(new TeamManager(teamType));
            results.add(new TeamModule("observers", "Observers", true, ChatUtil.HIGHLIGHT, Integer.MAX_VALUE, Integer.MAX_VALUE, TeamModule.JoinAllowance.ALL));

            for (Element element : teamsElement.getChildren()) {
                String id = element.getAttributeValue("id");
                String name = element.getTextNormalize();
                ChatColor color = Parser.parseColor(element.getAttributeValue("color"));

                int maxPlayers = Numbers.parseInt(element.getAttributeValue("max"));
                int maxOverfill = maxPlayers;
                TeamModule.JoinAllowance joinAllowance = TeamModule.JoinAllowance.ALL;

                if (element.getAttributeValue("join") != null) {
                    joinAllowance = TeamModule.JoinAllowance.valueOf(element.getAttributeValue("join").toUpperCase().replace(" ", "_"));
                }
                if (element.getAttributeValue("overfill") != null) {
                    maxOverfill = Numbers.parseInt(element.getAttributeValue("overfill"));
                }

                TeamModule teamModule = new TeamModule(id, name, false, color, maxPlayers, maxOverfill, joinAllowance);
                results.add(teamModule);
            }
        }

        return results;
    }
}
