package com.resliv.place.tg.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public abstract class AbstractCommand extends BotCommand {
  public AbstractCommand(String commandIdentifier, String description) {
    super(commandIdentifier, description);
  }

  void execute(AbsSender sender, SendMessage message, User user) {
    try {
      log.info("execute command with id: {}", getCommandIdentifier());
      sender.execute(message);
    } catch (TelegramApiException e) {
      log.error(
          "{}; userId: {}, commandIdentifier: {}",
          e.getMessage(),
          user.getId(),
          getCommandIdentifier());
    }
  }
}
