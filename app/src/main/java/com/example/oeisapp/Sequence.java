package com.example.oeisapp;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Sequence {
    public static final String TAG = "SEQ";
    public int number;
    public String id;
    public List<BigInteger> data;
    public String name;
    public List<String> comments, references, links;
    public List<String> formulas, mathematica, maple, program;
    public List<String> xrefs, keywords, offset, ext;
    public String author;
    public int numReferences;
    public int revision;
    public String time, created;

    @Override
    public String toString(){
        String str = number+": ";
        for(int i = 0; i < 10; i++){
            str += data.get(i)+", ";
        }
        str += "\n"+name;
        return str;
    }

    private List<List<Map<String, String>>> listViewChildren = new ArrayList<>();
    private List<Map<String, String>> groups = new ArrayList<>();
    void createAndSetTextViews(Activity activity){
        attachView(activity, "A%06d: "+name, number);
        String dataString = data.toString();
        attachView(activity, dataString.substring(1, dataString.length()-1));

        //TODO: look up more information on these properties so that we can determine how to best display them

        //TODO: wrap these lines in null checks
        attachViews("Comments: ", comments);
        attachViews("References: ", references);
        attachViews("Links: ", links); //TODO: turn links into actual links
        attachViews("Formulas: ", formulas); //TODO: format formulas
        attachViews("Mathematica: ", mathematica);
        attachViews("Maple: ", maple);
        attachViews("Program: ", program); //TODO: split into lines by program type in (parenthesis)
        attachViews("Xrefs: ", xrefs);
        attachViews("More Information: ", generateMoreInfoList());

        ExpandableListAdapter adapter = new SimpleExpandableListAdapter(activity, groups, R.layout.group_heading, new String[]{"GroupKey"}, new int[]{R.id.heading},
                listViewChildren, R.layout.child_item, new String[]{"Key"}, new int[]{R.id.child_item});
        ExpandableListView listView = activity.findViewById(R.id.info_list);
        listView.setAdapter(adapter);
    }

    private void attachView(Activity activity, String format, Object... args){
        TextView view = new TextView(activity);
        view.setText(String.format(Locale.ENGLISH, format, args));
        view.setTextColor(Color.BLACK);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        LinearLayout layout = activity.findViewById(R.id.basic_info);
        layout.addView(view);
    }
    private void attachViews(String title, List<String> elements){
        List<Map<String, String>> elementList = new ArrayList<>();
        for(String element : elements){
            Log.v(TAG, element);
            elementList.add(Collections.singletonMap("Key", element));
        }
        listViewChildren.add(elementList);
        groups.add(Collections.singletonMap("GroupKey", title));
    }

    private List<String> generateMoreInfoList(){
        List<String> list = new ArrayList<>();


        if(keywords != null) {
            String keywordString = keywords.toString();
            list.add("Keywords: " + keywordString.substring(1, keywordString.length() - 1));
        }

        if(offset != null) list.add("Offset: "+offset.toString());
        if(ext != null) list.add("Ext: "+ext.toString());

        if(author != null) list.add("Author: "+author);
        list.add("#References: "+numReferences);
        list.add("Revision Number: "+revision);
        if(time != null) list.add("Last Updated: "+time); //TODO: format times
        if(created != null) list.add("Created: "+created);

        return list;
    }
}
