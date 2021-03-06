package com.company;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;


    //constructor
    public Block(String data, String previousHash){

        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();

    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String calculateHash(){

        return StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );

    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0');//Create a string with difficulty * "0"
        System.out.println("Target::::::::::::::::::::::::::: " + target);
        while (!hash.substring(0,difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }

        System.out.println("Block mined!!!! " + hash);
    }
}
