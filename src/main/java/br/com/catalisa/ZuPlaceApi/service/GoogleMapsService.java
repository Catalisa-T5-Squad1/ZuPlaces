package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.CoordsResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.DistanceRespondeDto;
import br.com.catalisa.ZuPlaceApi.dto.GoogleDistanceMatrixResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.GoogleGeocodeResponseDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


@Service
public class GoogleMapsService {

    @Autowired
    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private static final Gson gson = new Gson();

    public GoogleMapsService() {
        this.restTemplate = new RestTemplate();
    }


    public String encodeAddressToURL(String address) {
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String baseURL = "https://maps.googleapis.com/maps/api/geocode/json";
        return baseURL + "?address=" + encodedAddress + "&key=" + apiKey;
    }

    public CoordsResponseDto geocodeAddress(String address) {
        String url = encodeAddressToURL(address);

        if (url != null) {
            GoogleGeocodeResponseDto response = restTemplate.getForObject(url, GoogleGeocodeResponseDto.class);

            if (response != null && response.getResults() != null && response.getResults().length > 0) {
                GoogleGeocodeResponseDto.Result result = response.getResults()[0];
                double latitude = result.getGeometry().getLocation().getLat();
                double longitude = result.getGeometry().getLocation().getLng();
                return new CoordsResponseDto(latitude, longitude);
            }
        }
        return null;
    }

    public GoogleDistanceMatrixResponseDto getDistanceBetweenCeps(Double originLat, Double originLng, Double destinationLat, Double destinationLng) {
        try {

            //logger.debug("Método getDistanceBetweenCeps chamado com destinoCep: {}", destinoCep);

            String url = "https://maps.googleapis.com/maps/api/distancematrix/json"
                    + "?origins=" + originLat + "," + originLng
                    + "&destinations=" + destinationLat + "," + destinationLng
                    + "&key=" + apiKey;

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            GoogleDistanceMatrixResponseDto googleDistanceMatrixResponseDto = gson.fromJson(httpResponse.body(), GoogleDistanceMatrixResponseDto.class);

            if (googleDistanceMatrixResponseDto != null && googleDistanceMatrixResponseDto.getRows() != null && googleDistanceMatrixResponseDto.getRows().length > 0) {
                GoogleDistanceMatrixResponseDto.Row row = googleDistanceMatrixResponseDto.getRows()[0];
                if (row.getElements() != null && row.getElements().length > 0) {
                    GoogleDistanceMatrixResponseDto.Element element = row.getElements()[0];
                    GoogleDistanceMatrixResponseDto.Distance distance = element.getDistance();
                    if (distance != null) {
                        Integer distanceValue = distance.getValue();
                        System.out.println("Valor de 'value': " + distanceValue);
                    }
                }
            }

            if (httpResponse.statusCode() == 200) {
                return googleDistanceMatrixResponseDto;
            } else {
               // logger.error("A solicitação falhou com código de status: {}", httpResponse.statusCode());
                throw new RuntimeException("A solicitação falhou com código de status: " + httpResponse.statusCode());
            }
        } catch (IOException | InterruptedException e) {
           // logger.error("Erro ao buscar distância entre CEPs: {}", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
