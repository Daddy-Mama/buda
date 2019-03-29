package com.example.demo.service;

import com.example.demo.commands.Command;
import com.example.demo.commands.Commands;
import com.example.demo.interfaces.IMainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.commands.Commands.START;

@Service
public class MainMenuService extends BaseService {

    //    protected List<Command> allowableCommands = new ArrayList<>();
    protected List<String> allowableCommands = new ArrayList<>();
    private List<Integer> chatIdInServiceList = new ArrayList<>();
    private final List<String> menuButtonsList = List.of("Мой профиль", "Поиск аукциона", "Создать аукцион", "FAQ",
                                                         "Вопросы и предложения");
    protected ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();


    @Autowired
    public MainMenuService() {
//        setReplyKeyboardMarkup();
    }

    @Override
    public SendMessage execute(Update update) {
        return showMenu(update);
    }

    private SendMessage showMenu(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId(), " Privet, Che");
        sendMessage.setReplyMarkup(this.replyKeyboardMarkup);
        return sendMessage;
    }

    @Override
    protected void setReplyKeyboardMarkup() {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRowRow = new KeyboardRow();
        keyboardFirstRowRow.add(new KeyboardButton(menuButtonsList.get(0)));
        keyboard.add(keyboardFirstRowRow);

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(menuButtonsList.get(1)));
        keyboardSecondRow.add(new KeyboardButton(menuButtonsList.get(2)));

        // Третья строчка клавиатуры
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton(menuButtonsList.get(3)));
        keyboardThirdRow.add(new KeyboardButton(menuButtonsList.get(4)));


        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRowRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
