package org.springframework.boot.logging.logback;

import ch.qos.logback.core.joran.action.IncludeAction;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.RuleStore;
import com.ishansong.ops.datahub.configurator.SpringIncludeAction;
import org.springframework.boot.logging.LoggingInitializationContext;

public class SpringBootExtJoranConfigurator extends SpringBootJoranConfigurator {
    public SpringBootExtJoranConfigurator(LoggingInitializationContext initializationContext) {
        super(initializationContext);
    }

    @Override
    public void addInstanceRules(RuleStore rs){
        super.addInstanceRules(rs);
        rs.addRule(new ElementSelector("configuration/springInclude"), new SpringIncludeAction());
    }
}
