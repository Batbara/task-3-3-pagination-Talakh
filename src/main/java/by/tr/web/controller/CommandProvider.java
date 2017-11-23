package by.tr.web.controller;

import by.tr.web.controller.command.Command;
import by.tr.web.controller.command.impl.DOMParserCommand;
import by.tr.web.controller.command.impl.SAXParserCommand;
import by.tr.web.controller.command.impl.STAXParserCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.SAX, new SAXParserCommand());
        commands.put(CommandName.STAX, new STAXParserCommand());
        commands.put(CommandName.DOM, new DOMParserCommand());
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name);
        return commands.get(commandName);
    }

}
