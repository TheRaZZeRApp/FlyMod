package com.therazzerapp.flymod;

import com.therazzerapp.flymod.config.Config;
import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Project: FlyMod
 * User: TheRaZZeRApp
 * Date: 12/03/2015
 * Time: 21:10 PM
 * Package: com.therazzerapp.flymod
 * E-Mail: rezzer101@googlemail.com
 */

public class FlyMod extends Plugin{

    private static Translator translator;
    private static Settings settings;

    @Override
    public boolean enable() {

        File configDir = new File("./config/FlyMod/");
        if(!configDir.exists()){
            configDir.mkdir();
        }

        File configFile = new File("./config/FlyMod/config.json");
        if(!configFile.exists()){
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogman().info("Config file created");
            Config.createConfig(configFile);
        }
        settings = new Settings(Config.readJsonFile(configFile));
        getLogman().info("Config loaded!");

        try{
            translator = new Translator(true, "./lang/FlyMod", "en_US");
            getLogman().info("Language files loaded!");
        } catch (NullPointerException e){
            getLogman().error("No language files found, language is set to default (en_US)");
            translator = new Translator("en_US");
        }

        try {
            Canary.commands().registerCommands(new CommandList(),this,false);
        } catch (CommandDependencyException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void disable() {

    }

    public static Settings getSettings() {
        return settings;
    }

    public static Translator getTranslator() {
        return translator;
    }
}
