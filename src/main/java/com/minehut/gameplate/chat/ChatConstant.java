package com.minehut.gameplate.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minehut.gameplate.GamePlate;
import org.bukkit.ChatColor;

public enum ChatConstant {

    UI_WELCOME("ui.welcome"),
    UI_TEAM_JOIN("ui.teamJoin"),
    UI_POTION_EFFECTS("ui.potionEffects"),
    UI_NO_POTION_EFFECTS("ui.noPotionEffects"),
    UI_HUNGER_LEVEL("ui.hungerLevel"),
    UI_HEALTH_LEVEL("ui.healthLevel"),
    UI_CYCLING_TIMER("ui.cycleTimer"),
    UI_CYCLED_TO("ui.cycledTo"),
    UI_SECOND("ui.second"),
    UI_SECONDS("ui.seconds"),
    UI_STARTING_TIMER("ui.startingTimer"),

    ERROR_JSON("error.json"),
    ERROR_INVENTORY_NOT_VIEWABLE("error.inventoryNotViewable"),
    ERROR_TEAM_FULL("error.teamFull"),
    ERROR_TEAM_OVERFLOWED("error.teamOverflowed"),
    ERROR_BUILD_HEIGHT("error.buildHeight"),
    ERROR_BLOCKED_CRAFT("error.blockedCraft"),
    ERROR_BLOCKED_PLACE("error.blockedPlace"),
    ERROR_NO_PERMISSION("error.noPermission"),
    ERROR_NUMBER_STRING("error.numberString"),
    ERROR_UNKNOWN_ERROR("error.unknownError"),
    ERROR_COMMAND_PLAYERS_ONLY("error.commandPlayerOnly"),
    ERROR_NO_TEAM_FOUND("error.noTeamFound");

    private final String path;

    ChatConstant(String path) {
        this.path = path;
    }

    public static ChatConstant fromPath(String path) {
        if (path != null) {
            for (ChatConstant chatConstant : ChatConstant.values()) {
                if (path.equalsIgnoreCase(chatConstant.path)) {
                    return chatConstant;
                }
            }
        }
        return null;
    }

    public String getMessage(String locale) {
        JsonObject localized = GamePlate.getInstance().getLocaleHandler().getLocaleDocument(locale.split("_")[0]);

        String split[] = this.path.split("\\.");
        String type = split[0];
        String id = split[1];

        for (JsonElement jsonElement : localized.getAsJsonArray("data")) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.get("type").getAsString().equalsIgnoreCase(type)) {
                for (JsonElement e : jsonObject.getAsJsonArray("messages")) {
                    JsonObject o = e.getAsJsonObject();
                    if (o.get("id").getAsString().equalsIgnoreCase(id)) {
                        return o.get("message").getAsString();
                    }
                }
            }
        }
        return getMessage("en_US");
    }

    public ChatMessage asMessage(ChatMessage... messages) {
        return new LocalizedChatMessage(this, messages);
    }

    public ChatMessage asMessage(ChatColor color, ChatMessage... messages) {
        return new UnlocalizedChatMessage(color + "{0}", asMessage(messages));
    }

}
