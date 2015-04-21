package com.therazzerapp.flymod.commands;

import com.therazzerapp.flymod.FlyMod;
import com.therazzerapp.flymod.FlyModCommand;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * Project: FlyMod
 * User: Pual
 * Date: 13/03/2015
 * Time: 15:40 PM
 * Package: com.therazzerapp.flymod.commands
 * E-Mail: rezzer101@googlemail.com
 */

public class FlySpeed implements FlyModCommand{

    @Override
    public void run(MessageReceiver caller, String[] args){

        String locale;
        if(caller instanceof Player){
            locale = caller.asPlayer().getLocale();
        } else {
            locale = "en_US";
        }

        if (Canary.getServer().getPlayer(args[1]) == null){
            caller.message(FlyMod.getTranslator().localeTranslate("error_PlayerNotFound",locale));
            return;
        } else if (!Canary.getServer().getPlayer(args[1]).isOnline()){
            caller.message(FlyMod.getTranslator().localeTranslate("error_PlayerNotOnline",locale));
            return;
        }

        try {
            if (Integer.parseInt(args[2]) > FlyMod.getSettings().getMaxFlySpeed() || Integer.parseInt(args[2]) <= 0){
                String temp = FlyMod.getTranslator().localeTranslate("error_WrongSpeedAmount", locale);
                temp = temp.replaceAll("%a", "" + FlyMod.getSettings().getMaxFlySpeed());
                caller.message(temp);
                return;
            }
        } catch (NumberFormatException e){
            String temp = FlyMod.getTranslator().localeTranslate("error_NotANumber", locale);
            temp = temp.replaceAll("%e",args[2]);
            temp = temp.replaceAll("%a", "" + FlyMod.getSettings().getMaxFlySpeed());
            caller.message(temp);
            return;
        }

        changeFlySpeed(Canary.getServer().getPlayer(args[1]),rechnung(Integer.parseInt(args[2])));
        caller.message(FlyMod.getTranslator().localeTranslate("speedChanged",locale));

    }

    public void changeFlySpeed(Player player, float speed){
        player.getCapabilities().setFlySpeed(speed);
        player.updateCapabilities();
    }

    private float rechnung(int speed){
        return (float)speed/100;
    }
}
