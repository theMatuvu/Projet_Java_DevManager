package com.example.front_devmanager.service;

import com.example.front_devmanager.config.AppConfig;
import com.example.front_devmanager.model.Programmeur;
import com.example.front_devmanager.model.Projet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ApiService {
    private static final String BASE_URL = AppConfig.API_BASE_URL;
    private final HttpClient httpClient;
    private final Gson gson;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
        
        // Configuration de Gson avec les adaptateurs pour LocalDate
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, 
                    (JsonDeserializer<LocalDate>) (json, type, context) -> 
                        LocalDate.parse(json.getAsString()))
                .registerTypeAdapter(LocalDate.class, 
                    (JsonSerializer<LocalDate>) (date, type, context) -> 
                        context.serialize(date.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .create();
    }

    // ==================== PROGRAMMEURS ====================
    
    public List<Programmeur> getAllProgrammeurs() throws IOException, InterruptedException {
        String url = BASE_URL + "/programmeurs";
        System.out.println("API Call: GET " + url);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response Status: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
        
        if (response.statusCode() == 200) {
            Programmeur[] programmeurs = gson.fromJson(response.body(), Programmeur[].class);
            return Arrays.asList(programmeurs);
        }
        throw new IOException("Erreur HTTP " + response.statusCode() + ": " + response.body());
    }

    public Programmeur getProgrammeurById(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/programmeurs/" + id))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), Programmeur.class);
        }
        return null;
    }

    public void createProgrammeur(Programmeur programmeur) throws IOException, InterruptedException {
        String json = gson.toJson(programmeur);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/programmeurs"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 201) {
            throw new IOException("Erreur lors de la création du programmeur: " + response.body());
        }
    }

    public void updateProgrammeurSalaire(int id, double salaire) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/programmeurs/" + id + "/salaire?salaire=" + salaire))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("Erreur lors de la mise à jour du salaire: " + response.body());
        }
    }

    public void updateProgrammeur(Programmeur programmeur) throws IOException, InterruptedException {
        String json = gson.toJson(programmeur);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/programmeurs/" + programmeur.getId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200 && response.statusCode() != 204) {
            throw new IOException("Erreur lors de la mise à jour du programmeur: " + response.body());
        }
    }

    public void deleteProgrammeur(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/programmeurs/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("Erreur lors de la suppression du programmeur: " + response.body());
        }
    }

    // ==================== PROJETS ====================
    
    public List<Projet> getAllProjets() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/projets"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            Projet[] projets = gson.fromJson(response.body(), Projet[].class);
            return Arrays.asList(projets);
        }
        throw new IOException("Erreur lors de la récupération des projets: " + response.statusCode());
    }

    public Projet getProjetById(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/projets/" + id))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), Projet.class);
        }
        return null;
    }

    public void createProjet(Projet projet) throws IOException, InterruptedException {
        String json = gson.toJson(projet);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/projets"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 201) {
            throw new IOException("Erreur lors de la création du projet: " + response.body());
        }
    }

    public void updateProjet(Projet projet) throws IOException, InterruptedException {
        String json = gson.toJson(projet);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/projets/" + projet.getId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("Erreur lors de la mise à jour du projet: " + response.body());
        }
    }

    public void deleteProjet(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/projets/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("Erreur lors de la suppression du projet: " + response.body());
        }
    }
}
