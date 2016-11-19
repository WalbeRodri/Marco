package adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.Query;

import java.util.ArrayList;

import base.Trip;




public class TripAdapter extends FirebaseRecyclerAdapterNovo<TripAdapter.ViewHolder, Trip> {


    public TripAdapter(Query query, Class<Trip> itemClass, @Nullable ArrayList<Trip> items, @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View view) {
            super(view);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    protected void itemAdded(Trip item, String key, int position) {

    }

    @Override
    protected void itemChanged(Trip oldItem, Trip newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Trip item, String key, int position) {

    }

    @Override
    protected void itemMoved(Trip item, String key, int oldPosition, int newPosition) {

    }

}

/*package projetao.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

import projetao.firebase.adapters.LocalAdapter;
import projetao.firebase.adapters.PerfilAdapter;
import projetao.firebase.base.Local;
import projetao.firebase.base.Perfil;
import projetao.firebase.database.dataBaseMarco;

public class MainRealBack2 extends AppCompatActivity {
    private static final String TAG = "MainRealBack2";
    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";


    private FirebaseDatabase database;
    private DatabaseReference tripRef;
    private dataBaseMarco dbMarco ;


    private LocalAdapter localAdapter; //adapter
    private Query mQuery; //caminho a ser acessado
    private Query mQuery2;
    private ArrayList<Local> mAdapterLocal; //armazena lista de locais
    private ArrayList<String> mAdapterKeys; //chave do nó
    private PerfilAdapter perfilAdapter; //adapter de perfil , não usado


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back2);
         dbMarco = new dataBaseMarco(); //inicializando banco de dados
         handleInstanceState(savedInstanceState);
         setUpFirebase();
         setUpList();
    }

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mAdapterLocal = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mAdapterLocal = new ArrayList<Local>(); //INICIALIZANDO VARIAVEIS
            mAdapterKeys = new ArrayList<String>();
        }
    }


    private void setUpFirebase() {
        mQuery = dbMarco.recoverLocal(); //ACESANDO NÓ DE CONSULTA
        mQuery2 = dbMarco.recoverPerfil();
    }

    public void setUpList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
       localAdapter = new LocalAdapter(mQuery, Local.class, mAdapterLocal, mAdapterKeys);
        perfilAdapter = new PerfilAdapter(mQuery2,Perfil.class);


      recyclerView.setLayoutManager(new LinearLayoutManager(this)); //USANDO A INTERFACE PARA EXIBIR
     recyclerView.setAdapter(localAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    // Saving the list of items and keys of the items on rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(localAdapter.getItems())); //SETANDO LOCAL QUE IRÁ ARMAZENAR
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, localAdapter.getKeys());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localAdapter.destroy(); //destroindo o adapter
        perfilAdapter.destroy();
    }

    public void start(View view) {
        //COMENTÁRIOS  ABAIXO SÃO OS TESTES , PODE DELETAR.
  /*      if(!localAdapter.getItems().isEmpty()) {
            Toast.makeText(getApplicationContext(), localAdapter.getItems().get(0).getName(), Toast.LENGTH_SHORT).show();
     //     Toast.makeText(getApplicationContext(), perfilAdapter.getItem(0).getGender(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "viado", Toast.LENGTH_SHORT).show();

        }
*/
//Date data12 = new Date();

//   Preference preference12 = new Preference();
//    preference12.getPreferences().add("bar");
//      Perfil perfil10 = new Perfil("Djair","e4prolinfo","m",data12,null);
// FirebaseAuth auth = FirebaseAuth.getInstance();
// String uidUser = auth.getCurrentUser().getUid();
//database = FirebaseDatabase.getInstance();

//DatabaseReference perfilRef = database.getReference("perfil").child(uidUser);
//        List<Day> days = new ArrayList<>();
//      List<Local> locas = new ArrayList<>();
//    Day day1 = new Day(data12,locas);
//  days.add(day1);
// Agenda agenda = new Agenda(days);
//locas.add(new Local("Barzinho do Zé", "Bar", 0, "08:00", "3hrs", 120.0, 150.0));
//Trip trip = new Trip("holanda",10.2,"hoi",data12,data12,"holi",agenda,"fok");
//  dbMarco.editPerfil(perfil10,perfilAdapter.getKeys().get(0));
// dbMarco.createPerfil(perfil10);
// dbMarco.createTrip(trip);
//database.getReference("message").setValue("torta");
//tripRef.setValue(trip);
// dbMarco.createPerfil(perfil10);
//dbMarco.recoverPerfil().push().setValue(perfil10);
      /*  dbMarco.createPerfil(perfil10);*/
//        Toast.makeText(getApplicationContext()," sucesso!", Toast.LENGTH_SHORT).show();
     /*   Trip trip = new Trip();
        Agenda agenda = new Agenda();
        Date date1 = new Date(); // passando os parametros para o banco
        Date date2 =  new Date();
        dbMarco.createTrip("docelqa",1992.5,"Recife",date1,date2,"Rua Daminhanopolis",agenda,"8:00");

        //simulando repasse de informação
        //saber deletar,recuperar e criar

        //dbMarco.createTrip(3100.5,"Recife - PE",date1 , date2,null,"Rua da moeda");
*/
//      Toast.makeText(getApplicationContext(), mAdapterLocal.get(1).getSchedule(), Toast.LENGTH_SHORT).show();
// Toast.makeText(getApplicationContext(), mAdapterKeys.get(0), Toast.LENGTH_SHORT).show();
// LocalAdapter.ViewHolder viewHolder = new LocalAdapter.ViewHolder(view);
// localAdapter.onBindViewHolder(viewHolder,0);

/*}





        }*/