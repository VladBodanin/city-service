package com.resliv.place.tg.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class HelpCommand extends AbstractCommand {

  private static final String HELP_COMMAND_NAME = "help";
  private static final String HELP_COMMAND_DESCRIPTION = "help command\n";
  private static final String HELP_COMMAND_TEXT =
      "Use /find for search place by city name and country name. Example: /find Paris Belarus\n";

  public HelpCommand() {
    super(HELP_COMMAND_NAME, HELP_COMMAND_DESCRIPTION);
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    SendMessage message = new SendMessage();
    message.setChatId(chat.getId().toString());
    message.setText(HELP_COMMAND_TEXT);
    execute(absSender, message, user);
  }
}
