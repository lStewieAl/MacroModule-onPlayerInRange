package me.lstewieal.playerinrange.events;

import me.lstewieal.playerinrange.ModuleInfo;
import me.lstewieal.playerinrange.events.providers.OnPlayerInRangeProvider;
import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

@APIVersion(ModuleInfo.API_VERSION)
public class EventProviderPlayerInRange implements IMacroEventDispatcher, IMacroEventProvider {
    private ArrayList<String> help = new ArrayList<>();
    private IMacroEvent event;
    private OnPlayerInRangeProvider provider;

    /*
    *  setup event provider
    * */
    public EventProviderPlayerInRange() {
        this.event = new OnPlayerInRange(this);
    }



    @Override
    public void onTick(IMacroEventManager manager, Minecraft minecraft) {
        int nearPlayerCount = minecraft.world.playerEntities.size();
        // check the last tick positions aren't the default values
        if(nearPlayerCount > 1) {
            /* give distance to variable provider and fire event! */
            provider.setNearPlayerCount(nearPlayerCount);
            manager.sendEvent("onPlayerInRange", 10, Integer.toString(nearPlayerCount));
        }
    }

    @Override
    public IMacroEventDispatcher getDispatcher() {
        return this;
    }

    @Override
    public void registerEvents(IMacroEventManager manager) {
      manager.registerEvent(this.event);
    }

    @Override
    public List<String> getHelp(IMacroEvent macroEvent) {
        return this.help;
    }

    @Override
    public void onInit() {
      setupHelp();

      /* register event provider (to add event to in-game events list) */
      ScriptContext.MAIN.getCore().registerEventProvider(this);

      /* setup variable provider (for %NEARPLAYERCOUNT% etc.) */
      this.provider = new OnPlayerInRangeProvider();
      ScriptContext.MAIN.getCore().getScriptActionProvider().registerVariableProvider(this.provider);
    }

    private void setupHelp() {
        help.add("§f<" + event.getName() + ">");
        help.add("This event is raised if there are any other players rendered.");
        help.add("You can access the number of rendered players using the");
        help.add("§CNEARPLAYERCOUNT§r global variable.");
        help.add("§k:::§r §4§lMade By lStewieAl§r §k:::");
    }
}

