package PersonService.service;

import dto.notification.PersonOnline;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonReceiver {
    private final PersonService personService;

    @KafkaListener(topics = "PERSON_ONLINE")
    public void handlePersonOnline(PersonOnline personOnline) {
        personService.updateOnlineStatus(personOnline);
    }
}
