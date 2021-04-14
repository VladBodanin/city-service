package com.resliv.place.tg.command;

import com.resliv.place.dto.CityDto;
import com.resliv.place.exception.PlaceBotException;
import com.resliv.place.service.CitySearchService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;
import java.util.Optional;

@Component
public class FindPlaceCommand extends AbstractCommand {

  private static final String FIND_PLACE_COMMAND_NAME = "find";
  private static final String FIND_PLACE_COMMAND_DESCRIPTION =
      "Find place by city name and country name. Example: /find Paris Belarus\n";
  private static final String AGRS_ERROR =
      "Error: 'find' command expects one or two args. First arg: <City>; Second optional arg: <Country>";

  private final CitySearchService citySearchService;

  public FindPlaceCommand(CitySearchService citySearchService) {
    super(FIND_PLACE_COMMAND_NAME, FIND_PLACE_COMMAND_DESCRIPTION);
    this.citySearchService = citySearchService;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    SendMessage message = new SendMessage();
    message.setChatId(chat.getId().toString());
    try {
      validateParams(strings);
      List<CityDto> places =
          citySearchService.search(
              Optional.of(strings[0]), Optional.ofNullable(strings.length > 1 ? strings[1] : null));
      message.setText(placeDtosToText(places));
    } catch (Exception e) {
      message.setText(e.getMessage());
    }
    execute(absSender, message, user);
  }

  private void validateParams(String[] args) throws PlaceBotException {
    if (args.length == 0) {
      throw new PlaceBotException(AGRS_ERROR);
    }
    if (args.length > 2) {
      throw new PlaceBotException(AGRS_ERROR);
    }
  }

  private String placeDtosToText(List<CityDto> places) {
    if (places.size() == 0) {
      return "Places not found";
    }

    StringBuilder stringBuilder = new StringBuilder();
    places.forEach(
        e -> stringBuilder.append(placeDtoToText(e)).append("\n----------------------------\n"));

    return stringBuilder.toString();
  }

  private String placeDtoToText(CityDto place) {
    return new StringBuilder()
        .append(place.getName())
        .append(", ")
        .append(place.getCountry().getName())
        .append("\nAbout place: ")
        .append(place.getDescription())
        .toString();
  }
}
