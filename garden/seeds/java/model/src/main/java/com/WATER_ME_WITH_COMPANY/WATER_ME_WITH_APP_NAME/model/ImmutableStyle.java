package com.{{company_name}}.{{app_package_name_prefix}}.model;

import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
        typeImmutable = "*",
        build = "create",
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        jdkOnly = true,
        defaults = @Value.Immutable
)
@interface ImmutableStyle {
}
