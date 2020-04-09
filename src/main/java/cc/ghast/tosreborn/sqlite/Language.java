package cc.ghast.tosreborn.sqlite;

import org.jetbrains.annotations.NonNls;

public @interface Language {
    @NonNls
    String value();

    @NonNls
    String prefix() default "";

    @NonNls
    String suffix() default "";
}
