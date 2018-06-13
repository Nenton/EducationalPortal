package ru.innopolis.stc9.earth_stc9.exceptions;

/**
 * Кастомный класс исключения для контроллеров пользователей
 */
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
