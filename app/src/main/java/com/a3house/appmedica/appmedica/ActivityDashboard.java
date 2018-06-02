package com.a3house.appmedica.appmedica;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.a3house.appmedica.appmedica.PreferenceHelper.getName;
import static com.a3house.appmedica.appmedica.PreferenceHelper.logOutUser;

public class ActivityDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Usuario2 ultimoUsuario;
    public static Peso2 ultimoPeso;
    private GestionFirebase gf = new GestionFirebase();
    public static Button btnPesoActual;
    public static Button btnIMCActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        gf.recibirUsuario();
        gf.recibirPeso();
        final GestionPeso gestionPeso = new GestionPeso();


        //Implementamos Butterknife
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recuperamos el nombre del usuario
        String nombreUsuario = getName();

        //Mensaje de bienvenida
        Toast bienvenida = Toast.makeText(getApplicationContext(), "¡Hola " + nombreUsuario + "!" + " Cada día estás más cerca de tu objetivo" , Toast.LENGTH_LONG);
        bienvenida.setGravity(Gravity.CENTER, 0,0);
        bienvenida.show();

        //Recuperamos el último peso y el último IMC que se ha enviado a Firebase

        //Recuperamos el boton donde se muestra el Peso Actual
        btnPesoActual = (Button) findViewById(R.id.btnPesoActual);

        //Recuperamos el boton donde se muestra el IMC Actual
        btnIMCActual = findViewById(R.id.btnIMCActual);

        //Recuperamos el resultado de la clasificacionIMC
        //String resultadoEscalaIMC = clasificacionIMC(ultimoPeso);

        //Creamos un escuchador al hacer click sobre el IMC para mostrarlo
        btnIMCActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double imcActual = Double.valueOf(btnIMCActual.getText().toString());
                String miImc = gestionPeso.clasificacionIMC(imcActual);
                Snackbar.make(view, "Tu IMC (índice de masa corporal) corresponde en la escala a "+miImc, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        }
        });

        //Creamos un escuchador al hacer click sobre el Peso Actual
        //que muestra un mensaje motivador

        btnPesoActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double mipesoideal = gestionPeso.calcularPesoIdeal(ultimoUsuario);
                Snackbar.make(view, "Tu peso ideal es "+mipesoideal+" Kg. Cada día estás más cerca de tu objetivo ;)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Botón con icono bascula para introducir el peso

        ImageButton botonBascula = (ImageButton) findViewById(R.id.btnBascula);
        botonBascula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestionPeso gp = new GestionPeso();
                gp.introducirPesoshowDialog(ActivityDashboard.this ,"Introduce tu peso");

            }
        });

        //Si clickan en el texto se llama a la misma función
        Button botonBasculaTexto = (Button) findViewById(R.id.btnBasculaText);
        botonBasculaTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestionPeso gp = new GestionPeso();
                gp.introducirPesoshowDialog(ActivityDashboard.this ,"Introduce tu peso");
            }
        });

        //Muestra el menú lateral
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        timer();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logOutUser();
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_principal) {
            // Handle the camera action
            //boton del menu para volver al Dashboard
            if(this.getClass() != ActivityDashboard.class){
                Intent lanzadorDashboard = new Intent(this, ActivityDashboard.class);
                this.startActivity(lanzadorDashboard);
            }
        } else if (id == R.id.mi_perfil) {
            Intent lanzadorDashboard = new Intent(this, ActivityPerfil.class);
            this.startActivity(lanzadorDashboard);
        } else if (id == R.id.lista_pesos) {
            Intent lanzadorListaPesos = new Intent(this, ActivityListaPesos.class);
            this.startActivity(lanzadorListaPesos);
        } else if (id == R.id.nueva_visita) {
            Intent lanzadorNuevaVisita = new Intent(this, ActivityNuevaVisita.class);
            this.startActivity(lanzadorNuevaVisita);
        } else if (id == R.id.lista_visita) {
            Intent lanzadorListaVisita = new Intent(this, ActivityListaVisitas.class);
            this.startActivity(lanzadorListaVisita);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static Handler manejador = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            //Resuperaremos el contenido del mensaje del setvivio y lo escribiremos
            //por pantalla.
            String resultado = String.valueOf(msg.obj);
            GestionPeso gp = new GestionPeso();
            double imc = gp.calcularIMC(ultimoUsuario, Double.valueOf(resultado));
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("#.#", simbolos);
            btnPesoActual.setText(resultado);
            btnIMCActual.setText(df.format(imc));
        }
    };

    private void timer(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(ultimoPeso != null)
                    ActivityDashboard.manejador.obtainMessage(0, 0, 0, ultimoPeso.getValor()).sendToTarget();
            }
        };
        timer.schedule(task, 5000);
    }

    public static void recibirUnPeso(Peso2 peso){
        ActivityDashboard.ultimoPeso = peso;
    }

    public static void recibirUnUsuario(Usuario2 usuario){
        ActivityDashboard.ultimoUsuario = usuario;
    }
}
