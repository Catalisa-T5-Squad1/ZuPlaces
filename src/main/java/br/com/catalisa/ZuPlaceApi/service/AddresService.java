package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.AddresResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.ZipCodeRequestDto;
import br.com.catalisa.ZuPlaceApi.model.AddresModel;
import br.com.catalisa.ZuPlaceApi.repository.AddresRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.MINUTES;


@Service
public class AddresService {

    private static final String viaCepUrl = "https://viacep.com.br/ws/";
    private static final Gson gson = new Gson();

    private static final Logger logger = LoggerFactory.getLogger(AddresService.class);

    @Autowired
    private AddresRepository addresRepository;

    @Autowired
    ModelMapper modelMapper;

    public AddresResponseDto findCep(ZipCodeRequestDto cepString) {
        try {
            logger.debug("Método findCep chamado com CEP: {}", cepString.getCep());

//            cepValidations.validaCep(cepString.cep());
//            cepValidations.removedorDeMascaraCep(cepString.cep());

            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.of(1, MINUTES))
                    .build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(viaCepUrl+cepString.getCep()+"/json"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            AddresModel addresModel = gson.fromJson(httpResponse.body(), AddresModel.class);

            AddresResponseDto addresResponseDto = modelMapper.map(addresModel, AddresResponseDto.class);

            addresRepository.save(addresModel);

            logger.info("CEP {} encontrado com sucesso", cepString.getCep());

            return addresResponseDto;

        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao buscar CEP: {}", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
