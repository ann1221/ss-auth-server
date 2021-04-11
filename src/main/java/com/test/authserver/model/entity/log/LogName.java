package com.test.authserver.model.entity.log;

public enum LogName {
    USER_AUTHENTICATION_FAILURE("Не удалось войти в систему, учетные данные неверны"),
    USER_AUTHENTICATION_SUCCEED("Пользователь вошел в систему"),
    USER_LOGOUT("Пользователь вышел из системы"),
    IP_BLOCKED("Попытака получения запроса с заблокированного ip");
    private final String full;

    LogName(String full){
        this.full = full;
    }

    public String getFull(){ return full;}
}

