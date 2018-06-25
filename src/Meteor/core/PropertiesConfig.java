package Meteor.core;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:Meteor/assets/config.properties"
})

public interface PropertiesConfig extends Config {
    @DefaultValue("jdbc:mysql://localhost:3306/unimanagement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
    @Key("db.url")
    String URL();

    @DefaultValue("root")
    @Key("db.user")
    String user();

    @DefaultValue("")
    @Key("db.password")
    String password();
}