package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.CoordsResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.GoogleDistanceMatrixResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.GoogleGeocodeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleMapsService {

    @Autowired
    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GoogleMapsService() {
        this.restTemplate = new RestTemplate();
    }

    public CoordsResponseDto geocodeAddress(String address) {
        String geocodingBaseUrl = "https://maps.googleapis.com/maps/api/geocode/json";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(geocodingBaseUrl)
                .queryParam("address", address)
                .queryParam("key", apiKey);

        GoogleGeocodeResponseDto response = restTemplate.getForObject(builder.toUriString(), GoogleGeocodeResponseDto.class);

        if (response != null && response.getResults() != null && response.getResults().length > 0) {
            GoogleGeocodeResponseDto.Result result = response.getResults()[0];
            double latitude = result.getGeometry().getLocation().getLat();
            double longitude = result.getGeometry().getLocation().getLng();
            return new CoordsResponseDto(latitude, longitude);
        }
        return null;
    }

    public double calculateDistance(Double originLat, Double originLng, Double destinationLat, Double destinationLng) {
        String distanceMatrixBaseUrl = "https://maps.googleapis.com/maps/api/distancematrix/json";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(distanceMatrixBaseUrl)
                .queryParam("origins", originLat + "," + originLng)
                .queryParam("destinations", destinationLat + "," + destinationLng)
                .queryParam("key", apiKey);

        GoogleDistanceMatrixResponseDto response = restTemplate.getForObject(builder.toUriString(), GoogleDistanceMatrixResponseDto.class);

        if (response != null && response.getRows() != null && response.getRows().length > 0) {
            GoogleDistanceMatrixResponseDto.Row row = response.getRows()[0];
            if (row.getElements() != null && row.getElements().length > 0) {
                GoogleDistanceMatrixResponseDto.Element element = row.getElements()[0];
                if (element.getStatus().equals("OK")) {
                    return element.getDistance().getValue() / 1000.0;
                }
            }
        }
        return Double.MAX_VALUE;
    }
}
