public class Sensor {

    /******************************************
    *enum class to represent the sensor type.
     */
    enum Type {
        ANALOG,
        DIGITAL
    }

    /*********************************************************************
    * Attributes of a sensor. Sensor ID, model, type and current reading
     */
    private String ID;
    private String model;
    private Type type;
    private int reading;

    /*********************************************************************
     * Constructor to initialize a sensor only with ID and model.
     * Sensor type is not set.
     * @param ID
     * @param model
     */
    public Sensor(String ID, String model){
        this.ID = ID;
        this.model = model;
    }

    /*********************************************************************
     * Constructor to initialize a sensor with ID, model and type.
     * @param ID
     * @param model
     * @param type
     */
    public Sensor(String ID, String model, Type type){
        this.ID = ID;
        this.model = model;
        this.type = type;
    }

    /********************************************************
     * Getters
     */
    public String getID(){
        return this.ID;
    }
    public String getModel(){
        return this.model;
    }
    public Type getType(){
        return this.type;
    }
    public int getReading(){
        return this.reading;
    }

    /**********************************************************
     * Methods to set sensor type.
     */
    public void setTypeDigital(){
        this.type = Type.DIGITAL;
    }
    public void setTypeAnalog(){
        this.type = Type.ANALOG;
    }

    /**********************************************************
     * This method sets the actual sensor reading value to its
     * corresponding sensor attribute.
     * If the sensor is a DIGITAL sensor, readings greater than
     * 0 are assigned as 1. Readings less than 0 are assigned as 0.
     * @param input
     */
    public void setReading(int input){
        if(this.type == Type.DIGITAL){
            if(input > 0){
                this.reading = 1;
            }
            else{
                this.reading = 0;
            }
        }
        else{
            this.reading = input;
        }
    }

    /*************************************************************************
     * Overrides default toString method.
     * Returns a string which contains all details of the sensor.
     * @return
     */
    public String toString(){
        String str = "[ ID:" + this.ID + " | Model:" + this.model;

        if(this.type == null){
            str += " | Type:Not Set ]";
        }
        else{
            str += " | Type:" + this.type + " | Reading:" + this.reading + " ]";
        }

        return str;
    }
}
