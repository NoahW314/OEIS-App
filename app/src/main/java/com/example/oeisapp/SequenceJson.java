package com.example.oeisapp;

import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.oeisapp.Sequence.TAG;

public class SequenceJson {
    private int number;
    private String id, data, name;
    private String[] comment, reference, link;
    private String[] formula, mathematica, maple, program;
    private String[] xref, ext;
    private String references, keyword, offset, author;
    private int revision;
    private String time, created;

    public Sequence toSequence(){
        Sequence seq = new Sequence();
        seq.number = number;
        seq.id = id;
        seq.data = parseStringList(Arrays.asList(data.split(",")));
        seq.name = name;


        if(comment != null) seq.comments = Arrays.asList(comment);
        else Log.v(TAG, "Comment is null!");
        if(reference != null) seq.references = Arrays.asList(reference);
        if(link != null) seq.links = Arrays.asList(link);

        if(formula != null) seq.formulas = Arrays.asList(formula);
        if(mathematica != null) seq.mathematica = Arrays.asList(mathematica);
        if(maple != null) seq.maple = Arrays.asList(maple);
        if(program != null) seq.program = Arrays.asList(program);

        if(xref != null) seq.xrefs = Arrays.asList(xref);
        if(keyword != null) seq.keywords = Arrays.asList(keyword.split(","));
        if(offset != null) seq.offset = Arrays.asList(offset.split(","));
        if(ext != null) seq.ext = Arrays.asList(ext);

        seq.author = author;
        if(references != null) seq.numReferences = Integer.parseInt(references);
        seq.revision = revision;
        seq.time = time;
        seq.created = created;

        return seq;
    }

    private static List<BigInteger> parseStringList(List<String> list){
        List<BigInteger> intList = new ArrayList<>();
        for(String str : list){
            intList.add(new BigInteger(str));
        }
        return intList;
    }
}
