package task9_7_1_part6_KakVVideo.commands;

import task9_7_1_part6_KakVVideo.commands.AppBotCommand;

public class BotCommonCommands {
    @task9_7_1_part6_KakVVideo.commands.AppBotCommand(name = "/hello", description = "when request hello", showInHelp = true)
    public String hello() {
        return "Hello, User";
    }

    @task9_7_1_part6_KakVVideo.commands.AppBotCommand(name = "/bye", description = "when request bye", showInHelp = true)
    public String bye() {
        return "Good bye, User";
    }

    @AppBotCommand(name = "/help", description = "when request help", showInKeyboard = true)
    public String help() {
        return "HEEEELP";
    }
}
