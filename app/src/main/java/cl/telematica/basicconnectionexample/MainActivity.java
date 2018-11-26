package cl.telematica.basicconnectionexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cl.telematica.basicconnectionexample.connection.HttpServerConnection;
import cl.telematica.basicconnectionexample.models.Libro;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute()
            {
                //Antes de ejecutar la hebra
            }

            @Override
            protected String doInBackground(Void... voids) {
                String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/5bfc6aa9310000780039be36", 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null) {
                    mAdapter = new UIAdapter(Libro.getLibros(result));
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        };

        task.execute();
    }
}
