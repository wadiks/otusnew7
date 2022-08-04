package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ApplicationShellCommands {

    @ShellMethod(value = "Help command", key = {"?"})
    public void help() {
        System.out.println("Список команд которые можно сделать.");
        System.out.println("Посмотреть весь каталог книг. Команда - books , bGetAll");
        System.out.println("Количество книг. Команда - c, count");
        System.out.println("Добавить книгу. Команда - ins, insert");
        System.out.println("Удалить книгу. Команда - del, delete");
        System.out.println("Найти книгу по id. Команда - bId, bGetId");
        System.out.println("Изменить название книги. Команда - rename");
        System.out.println("Количество авторов. Команда - a, aCount");
        System.out.println("Посмотреть всех аторов. Команда - authors , aGetAll");
        System.out.println("Найти автора по id. Команда - aId, aGetId");
        System.out.println("Количество жанров. Команда - g, gCount");
        System.out.println("Посмотреть все жанры. Команда - genres, gGetAll");
        System.out.println("Найти жанр по id. Команда - gId, gGetId");
        System.out.println("Посмотреть все коментарии к книге. Команда - comment , cGetAll");
        System.out.println("Количество коментариев. Команда - cc, countComment");
        System.out.println("Добавить коментарий. Команда - insCom, insertComment");
        System.out.println("Удалить коментарий. Команда - delCom, deleteComment");
        System.out.println("Найти коментарий по id. Команда - cId, cGetId");
        System.out.println("Изменить коментарий книги. Команда - cRename");
    }
}


