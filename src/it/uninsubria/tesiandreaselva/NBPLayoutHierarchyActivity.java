package it.uninsubria.tesiandreaselva;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NBPLayoutHierarchyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.activity_nbplayout_hierarchy); 
		
		LinearLayout rootLayout = new LinearLayout(this); //layout root
		rootLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);  // params root
		rootLayout.setPadding(25, 25, 0, 0);
		
		LayoutParams lpView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); //parametri textview
        TextView tv = new TextView(this); //creo prima textview
        tv.setText("Not Best Practices Layout Hierarchy");
        
        rootLayout.addView(tv, lpView); //inserisco la textview nella rootLinearLayout
        
        LinearLayout fatherLayout = rootLayout; //copio il root per usarlo in seguito nell'algoritmo
        
		for(int i=0; i<43; i++){ // MAX num di elementi 44 textview (stackoverflowerror)
			
			LinearLayout childLayout = new LinearLayout(this); //creo childLayout
			childLayout.setOrientation(LinearLayout.VERTICAL);
			fatherLayout.addView(childLayout, linLayoutParam); //aggiungo childLayout al fatherLayout
			
			tv = new TextView(this); //creo nuovo text view
		    tv.setText("Not Best Practices Layout Hierarchy");
			childLayout.addView(tv, lpView);  //inserisco nuovo textview al childLayout
			
			fatherLayout=childLayout; //ora il child deve diventare father e si cicla
			
        }
		rootLayout.setBackgroundColor(0xFFFF4C4C);
		setContentView(rootLayout, linLayoutParam);
		//setContentView(R.layout.activity_nbplayout_hierarchy);
		
		ViewServer.get(this).addWindow(this);
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
		getMenuInflater().inflate(R.menu.nbplayout_hierarchy, menu);
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
