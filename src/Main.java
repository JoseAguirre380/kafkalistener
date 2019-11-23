public class Main {
    public static void main(String[] args) {
        FreightShipment freightShipment = new FreightShipment();

//        al llamar el metodo StrtConsummer kafka empieza consumir el topico el cual al final te retorna el valor que recibe de kie server
        System.out.println("RESPUESTA DE KIE SERVER --->" + freightShipment.startConsummer("ternium", "test", "mensaje de prubea"));
    }
}
