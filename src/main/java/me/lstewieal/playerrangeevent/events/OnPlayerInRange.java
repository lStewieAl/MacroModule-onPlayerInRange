package me.lstewieal.playerrangeevent.events;

import net.eq2online.macros.compatibility.IconTiled;
import net.eq2online.macros.event.MacroEvent;
import net.eq2online.macros.res.ResourceLocations;
import net.eq2online.macros.scripting.api.IMacroEventProvider;

class OnPlayerInRange extends MacroEvent {
    OnPlayerInRange(IMacroEventProvider provider) {
        super(provider, "onPlayerInRange", true, "world", new IconTiled(ResourceLocations.EXT, 9, 216, 0, 24, 24, 256, 256));
    }
}
