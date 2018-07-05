package com.company;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class mambaChain {

    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static int difficulty = 5;


    public static void main(String[] args) {


//        Block genesisBlock = new Block("Block Chain","0");
//        System.out.println("Hash for block 1: " + genesisBlock.hash);
//
//loc
//        Block secondBlock = new Block("Yo am the second block",genesisBlock.hash);
//        System.out.println("Hash for block 2: " +  secondBlock.hash);
//
//        Block thirdBlock = new Block("Yo am the third block", secondBlock.hash);
//        System.out.println("Hash for block 3: " + thirdBlock.hash);

        //add blocks to the BlockChain ArrayList
        blockChain.add(new Block("Hi am first block","0"));
        System.out.println("Trying to mine block 1... ");
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("Yo am the second block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Trying to mine block 2...");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("Yoo am the third block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Trying to mine block 3...");
        blockChain.get(2).mineBlock(difficulty);


        System.out.println("\n      is block chain valid?: " + isChainValid());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("The block chain : ");
        System.out.println(blockChainJson);


    }

    public static boolean isChainValid(){

        Block currentBlock = null;
        Block previousBlock = null;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        //loop through the chain to compare hashes
        for (int i =1; i<blockChain.size(); i++){

            if (!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hash not equal");
                return false;

            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {

                System.out.println("Previous hash not equal");
                return false;
            }

            if (!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;


    }

}
