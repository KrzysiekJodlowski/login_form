package com.codecool.dao;


public interface SessionDAO {

    void createSession(int userId, String sessionId);
    void removeSession(String sessionId);
    int getUserBySessionId(String sessionId);

}
