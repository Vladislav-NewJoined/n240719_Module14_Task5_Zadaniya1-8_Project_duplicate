package task9_7_1.commands;

public class BotCommonCommands {
    @BotCommand(name = "/hello", description = "when request hello", showInHelp = true)
    String hello() {
        return "Hello, User";
    }

    @BotCommand(name = "/bye", description = "when request bye", showInHelp = true)
    String bye() {
        return "Good bye, User";
    }
}
