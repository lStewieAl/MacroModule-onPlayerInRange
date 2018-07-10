package me.lstewieal.playerinrange.events.vars;

import me.lstewieal.playerinrange.ModuleInfo;
import net.eq2online.macros.scripting.ScriptedIterator;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.api.IScriptedIterator;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptedIteratorNearbyPlayers extends ScriptedIterator implements IScriptedIterator {

  private static final String NAME = "nearplayers";

  public ScriptedIteratorNearbyPlayers() {
    super(null, null);
  }

  public ScriptedIteratorNearbyPlayers(IScriptActionProvider provider, IMacro macro) {
    super(provider, macro);
    this.populate();
  }

  private void populate() {
    final List<EntityPlayer> nearPlayers = Minecraft.getMinecraft().world.playerEntities;

    for (EntityPlayer player : nearPlayers) {
      this.begin();
      this.add("NAME", player.getName());
      this.end();
    }
  }

  @Override
  public void onInit() {
    for (ScriptContext ctx : ScriptContext.getAvailableContexts()) {
      ctx.getCore().registerIterator(NAME, this.getClass());
    }
  }
}