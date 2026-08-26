package com.duabyaksha.commentpicker;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    EditText comments, keyword, winners;
    TextView result, count, winnerLabel;
    CheckBox unique;
    int purple = Color.rgb(124,58,237), pink = Color.rgb(236,72,153), dark = Color.rgb(23,17,31), muted = Color.rgb(107,100,115);

    int dp(float v){ return (int)(v * getResources().getDisplayMetrics().density + .5f); }
    TextView text(String s, float sp, int color, boolean bold){
        TextView t = new TextView(this); t.setText(s); t.setTextSize(sp); t.setTextColor(color); t.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL); return t;
    }
    GradientDrawable bg(int color, float radius){ GradientDrawable g=new GradientDrawable(); g.setColor(color); g.setCornerRadius(dp(radius)); return g; }
    GradientDrawable gradient(){ GradientDrawable g=new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{purple,pink}); g.setCornerRadius(dp(16)); return g; }
    Button button(String label, boolean primary){
        Button b=new Button(this); b.setText(label); b.setTextSize(15); b.setAllCaps(false); b.setTypeface(Typeface.DEFAULT,Typeface.BOLD); b.setPadding(dp(14),0,dp(14),0);
        b.setBackground(primary ? gradient() : bg(Color.rgb(241,235,255),14)); b.setTextColor(primary ? Color.WHITE : purple); b.setMinHeight(dp(50)); return b;
    }
    LinearLayout card(){ LinearLayout c=new LinearLayout(this); c.setOrientation(LinearLayout.VERTICAL); c.setPadding(dp(18),dp(18),dp(18),dp(18)); c.setBackground(bg(Color.WHITE,18)); return c; }
    void margin(View v,int l,int t,int r,int b){ LinearLayout.LayoutParams p=(LinearLayout.LayoutParams)v.getLayoutParams(); if(p==null)p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(dp(l),dp(t),dp(r),dp(b)); v.setLayoutParams(p); }

    @Override public void onCreate(Bundle b){ super.onCreate(b); getWindow().setStatusBarColor(purple); build(); }

    void build(){
        ScrollView scroll=new ScrollView(this); scroll.setFillViewport(true); scroll.setBackgroundColor(Color.rgb(250,248,252));
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(18),dp(18),dp(18),dp(28)); scroll.addView(root);

        LinearLayout header=new LinearLayout(this); header.setGravity(Gravity.CENTER_VERTICAL); header.setPadding(dp(18),dp(16),dp(18),dp(16)); header.setBackground(gradient());
        ImageView logo=new ImageView(this); logo.setImageResource(com.duabyaksha.commentpicker.R.drawable.logo_dua); header.addView(logo,new LinearLayout.LayoutParams(dp(56),dp(56)));
        LinearLayout brand=new LinearLayout(this); brand.setOrientation(LinearLayout.VERTICAL); brand.setPadding(dp(14),0,0,0);
        brand.addView(text("Dua by Aksha",21,Color.WHITE,true)); brand.addView(text("COMMENT GIVEAWAY",12,Color.rgb(253,231,247),true));
        header.addView(brand,new LinearLayout.LayoutParams(0,-2,1)); root.addView(header);

        TextView intro=text("Pick your giveaway winner",24,dark,true); margin(intro,2,22,2,3); root.addView(intro);
        TextView hint=text("Fair, simple and ready for your next Instagram giveaway.",14,muted,false); root.addView(hint);

        LinearLayout commentsCard=card(); margin(commentsCard,0,18,0,0); root.addView(commentsCard);
        commentsCard.addView(text("1  •  ADD COMMENTS",13,purple,true));
        TextView helper=text("Paste one comment per line. Format: @username: comment",13,muted,false); margin(helper,0,6,0,8); commentsCard.addView(helper);
        comments=new EditText(this); comments.setHint("@username: Love this!\n@anotheruser: Hope I win"); comments.setGravity(Gravity.TOP|Gravity.START); comments.setMinLines(7); comments.setInputType(0x00020001|0x00000001); comments.setBackgroundResource(R.drawable.bg_input); commentsCard.addView(comments,new LinearLayout.LayoutParams(-1,dp(150)));
        Button demo=button("Load demo comments",false); margin(demo,0,10,0,0); commentsCard.addView(demo); demo.setOnClickListener(v->comments.setText("@anu: Love this!\n@riya: Amazing giveaway #giveaway\n@anu: My second comment\n@megha: Joining!\n@fathima: Beautiful collection\n@riya: Hope I win\n@shalu: Love it"));

        LinearLayout rules=card(); margin(rules,0,14,0,0); root.addView(rules);
        rules.addView(text("2  •  GIVEAWAY RULES",13,purple,true));
        TextView keyLabel=text("Optional keyword / hashtag",13,muted,false); margin(keyLabel,0,12,0,5); rules.addView(keyLabel);
        keyword=new EditText(this); keyword.setHint("e.g. #giveaway"); keyword.setSingleLine(true); keyword.setBackgroundResource(R.drawable.bg_input); rules.addView(keyword);
        unique=new CheckBox(this); unique.setText("One entry per username"); unique.setTextSize(14); unique.setTextColor(dark); unique.setChecked(true); margin(unique,0,8,0,0); rules.addView(unique);
        LinearLayout winRow=new LinearLayout(this); winRow.setGravity(Gravity.CENTER_VERTICAL); winRow.addView(text("Number of winners",14,dark,false),new LinearLayout.LayoutParams(0,-2,1));
        winners=new EditText(this); winners.setText("1"); winners.setInputType(2); winners.setGravity(Gravity.CENTER); winners.setBackgroundResource(R.drawable.bg_input); winRow.addView(winners,new LinearLayout.LayoutParams(dp(72),dp(48))); rules.addView(winRow);

        LinearLayout action=card(); margin(action,0,14,0,0); root.addView(action);
        count=text("0 eligible comments",13,muted,false); count.setGravity(Gravity.CENTER); action.addView(count);
        Button pick=button("🎉  PICK WINNER",true); margin(pick,0,10,0,0); action.addView(pick); pick.setOnClickListener(v->pickWinners());

        LinearLayout winnerCard=card(); margin(winnerCard,0,14,0,0); root.addView(winnerCard);
        winnerLabel=text("WINNER RESULT",12,purple,true); winnerLabel.setGravity(Gravity.CENTER); winnerCard.addView(winnerLabel);
        result=text("Your winner will appear here",20,dark,true); result.setGravity(Gravity.CENTER); result.setPadding(dp(10),dp(26),dp(10),dp(26)); winnerCard.addView(result);
        Button share=button("Share result",false); winnerCard.addView(share); share.setOnClickListener(v->shareResult());
        Button clear=button("Clear all",false); margin(clear,0,14,0,0); root.addView(clear); clear.setOnClickListener(v->{comments.setText(""); keyword.setText(""); winners.setText("1"); result.setText("Your winner will appear here"); count.setText("0 eligible comments");});

        TextView footer=text("Dua by Aksha  •  Giveaway Comment Picker",12,muted,false); footer.setGravity(Gravity.CENTER); margin(footer,0,8,0,0); root.addView(footer);
        setContentView(scroll);
    }

    ArrayList<String> eligible(){
        String raw=comments.getText().toString().trim(); ArrayList<String> list=new ArrayList<>(); if(raw.isEmpty()) return list;
        String key=keyword.getText().toString().trim().toLowerCase(Locale.ROOT); HashSet<String> users=new HashSet<>();
        for(String line:raw.split("\\r?\\n")){ line=line.trim(); if(line.isEmpty())continue; if(!key.isEmpty()&&!line.toLowerCase(Locale.ROOT).contains(key))continue;
            String user=line; int c=line.indexOf(':'); if(c>0)user=line.substring(0,c).trim();
            if(unique.isChecked()&&!users.add(user.toLowerCase(Locale.ROOT)))continue; list.add(line);
        } return list;
    }
    void pickWinners(){
        ArrayList<String> list=eligible(); count.setText(list.size()+" eligible comments"); int n=1;
        try{n=Math.max(1,Integer.parseInt(winners.getText().toString()));}catch(Exception ignored){}
        if(list.isEmpty()){result.setText("No eligible comments found."); return;}
        Collections.shuffle(list); n=Math.min(n,list.size());
        StringBuilder out=new StringBuilder(); for(int i=0;i<n;i++) out.append(i+1).append(". ").append(list.get(i)).append("\n");
        result.setText(out.toString().trim()); winnerLabel.setText(n==1?"🎉  WINNER  🎉":"🎉  WINNERS  🎉");
        Animation a=new AlphaAnimation(.15f,1f); a.setDuration(450); result.startAnimation(a);
    }
    void shareResult(){
        String body="Dua by Aksha - Giveaway Winner\n\n"+result.getText().toString();
        Intent i=new Intent(Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(Intent.EXTRA_TEXT,body); startActivity(Intent.createChooser(i,"Share giveaway result"));
    }
}
