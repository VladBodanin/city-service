package com.resliv.place.config;

import com.resliv.place.tg.PlaceBot;
import com.resliv.place.tg.command.HelpCommand;
import com.resliv.place.tg.command.FindPlaceCommand;
import com.resliv.place.tg.command.StartCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
@Configuration
public class BotConfig {

  @Bean
  public PlaceBot placeBot(
      StartCommand startCommand, HelpCommand helpCommand, FindPlaceCommand findPlaceCommand) {
    return new PlaceBot(List.of(startCommand, helpCommand, findPlaceCommand), getDefaultAction());
  }

  @Bean
  public TelegramBotsApi telegramBotsApi(PlaceBot placeBot) {
    TelegramBotsApi botsApi = null;
    try {
      botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(placeBot);

    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
    return botsApi;
  }

  private BiConsumer<AbsSender, Message> getDefaultAction() {
    return (absSender, message) -> {
      SendMessage text = new SendMessage();
      text.setChatId(String.valueOf(message.getChatId()));
      text.setText(message.getText() + " Command not found!");

      try {
        absSender.execute(text);
      } catch (TelegramApiException e) {
        log.error("Error while replying unknown command to user. {}", message.getFrom());
      }
    };
  }
}
