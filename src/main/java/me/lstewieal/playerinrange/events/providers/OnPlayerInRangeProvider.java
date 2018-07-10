package me.lstewieal.playerinrange.events.providers;

import net.eq2online.macros.scripting.api.IMacroEventVariableProvider;

import java.util.HashSet;
import java.util.Set;

public class OnPlayerInRangeProvider implements IMacroEventVariableProvider {
    private int nearPlayerCount = 0;
    @Override
    public void initInstance(String[] instanceVariables) {
        try {
            this.nearPlayerCount = Integer.parseInt(instanceVariables[0]);
        } catch (NumberFormatException e) {
            this.nearPlayerCount = 0;
        }

    }

    @Override
    public void updateVariables(boolean clock) {}

    @Override
    public Object getVariable(String variableName) {
        if (variableName.equals("NEARPLAYERCOUNT")) {
            return this.nearPlayerCount;
        }
        return null;
    }

    @Override
    public Set<String> getVariables() {
        Set<String> variables = new HashSet();
        variables.add("NEARPLAYERCOUNT");
        return variables;
    }

    @Override
    public void onInit() {

    }

    public void setNearPlayerCount(int nearPlayerCount) {
        this.nearPlayerCount = nearPlayerCount;
    }
}
