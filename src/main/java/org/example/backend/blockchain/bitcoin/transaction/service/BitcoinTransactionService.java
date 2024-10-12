package org.example.backend.blockchain.bitcoin.transaction.service;

import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.output.BitcoinTransactionOutputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransactionDto;
import org.example.backend.blockchain.bitcoin.transaction.mapper.BitcoinTransactionMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class BitcoinTransactionService {

    private final RestTemplate restTemplate;

    public BitcoinTransactionService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BitcoinTransactionDto getTransactionByHash(String hash) {

        String url = "https://api.blockcypher.com/v1/btc/main/txs/" + hash;

        try {
            Optional<BitcoinTransaction> optionalBitcoinTransaction = Optional.ofNullable(
                    restTemplate.getForObject(url, BitcoinTransaction.class));
            if (optionalBitcoinTransaction.isPresent()) {
                return BitcoinTransactionMapper.mapTransactionToTransactionDto(optionalBitcoinTransaction.get());
            } else {
                return null;
            }
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    // Returns details of the inputs of a specific Bitcoin transaction.
    public List<BitcoinTransactionInputDto> getTransactionInput(String txHash) {
        BitcoinTransactionDto transactionDto = getTransactionByHash(txHash);

        if (transactionDto != null && transactionDto.getBitcoinTransactionInputsDto() != null) {
            return transactionDto.getBitcoinTransactionInputsDto();
        } else {
            return null;
        }
    }

    // Returns details of the outputs of a specific Bitcoin transaction.
    public List<BitcoinTransactionOutputDto> getTransactionOutput(String txHash) {
        BitcoinTransactionDto transactionDto = getTransactionByHash(txHash);

        if (transactionDto != null && transactionDto.getBitcoinTransactionOutputsDto() != null) {
            return transactionDto.getBitcoinTransactionOutputsDto();
        } else {
            return null;
        }
    }

    // Returns the confidence level of a transaction being included in the blockchain.
    public Float getTransactionConfidence(String txHash) {
        BitcoinTransactionDto transactionDto = getTransactionByHash(txHash);

        if (transactionDto != null) {
            return transactionDto.getConfidence();
        } else {
            return null;
        }
    }

}
