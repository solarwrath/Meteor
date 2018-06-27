package Meteor.core;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:Meteor/assets/config.properties"
})

public interface PropertiesConfig extends Config {
    @DefaultValue("jdbc:mysql://localhost:3306/unimanagement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
    @Key("db.url")
    String dbURL();

    @DefaultValue("root")
    @Key("db.user")
    String dbUser();

    @DefaultValue("")
    @Key("db.password")
    String dbPassword();

    @DefaultValue("meteor.com.dev@gmail.com")
    @Key("mail.from")
    String mailFrom();

    @DefaultValue("meteor.com.dev")
    @Key("mail.username")
    String mailUsername();

    @DefaultValue("71f46fec")
    @Key("mail.password")
    String mailPassword();

    @DefaultValue("smtp.gmail.com")
    @Key("mail.host")
    String mailHost();

    @DefaultValue("true")
    @Key("mail.smtp.auth")
    String mailAuth();

    @DefaultValue("true")
    @Key("mail.smtp.starttls.enable")
    String mailStartTLSEnable();

    @DefaultValue("587")
    @Key("mail.port")
    String mailPort();

}