package com.therazzerapp.flymod;

import com.therazzerapp.flymod.commands.Fly;
import com.therazzerapp.flymod.commands.FlySpeed;
import com.therazzerapp.flymod.commands.FlySpeedReset;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

/**
 * Project: FlyMod
 * User: TheRaZZeRApp
 * Date: 12/03/2015
 * Time: 21:10 PM
 * Package: com.therazzerapp.flymod
 * E-Mail: rezzer101@googlemail.com
 */

public class CommandList implements CommandListener{

    static final FlyModCommand[] cmds = new FlyModCommand[3];

    static {
        cmds[0] = new Fly();
        cmds[1] = new FlySpeed();
        cmds[2] = new FlySpeedReset();
    }

    @Command(aliases = {"fly"},
            description = "Change players fly state",
            permissions = {"fly.fly"},
            toolTip = "/fly <player>",
            min = 1,
            max = 2
    )
    public void commandFly(MessageReceiver caller, String[] args){
        cmds[0].run(caller, args);
    }

    @Command(aliases = {"flyspeed"},
            description = "Change players fly speed",
            permissions = {"fly.speed.change"},
            toolTip = "/flyspeed <player> <speed>",
            min = 3,
            max = 4
    )
    public void commandFlySpeed(MessageReceiver caller, String[] args){
        cmds[1].run(caller, args);
    }

    @Command(aliases = {"reset"},
            parent = "flyspeed",
            description = "Sets players fly speed to default",
            permissions = {"fly.speed.reset"},
            toolTip = "/flyspeed reset <player>",
            min = 1,
            max = 3
    )
    public void commandResetFlySpeed(MessageReceiver caller, String[] args){
        cmds[2].run(caller, args);
    }
}
