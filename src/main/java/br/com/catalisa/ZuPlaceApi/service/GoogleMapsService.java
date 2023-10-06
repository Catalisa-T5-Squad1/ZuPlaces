package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.CoordsResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.DistanceRespondeDto;
import br.com.catalisa.ZuPlaceApi.dto.GoogleDistanceMatrixResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.GoogleGeocodeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GoogleMapsService {

    @Autowired
    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

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

    public DistanceRespondeDto calculateDistance(Double originLat, Double originLng, Double destinationLat, Double destinationLng) {
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
                    new DistanceRespondeDto(element.getDistance().getValue() / 1000.0) ;
                }
            }
        }
        return new DistanceRespondeDto(Double.MAX_VALUE);
    }

}
