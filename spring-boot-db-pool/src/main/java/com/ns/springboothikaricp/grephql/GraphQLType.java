package com.ns.springboothikaricp.grephql;

import graphql.Scalars;
import graphql.language.FieldDefinition;
import graphql.schema.GraphQLObjectType;

import java.io.File;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class GraphQLType {
    public static GraphQLObjectType userGraphQLType = newObject().name("user")
            .field(newFieldDefinition().name("id").type(Scalars.GraphQLLong).build())// 类型一定要与结构类字段的类型相对应
            .field(newFieldDefinition().name("userName").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("userAge").type(Scalars.GraphQLInt).build()).build();

  /*  public static GraphQLObjectType info = newObject().name("info")
            .field(newFieldDefinition().name("id").type(Scalars.GraphQLInt).build())// 类型一定要与结构类字段的类型相对应
            .field(newFieldDefinition().name("truename").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("address").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("user").type(user).build()).build();*/
 }
