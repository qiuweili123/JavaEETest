package com.ns.springboothikaricp.grephql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class VersionQuery implements GraphQLQueryResolver {
    private String version;
}
