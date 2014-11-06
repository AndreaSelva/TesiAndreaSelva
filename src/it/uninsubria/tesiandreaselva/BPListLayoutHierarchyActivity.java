package it.uninsubria.tesiandreaselva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BPListLayoutHierarchyActivity extends Activity {

	private TextView mMemoryTextView;
	private TextView mFreeTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bplist_layout_hierarchy);
		
		ArrayList<Person> personList=new ArrayList<Person>(); //lista delle persone che la listview visualizzerà
	        
	    Person [] people={
	                new Person("Luca","Bolli","3463872640", getPhoto()),
	                new Person("Giovanni", "Plutonio", "3428761119", getPhoto()),
	                new Person("Sandro","Latti","3609382882", getPhoto()),
	                new Person("Manuela","Corte","3338476610", getPhoto()),
	                new Person("Filippa","Sola","3465457887", getPhoto()),
	                new Person("Andrea","Rapa","3487889246", getPhoto()),
	                new Person("Francesca","Gentile","399875458", getPhoto())};
	     
	    Random r=new Random();
	    for(int i=0;i<100;i++){
	                personList.add(people[r.nextInt(people.length)]);
	    }
	  
	    //Questa è la lista che rappresenta la sorgente dei dati della listview
        //ogni elemento è una mappa(chiave->valore)
        ArrayList<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();

        for(int i=0;i<personList.size();i++){
            Person p=personList.get(i);// per ogni persona all'inteno della ditta
            
            HashMap<String,Object> personMap=new HashMap<String, Object>();//creiamo una mappa di valori
            
            personMap.put("name", p.getName()); // per la chiave name,l'informazine sul nome
            personMap.put("surname", p.getSurname());// per la chiave surnaname, l'informazione sul cognome
            personMap.put("phone", p.getTelephone());// per la chiave phone, inseriamo la risorsa dell numero telefono
            personMap.put("image", p.getPhotoRes());// per la chiave immagine inseriamo l'info dell'immagine
            data.add(personMap);  //aggiungiamo la mappa di valori alla sorgente dati
        }
        
        String[] from={"name","surname","phone","image"}; //dai valori contenuti in queste chiavi
        int[] to={R.id.personName,R.id.personSurname,R.id.personPhone,R.id.personImage};//agli id delle view
        
        //costruzione dell adapter
        SimpleAdapter adapter=new SimpleAdapter(
                        getApplicationContext(),
                        data,//sorgente dati
                        R.layout.list_item_bp, //layout contenente gli id di "to"
                        from,
                        to);
       
        //utilizzo dell'adapter
        ListView listView = (ListView)findViewById(R.id.BPlistView);
        listView.setAdapter(adapter);
        
        ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
		mTitleTextView.setText("BP List Layout Hierarchy");
		mMemoryTextView = (TextView) mCustomView.findViewById(R.id.memory_text);
		
		mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
		mFreeTextView = (TextView) mCustomView.findViewById(R.id.free_text);
		mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");

		ImageButton imageButton = (ImageButton)mCustomView.findViewById(R.id.imageButton);
		imageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
				mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");
			}
		});

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
		
        
		ViewServer.get(this).addWindow(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
		mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");
		ViewServer.get(this).removeWindow(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
		mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");
		ViewServer.get(this).setFocusedWindow(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bplist_layout_hierarchy, menu);
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
	private int getPhoto(){
		Random r=new Random();
		int i = r.nextInt(1000)%10;
		if(i==0) return R.drawable.img1;
		else if(i==1) return R.drawable.img2;
		else if(i==2) return R.drawable.img3;
		else if(i==3) return R.drawable.img4;
		else if(i==4) return R.drawable.img5;
		else if(i==5) return R.drawable.img6;
		else if(i==6) return R.drawable.img7;
		else if(i==7) return R.drawable.img8;
		else if(i==8) return R.drawable.img9;
		else return R.drawable.img10;
	}
}
