public interface CircularStructureInterface{
    public void addSensor(Sensor sensor);
    public void addSensor(Sensor sensor, int index);
    public CircularStructure.Node getHead();
    public CircularStructure.Node getTail();
    public CircularStructure.Node removeSensor();
    public CircularStructure.Node removeSensor(int index);
    public CircularStructure.Node findNode(int index);
    public void printAllSensors();
}