package com.resliv.place.tg.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartCommand extends AbstractCommand {

  private static final String START_COMMAND_NAME = "start";
  private static final String START_COMMAND_DESCRIPTION = "start command\n";
  private static final String START_COMMAND_TEXT =
      "Hi, you started communication with the PlaceBot. More information you can get by /help command\n";

  public StartCommand() {
    super(START_COMMAND_NAME, START_COMMAND_DESCRIPTION);
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    SendMessage message = new SendMessage();
    message.setChatId(chat.getId().toString());
    message.setText(START_COMMAND_TEXT);
    execute(absSender, message, user);
  }
}
