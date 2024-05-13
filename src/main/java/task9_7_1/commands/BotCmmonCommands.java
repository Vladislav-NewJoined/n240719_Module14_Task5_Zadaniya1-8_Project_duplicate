package task9_7_1.commands;

public class BotCmmonCommands {

    @AppBotCommand(name = "/hello", description = "when request hello", showInHelp = true)
    String hello() {
        return "Hello, User";
    }

    @AppBotCommand(name = "/bye", description = "when request bye", showInHelp = true)
    String bye() {
        return "Good bye, User";
    }
}
