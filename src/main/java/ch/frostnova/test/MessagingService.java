package ch.frostnova.test;

import java.util.List;

/**
 * Messaging service, can send and receive string messages
 *
 * @author wap
 * @since 01.03.2018
 */
public interface MessagingService {

    /**
     * Send a message
     *
     * @param message message
     */
    void send(String message);

    /**
     * Get a list of all messages received yet
     *
     * @return list of received messages
     */
    List<String> getMessages();
}
