package com.ishansong.ops.datahub.log;


import ch.qos.logback.core.joran.GenericConfigurator;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.Interpreter;
import ch.qos.logback.core.joran.spi.RuleStore;

import java.util.HashMap;

public class TrivialConfigurator extends GenericConfigurator {

    HashMap<ElementSelector, Action> rulesMap;

    public TrivialConfigurator(HashMap<ElementSelector, Action> rules) {
        this.rulesMap = rules;
    }



    @Override
    protected void addInstanceRules(RuleStore rs) {
        for (ElementSelector elementSelector : rulesMap.keySet()) {
            Action action = rulesMap.get(elementSelector);
            rs.addRule(elementSelector, action);
        }
    }

    @Override
    protected void addImplicitRules(Interpreter interpreter) {

    }

}