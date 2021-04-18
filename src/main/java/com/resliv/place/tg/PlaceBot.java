package com.resliv.place.tg;

import com.resliv.place.tg.command.AbstractCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
public class PlaceBot extends TelegramLongPollingCommandBot {

  private static final String NON_COMMAND_UPDATE_TEXT =
      "I don't understand you. Could you try '/help' command to get more information";

  @Value("${telegram.bot.name}")
  private String botName;

  @Value("${telegram.bot.token}")
  private String botToken;

  public PlaceBot(
      List<? extends AbstractCommand> commands, BiConsumer<AbsSender, Message> defaultAction) {
    super();
    commands.forEach(this::register);
    registerDefaultAction(defaultAction);
  }

  @Override
  public String getBotUsername() {
    return botName;
  }

  @Override
  public void processNonCommandUpdate(Update update) {
    Message msg = update.getMessage();
    Long chatId = msg.getChatId();

    SendMessage answer = new SendMessage();
    answer.setText(NON_COMMAND_UPDATE_TEXT);
    answer.setChatId(chatId.toString());
    try {
      execute(answer);
    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}
