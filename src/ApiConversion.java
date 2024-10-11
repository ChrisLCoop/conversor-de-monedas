
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ApiConversion {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Scanner teclado = new Scanner(System.in);

        //var monedaBase = teclado.nextLine();
        //var monedaFinal = teclado.nextLine();

        //son loas cambios minimos solicitados del curso.....
        String dolarEstadounidense = "USD";
        String pesoArgentino = "ARS";
        String realBrasil = "BRL";
        String pesoColombiano ="COP";

        //String url = "https://v6.exchangerate-api.com/v6/369bea7047e294b188feee2a/pair/"+monedaBase+"/"+monedaFinal;
        String url1 = "https://v6.exchangerate-api.com/v6/369bea7047e294b188feee2a/pair/"+dolarEstadounidense+"/"+pesoArgentino;
        String url2 = "https://v6.exchangerate-api.com/v6/369bea7047e294b188feee2a/pair/"+dolarEstadounidense+"/"+realBrasil;
        String url3 = "https://v6.exchangerate-api.com/v6/369bea7047e294b188feee2a/pair/"+dolarEstadounidense+"/"+pesoColombiano;


        //primer cambio
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url1))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String primerCambio = response.body();



        //segundo cambio
        HttpClient client2 = HttpClient.newHttpClient();
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(url2))
                .build();

        HttpResponse<String> response2 = client2
                .send(request2, HttpResponse.BodyHandlers.ofString());
        String segundoCambio = response2.body();


        //tercer cambio
        HttpClient client3 = HttpClient.newHttpClient();
        HttpRequest request3 = HttpRequest.newBuilder()
                .uri(URI.create(url3))
                .build();

        HttpResponse<String> response3 = client3
                .send(request3, HttpResponse.BodyHandlers.ofString());
        String tercerCambio = response3.body();



        Gson gson = new Gson();

        //respuesta 1
        JsonObject conversion1 = gson.fromJson(primerCambio, JsonObject.class);
        double valorConversion1 = conversion1.get("conversion_rate").getAsDouble();
        //System.out.println("conversion 1: "+ valorConversion1);


        //respuesta 2
        JsonObject conversion2 = gson.fromJson(segundoCambio, JsonObject.class);
        double valorConversion2 = conversion2.get("conversion_rate").getAsDouble();
        //System.out.println("conversion 2: "+ valorConversion2);

        //respuesta 3
        JsonObject conversion3 = gson.fromJson(tercerCambio, JsonObject.class);
        double valorConversion3 = conversion3.get("conversion_rate").getAsDouble();
        //System.out.println("conversion 3: "+ valorConversion3);
        
        /*------------------------seccion de bucle--------------------*/

        Scanner teclado = new Scanner((System.in));
        System.out.println("******************");
        System.out.println("Bienvenido a tu casa de Cambio ");
        int opcion = 0;
        double cambioMoneda;
        double resultadoDeConversion = 0;
        while ( opcion !=7){
            System.out.println("Que operación desea realizar?");
            System.out.println();
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println();
            System.out.println("Digite el número de la operacion");
            System.out.println("*********************************");

            if (teclado.hasNextInt()){
                opcion = teclado.nextInt();
            }else{
                System.out.println("(o.O) No ingresastes un valor numerico.");
                break;
            }

            if (opcion == 7){
                System.out.println("gracias por usar este sistema .");
                break;
            }else if(opcion > 7){
                System.out.println("(o.O)  Por el momento no tenemos esa opcion.");
                break;
            } else if (0 > opcion) {
                System.out.println("(o.O)  No tenemos ninguna opcion en negativo");
                break;
            }

            System.out.println("Cuanto dinero desea convertir?:");
            if (teclado.hasNextDouble()){
                cambioMoneda = teclado.nextDouble();
            }else{
                System.out.println("(o.O) solo es valido valores numericos..");
                break;
            }

            if(0> cambioMoneda){
                System.out.println("solo es valido numeros positivos");
                break;
            }

            switch (opcion){
                case 1: resultadoDeConversion = Math.round((cambioMoneda * valorConversion1)*100.00) /100.00;
                    System.out.println("El cambio seria :" + resultadoDeConversion+" Pesos Argentinos");
                    break;
                case 2: resultadoDeConversion = Math.round((cambioMoneda / valorConversion1) * 100.00) / 100.00;
                    System.out.println("El cambio seria :" + resultadoDeConversion+" Dólares");
                    break;
                case 3: resultadoDeConversion = Math.round((cambioMoneda * valorConversion2) * 100.00) / 100.00;
                    System.out.println("El cambio seria :" + resultadoDeConversion+" Reales Brasileños");
                    break;
                case 4: resultadoDeConversion = Math.round((cambioMoneda / valorConversion2) * 100.00) / 100.00;
                    System.out.println("El cambio seria :" + resultadoDeConversion+" Dólares");
                    break;
                case 5: resultadoDeConversion = Math.round((cambioMoneda * valorConversion3) * 100.00) / 100.00;
                    System.out.println("El cambio seria :" + resultadoDeConversion+" Pesos Colombianos");
                    break;
                case 6: resultadoDeConversion = Math.round((cambioMoneda / valorConversion3) * 100.00) / 100.00;
                    System.out.println("El cambio seria :" + resultadoDeConversion+" Dólares");
                    break;
                default:
                    System.out.println("opcion no valida");
            }

        }
    }
}
