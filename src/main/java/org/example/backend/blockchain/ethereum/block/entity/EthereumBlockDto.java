package org.example.backend.blockchain.ethereum.block.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EthereumBlockDto {

    private String baseFeePerGas;
    private String blobGasUsed;
    private String difficulty;
    private String excessBlobGas;
    private String extraData;
    private String gasLimit;
    private String gasUsed;
    private String hash;
    private String logsBloom;
    private String miner;
    private String blockReward;
    private String mixHash;
    private String nonce;
    private String number;
    private String parentBeaconBlockRoot;
    private String parentHash;
    private String receiptsRoot;
    private String sha3Uncles;
    private String size;
    private String stateRoot;
    private String timestamp;
    private String totalDifficulty;
    private List<String> transactions;  // List of transaction hashes
    private String transactionsRoot;
    private List<WithdrawalDto> withdrawals;  // List of withdrawal objects
    private String withdrawalsRoot;

    public EthereumBlockDto() {

    }
}
