package it.uninsubria.tesiandreaselva;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BPLayoutHierarchy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout rootLayout = new RelativeLayout(this); //creo relative
		RelativeLayout.LayoutParams relLayoutParam = new RelativeLayout.LayoutParams
				(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); // params root
		rootLayout.setPadding(25, 25, 0, 0);
		
		
		RelativeLayout.LayoutParams lpViewTop = new RelativeLayout.LayoutParams
				(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); //parametri textview top
		lpViewTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		TextView tv = new TextView(this);
		tv.setId(1);//set id a 1 poichè a 0 non funziona 
        tv.setText("Best Practices Layout Hierarchy");
        
        rootLayout.addView(tv, lpViewTop); //aggiungo la textview
        
        RelativeLayout.LayoutParams lpViewBelow = new RelativeLayout.LayoutParams //creo parametri per quelli sotto 
				(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpViewBelow.addRule(RelativeLayout.BELOW, tv.getId());
        
        for(int i=2; i<45; i++){ //nell'ordine di secondi riesce ad agire anche con 10000 textview
        	
        	tv = new TextView(this); 	//creo ricorsivamente le text viev aggiungendole
        	tv.setId(i);
            tv.setText("Best Practices Layout Hierarchy");
        	rootLayout.addView(tv, lpViewBelow);
        	
        	lpViewBelow = new RelativeLayout.LayoutParams  //devo ricreare i parametri per poter aggiornare le rule
    				(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        	lpViewBelow.addRule(RelativeLayout.BELOW, tv.getId());
        	
        }
        
        setContentView(rootLayout, relLayoutParam); //creo l intera vista
        //setContentView(R.layout.activity_bplayout_hierarchy);
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		ViewServer.get(this).removeWindow(this);
	}
	
	@Override
    public void onResume() {
		super.onResume();
		ViewServer.get(this).setFocusedWindow(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bplayout_hierarchy, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
