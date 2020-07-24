package com.example.oeisapp;

import androidx.annotation.NonNull;

public class SequenceWrapperJson {
    public String greeting, query, count, start;
    public SequenceJson[] results;

    @Override
    @NonNull
    public String toString(){
        String str = "\n";
        str += "Greeting: "+greeting+"\n";
        str += "Query: "+query+"\n";
        str += "Count: "+count+"\n";
        str += "Start: "+start;
        return str;
    }

     public Sequence getSequence(){
        return results[0].toSequence();
     }
}
