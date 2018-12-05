package cl.telematica.basicconnectionexample.connection;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import cl.telematica.basicconnectionexample.models.Libro;

public interface MyApiService {
    @GET("5bfc6aa9310000780039be36")
    Call<List<Libro>> getLibros();
}
