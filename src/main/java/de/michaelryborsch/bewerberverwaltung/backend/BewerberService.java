package de.michaelryborsch.bewerberverwaltung.backend;

import de.michaelryborsch.bewerberverwaltung.backend.model.Bewerber;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BewerberService {

    private final WebClient webClient;

    public BewerberService(WebClient.Builder builder)
    {
        webClient= builder.baseUrl("http://localhost:8080/").build();
    }

    public Bewerber[] getBewerber()
    {
        return webClient
                .get()
                .uri("/bewerber")
                .retrieve()
                .bodyToMono(Bewerber[].class).block();
    }

    public Bewerber getBewerberById(long id)
    {
        return webClient.get()
                .uri("/bewerber/"+id)
                .retrieve()
                .bodyToMono(Bewerber.class).block();

    }

    public Bewerber addBewerber(Bewerber bewerber)
    {
        return webClient.post().
                uri("/bewerber")
                .syncBody(bewerber)
                .retrieve()
                .bodyToMono(Bewerber.class).block();
    }

    public Bewerber updateBewerber(long id, Bewerber bewerber)
    {
        System.out.println(id);
        return webClient.put()
                .uri("/bewerber/"+id)
                .syncBody(bewerber)
                .retrieve()
                .bodyToMono(Bewerber.class)
                .block();
    }
    public String deleteBewerber(long id)
    {
        return webClient.delete()
                .uri("/bewerber/"+id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
