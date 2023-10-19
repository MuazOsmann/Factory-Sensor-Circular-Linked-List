import java.util.Scanner;

public class Controller {

    /*******************************************
     * Contains a single object of the circular data structure
     */
    private CircularStructure circularStructure;

    /********************************************************
     * Constructor
     * Initializes the data structure object
     */
    public Controller(){
        this.circularStructure = new CircularStructure();
    }

    /********************************************************
     * Print the list of sensors with the updated values
     */
    public void printUpdatedStructure(){
        System.out.println("\n----- Updated Structure -----");
        circularStructure.printAllSensors();
    }

    /****************************************************************
     * Simple menu to for basic operations of the sensor structure
     */
    public void menu(){

        int input = -1;

        Scanner menuScanner = new Scanner(System.in);

        while(input != 0) {
            System.out.println("\n---- Controller Menu ----\n");
            System.out.println("[1] Add new sensor");
            System.out.println("[2] Remove a sensor");
            System.out.println("[3] Print the details of all the sensors");
            System.out.println("[4] Print the details of a sensor");
            System.out.println("[5] Set sensor type");
            System.out.println("[0] Exit");

            System.out.print("\nInsert number : ");
            input = menuScanner.nextInt();

            if(input == 1){
                /* Takes user inputs to create a new sensor */
                System.out.println("\n----- Add a new sensor -----");
                Scanner sensorScanner = new Scanner(System.in);
                System.out.print("Sensor ID : ");
                String ID = sensorScanner.nextLine();
                System.out.print("Sensor Model : ");
                String model = sensorScanner.nextLine();
                System.out.print("Sensor Type [ 1-Analog / 2-Digital / 0-Not Set ] : ");
                int typeInt = sensorScanner.nextInt();
                Sensor.Type type = null;
                if(typeInt == 1)
                    type = Sensor.Type.ANALOG;
                else if(typeInt == 2)
                    type = Sensor.Type.DIGITAL;
                System.out.print("Index [ 0-default ] : ");
                int index = sensorScanner.nextInt();

                if(index == 0)
                    circularStructure.addSensor(new Sensor(ID, model, type)); //Add the sensor in default way
                else
                    circularStructure.addSensor(new Sensor(ID, model, type), index); //Add the sensor into an indexed position

                printUpdatedStructure();
            }

            else if(input == 2){
                /* Take user input for index of the sensor to be deleted */
                System.out.println("\n----- Remove a sensor -----");
                Scanner indexScanner = new Scanner(System.in);
                System.out.print("Index [ 0 - Default ] : ");
                int index = indexScanner.nextInt();

                if(index == 0)
                    circularStructure.removeSensor();  // Default delete method
                else
                    circularStructure.removeSensor(index); // Delete the sensor in the given index

                printUpdatedStructure();

            }
            else if(input == 3){

                /* Print all sensors */
                circularStructure.printAllSensors();
            }
            else if(input == 4){

                /* Take user input for an index of a sensor to print its details */
                System.out.println("\n----- Print the details of a sensor -----");
                Scanner indexScanner = new Scanner(System.in);
                System.out.print("Index : ");
                int index = indexScanner.nextInt();

                /*
                Print the details of the sensor in given index. If a sensor doesn't
                exist at the index an error message will be diplayed.
                 */
                CircularStructure.Node node = circularStructure.findNode(index);
                if(node != null){
                    System.out.println(node.sensor);
                }
                else{
                    System.out.println("Error. Index is out of range!");
                }
            }
            else if(input == 5){
                /* Change the sensor type according to the user input */
                System.out.println("\n----- Set sensor type -----");
                Scanner indexScanner = new Scanner(System.in);
                System.out.print("Index : ");
                int index = indexScanner.nextInt();

                System.out.print("Sensor Type [ 1-Analog / 2-Digital / 0-Not Set ] : ");
                int typeInt = indexScanner.nextInt();

                CircularStructure.Node node = circularStructure.findNode(index);
                if(node != null){
                    if(typeInt == 1) {
                        node.sensor.setTypeAnalog();
                        System.out.println("Type is set to Analog");
                    }
                    else if(typeInt == 2) {
                        node.sensor.setTypeDigital();
                        System.out.println("Type is set to Digital");
                    }
                    else
                        System.out.println("Invalid input!");
                }
                else{
                    System.out.println("Error. Index is out of range!");
                }
            }
        }


    }

    /**********************************************************************
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.menu();
    }
}
