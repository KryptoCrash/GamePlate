package com.minehut.gameplate.map;


import com.google.gson.JsonObject;

import java.io.File;
import java.util.List;

/**
 * Created by luke on 12/19/16.
 */
public class LoadedMap {
    private String name, objective, version;
    private List<Contributor> authors, contributors;
    private List<String> rules;

    private File folder;
    private JsonObject jsonObject;

    public LoadedMap(String name, String objective, String version, List<Contributor> authors, List<Contributor> contributors, List<String> rules, File folder, JsonObject jsonObject) {
        this.name = name;
        this.objective = objective;
        this.version = version;
        this.authors = authors;
        this.contributors = contributors;
        this.rules = rules;
        this.folder = folder;
        this.jsonObject = jsonObject;
    }

    public String getName() {
        return name;
    }

    public String getObjective() {
        return objective;
    }

    public String getVersion() {
        return version;
    }

    public List<Contributor> getAuthors() {
        return authors;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public List<String> getRules() {
        return rules;
    }

    public File getFolder() {
        return folder;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}
