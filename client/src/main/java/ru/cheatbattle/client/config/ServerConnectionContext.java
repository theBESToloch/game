package ru.cheatbattle.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.cheatbattle.client.data.Game;
import ru.cheatbattle.client.schedule.ServerSynchronization;
import ru.cheatbattle.client.services.connection.Connection;
import ru.cheatbattle.client.services.connection.ConnectionWorker;
import ru.cheatbattle.client.services.connection.ConnectionWorkerImpl;
import ru.cheatbattle.client.services.connection.ServerConnectionService;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Configuration
@EnableScheduling
public class ServerConnectionContext {
    private final static String SERVER_IP = "127.0.0.1";
    private final static int SERVER_PORT = 8000;

    @Autowired
    private ServerConnectionService connectionService;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private Game game;

    @EventListener
    public void onContextRefreshEventHandler(ContextRefreshedEvent contextRefreshedEvent) {
        tryConfigureServerSync();
    }

    private void tryConfigureServerSync() {
        boolean isConfigure = configureServerSync(SERVER_IP, SERVER_PORT);
        if (!isConfigure) {
            log.debug("Can't connect to the server ip {}, port {}", SERVER_IP, SERVER_PORT);
            taskScheduler.schedule(this::tryConfigureServerSync, compileDateWithDelay());
        }
    }

    private Date compileDateWithDelay() {
        int delay = 5000;
        return new Date(Instant.now().toEpochMilli() + delay);
    }

    private boolean configureServerSync(String serverIp, int serverPort) {
        Optional<ConnectionWorker> connectionWorker = tryConnectToServer(serverIp, serverPort);
        if (connectionWorker.isPresent()) {
            ServerSynchronization serverSynchronization = new ServerSynchronization(connectionWorker.get(), game);

            serverSynchronization.sendCurrentState();

            taskScheduler.scheduleWithFixedDelay(serverSynchronization::sync, 33);
        }

        return connectionWorker.isPresent();
    }

    private Optional<ConnectionWorker> tryConnectToServer(String serverIp, int serverPort) {
        try {
            Connection connect = connectionService.connect(serverIp, serverPort);

            return Optional.of(new ConnectionWorkerImpl(connect));
        } catch (IOException e) {
            log.debug("error connect to server");
            return Optional.empty();
        }
    }
}
