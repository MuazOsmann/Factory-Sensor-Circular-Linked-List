import java.util.Iterator;

public class CircularStructure implements Iterable<Sensor>, CircularStructureInterface {

    /*********************************************************
     * Inner class to represent a node in the data structure
     * A Node contains a Sensor object, reference to previous node
     * and a reference to the next node.
     */
    class Node{
        Sensor sensor;
        Node previous;
        Node next;

        /*****************************************************
         * Constructor
         * @param sensor
         */
        public Node(Sensor sensor){
            this.sensor = sensor;
        }
    }

    /********************************************************
     * Attributes of the circular structure
     *
     */
    private Node head, tail = null;
    private int nodeCount = 0;

    /********************************************************
     * Getters
     * @return
     */
    public Node getHead(){
        return this.head;
    }
    public Node getTail(){
        return this.tail;
    }

    /********************************************************
     * Add a sensor in the default procedure
     * If the structure is empty new sensor will be added as the head
     * If the structure is having only one sensor, new sensor will be added as the tail
     * Otherwise the sensor will be added at the end of the structure and will be set as the tail.
     * @param sensor
     */
    public void addSensor(Sensor sensor){

        Node newNode = new Node(sensor);

        if(head == null){
            this.head = newNode;
        }
        else if(tail == null){
            this.tail = newNode;
            this.tail.next = head;
            this.tail.previous = head;

            this.head.next = tail;
            this.head.previous = tail;
        }
        else{
            Node tempNode = this.tail;
            this.tail = newNode;
            this.tail.next = head;
            this.tail.previous = tempNode;
            tempNode.next = tail;
        }

    }

    /***********************************************************
     * Add a new sensor to a user given index
     * @param sensor
     * @param index
     */
    public void addSensor(Sensor sensor, int index){

        if(index == 1){
            Node newNode = new Node(sensor);
            Node currentNodeAtIndex = this.head;
            this.head = newNode;
            this.head.next = currentNodeAtIndex;
            this.head.previous = this.tail;
            currentNodeAtIndex.previous = this.head;
            this.tail.next = this.head;
            this.nodeCount++;    
        }
        else if(index == nodeCount && nodeCount != 2){
            addSensor(sensor);            

        }

        else {
            Node newNode = new Node(sensor);

            Node currentNodeAtIndex = findNode(index);

            if (currentNodeAtIndex != null) {
                Node prevNode = currentNodeAtIndex.previous;

                prevNode.next = newNode;
                newNode.previous = prevNode;
                newNode.next = currentNodeAtIndex;
                currentNodeAtIndex.previous = newNode;

                this.nodeCount++;
            }
        }
    }

    /**********************************************************
     * Remove a sensor from the structure in default procedure.(From the beginning)
     * @return
     */
    public Node removeSensor(){
        Node nodeRemove = this.head;
        this.head = this.head.next;
        this.head.previous = this.tail;
        this.tail.next = this.head;
        this.nodeCount--;
        System.out.println("Node removed successfully!");
        return nodeRemove;
    }

    /************************************************************
     * Removes a sensor at a given index.
     * @param index
     * @return
     */
    public Node removeSensor(int index){

        if(index ==  1){
            return removeSensor();
        }
        else if(index == nodeCount){
            Node nodeRemove = this.tail;
            this.tail = this.tail.previous;
            this.tail.next = this.head;
            this.head.previous = this.tail;
            this.nodeCount--;
            System.out.println("Node removed successfully!");
            return nodeRemove;
        }
        else {
            Node nodeRemove = findNode(index);

            if (nodeRemove != null) {
                nodeRemove.previous.next = nodeRemove.next;
                nodeRemove.next.previous = nodeRemove.previous;
                this.nodeCount--;
                System.out.println("Node removed successfully!");
                return nodeRemove;
            }
            else{
                return null;
            }
        }
    }

    /***************************************************************
     * Returns a node at the given index of the structure
     * If the index is invalid, an error message will be printed and
     * a null object will be returned.
     * @param index
     * @return
     */
    public Node findNode(int index){

        if(index < 1 || index > nodeCount){
            System.out.println("\nError. Index is out of range!");
            return null;
        }

        int i = 0;
        Node node = this.head;

        while(i < index){
            node = node.next;
            i++;
        }

        return node;
    }

    /**********************************************************
     * Prints the details of all the sensors.
     */
    public void printAllSensors(){
        int i = 1;
        for (Sensor sensor : this) {
            System.out.println(i + " " + sensor);
            i++;
        }
    }

    /**********************************************************
     * Returns a customized iterator object for this significant data structure
     * @return
     */
    @Override
    public Iterator<Sensor> iterator() {
        return new CircularStructureIterator(this);
    }


}
