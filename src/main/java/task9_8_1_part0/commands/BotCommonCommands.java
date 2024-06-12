package task9_8_1_part0.commands;

import task9_8_1_part0.commands.AppBotCommand;

public class BotCommonCommands {
    @task9_8_1_part0.commands.AppBotCommand(name = "/hello", description = "when request hello", showInHelp = true)
//    @task9_8_1_part0.commands.AppBotCommand(name = "/hello", description = "when request hello", showInHelp = true)
    public String hello() {
        return "Hello, User";
    }

    @task9_8_1_part0.commands.AppBotCommand(name = "/bye", description = "when request bye", showInHelp = true)
//    @task9_8_1_part0.commands.AppBotCommand(name = "/bye", description = "when request bye", showInHelp = true)
    public String bye() {
        return "Good bye, User";
    }

    @AppBotCommand(name = "/help", description = "when request help", showInKeyboard = true)
    public String help() {
        return "HEEEELP";
    }
}
